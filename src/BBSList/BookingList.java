
package BBSList;

import BBSmanager.Booking;
import BBSmanager.Bus;
import BBSmanager.Passenger;
import LinkedList.MyList;
import LinkedList.Node;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author Duong & Thao
 */

public class BookingList extends MyList<Booking> {

    Scanner sc = new Scanner(System.in);

    public void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./bookings.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5) {
                    String bcode = parts[0].trim();
                    String pcode = parts[1].trim();

                    Date odate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        odate = sdf.parse(parts[2].trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    boolean paid = Boolean.parseBoolean(parts[3].trim());
                    int seat = Integer.parseInt(parts[4].trim());
                    Booking bk = new Booking(bcode, pcode, odate, paid, seat);
                    this.addLast(bk);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Not found");
        }
    }

    public void bookBus(BusList busList, PassengerList passengerList) {
        String bcode = "", pcode = "";
        Bus bus = null;
        Passenger passenger = null;
        int seat = 0;
        boolean validInput = false;

        while (!validInput) {
            do {
                System.out.println("Enter bcode: ");
                bcode = sc.nextLine().trim();
                if (bcode.isEmpty()) {
                    System.out.println("Bus code cannot be empty. Please try again.");
                } else {
                    bus = busList.searchByBcode(bcode);
                    if (bus == null) {
                        System.out.println("Bus not found. Please try again.");
                    }
                }
            } while (bcode.isEmpty() || bus == null);

            int totalBookedSeats = calculateTotalBookedSeats(bcode, bus);
            int availableSeats = bus.getSeat() - totalBookedSeats;

            // check if no seat available, return
            if (availableSeats == 0) {
                System.out.println("No seats available for this bus. Please find another bus.");
                sc.nextLine();
                return;
            }

            do {
                System.out.println("Enter pcode: ");
                pcode = sc.nextLine().trim();
                if (pcode.isEmpty()) {
                    System.out.println("Passenger code cannot be empty. Please try again.");
                } else {
                    passenger = passengerList.searchByPcode(pcode);
                    if (passenger == null) {
                        System.out.println("Passenger not found. Please try again.");
                    }
                }
            } while (pcode.isEmpty() || passenger == null);


            boolean validSeat = false;
            while (!validSeat) {
                System.out.println("Enter seat: ");
                try {
                    seat = Integer.parseInt(sc.nextLine().trim());
                    if (seat <= 0) {
                        System.out.println("Seat number must be positive. Please try again.");
                    } else if (seat > availableSeats) {
                        System.out.println("Not enough seats available. Available seats: "
                                + availableSeats + ". Please try again.");
                    } else {
                        validSeat = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid seat number. Please enter a valid integer.");
                }
            }

            System.out.println("Booking Details:");
            System.out.println("Bus Code: " + bcode);
            System.out.println("Passenger Code: " + pcode);
            System.out.println("Seats: " + seat);
            System.out.println("Is this correct? (Y/N)");
            String confirm = sc.nextLine().trim().toLowerCase();
            if (confirm.equals("y")) {
                validInput = true;
            }
        }

        Date odate = new Date();
        boolean paid = false;
        Booking bk = new Booking(bcode, pcode, odate, paid, seat);

        try {
            this.addLast(bk);
            System.out.println("Booking successful.");
        } catch (Exception e) {
            System.out.println("An error occurred during booking. The operation has been cancelled.");
        }
    }

    private int calculateTotalBookedSeats(String bcode, Bus bus) {
        int totalBookedSeats = 0;
        Node<Booking> current = this.getHead();
        while (current != null) {
            if (current.info.getBcode().equals(bcode)) {
                totalBookedSeats += current.info.getSeat();
            }
            current = current.next;
        }
        // plus booked seat from the bcode in bus
        totalBookedSeats += bus.getBooked();
        return totalBookedSeats;
    }

    public void display() {
        System.out.printf("| %-10s | %-10s | %-15s | %-5s | %-5s |\n", "Bcode", "Pcode", "Odate", "Paid", "Seat");
        this.traverse();
    }

    public String exportToDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("bookings.txt"));
            Node<Booking> current = this.getHead();
            while (current != null) {
                Booking bk = current.info;
                bw.write(bk.getBcode() + ", " + bk.getPcode() + ", " + exportToDate(bk.getOdate()) + ", "
                        + bk.isPaid() + ", " + bk.getSeat());
                bw.newLine();
                current = current.next;
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortByBcodePcode() {

        this.sort(new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                if (o1.getBcode().compareTo(o2.getBcode()) == 0) {
                    return o1.getPcode().compareTo(o2.getPcode());
                }
                return o1.getBcode().compareTo(o2.getBcode());
            }
        });
    }

    public void payByBcodePcode() {
        System.out.println("Enter bcode: ");
        String bcode = sc.nextLine().trim();
        System.out.println("Enter pcode: ");
        String pcode = sc.nextLine().trim();

        Node<Booking> current = this.getHead();
        while (current != null) {
            if (current.info.getBcode().equals(bcode) && current.info.getPcode().equals(pcode)) {
                if (!current.info.isPaid()) {
                    current.info.setPaid(true);
                    System.out.println("Booking marked as paid.");
                    return;
                } else {
                    System.out.println("This booking is already paid.");
                    return;
                }
            }
            current = current.next;
        }
        System.out.println("Booking not found.");
    }

    public void deleteBookingsByBcode(String bcode) {
        Node<Booking> current = this.getHead();
        while (current != null) {
            if (current.info.getBcode().equals(bcode)) {
                Node<Booking> toDelete = current;
                current = current.next;
                this.remove(toDelete);
            } else {
                current = current.next;
            }
        }
    }

    public void deleteBookingsByPcode(String pcode) {
        Node<Booking> current = this.getHead();
        while (current != null) {
            if (current.info.getPcode().equals(pcode)) {
                Node<Booking> toDelete = current;
                current = current.next;
                this.remove(toDelete);
            } else {
                current = current.next;
            }
        }
    }

    public void displayPassengersForBus(String bcode, PassengerList passengerList) {
        Node<Booking> current = this.getHead();
        while (current != null) {
            if (current.info.getBcode().equals(bcode)) {
                Passenger passenger = passengerList.searchByPcode(current.info.getPcode());
                if (passenger != null) {
                    System.out.println(passenger);
                }
            }
            current = current.next;
        }
    }

}
