
package BBSList;

import BBSmanager.Booking;
import BBSmanager.Bus;
import BBSmanager.Passenger;
import LinkedList.MyList;
import LinkedList.Node;
import LinkedList.Validate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author Duong & Thao
 */

public class BookingList extends MyList<Booking> {

    Scanner sc = new Scanner(System.in);
    Validate validate = new Validate();

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
        String bcode, pcode;
        Bus bus = null;
        Passenger passenger = null;

        // Get valid bus code
        do {
            System.out.print("Enter bus code: ");
            bcode = validate.checkInputString();
            bus = busList.searchByBcode(bcode);
            if (bus == null) {
                System.out.println("Bus not found. Please try again.");
            }
        } while (bus == null);

        // Check available seats
        int availableSeats = bus.getSeat() - bus.getBooked();
        if (availableSeats <= 0) {
            System.out.println("Sorry, this bus is fully booked.");
            return;
        }

        // Get valid passenger code
        do {
            System.out.print("Enter passenger code: ");
            pcode = validate.checkInputString();
            passenger = passengerList.searchByPcode(pcode);
            if (passenger == null) {
                System.out.println("Passenger not found. Please try again.");
            }
        } while (passenger == null);

        // Check if passenger already has a booking for this bus
        if (hasExistingBooking(bcode, pcode)) {
            System.out.println("This passenger already has a booking for this bus.");
            sc.nextLine();
            return;
        }

        // Get number of seats to book
        System.out.println("Available seats: " + availableSeats);
        System.out.print("Enter number of seats to book (1-" + availableSeats + "): ");
        int seatsToBook = validate.checkInputIntLimit(1, availableSeats);

        // Create booking
        Booking newBooking = new Booking(bcode, pcode, new Date(), false, seatsToBook);
        addLast(newBooking);
        bus.setBooked(bus.getBooked() + seatsToBook);

        System.out.println("Booking created successfully!");
    }

    public boolean hasExistingBooking(String bcode, String pcode) {
        Node<Booking> current = getHead();
        while (current != null) {
            if (current.info.getBcode().equals(bcode) &&
                    current.info.getPcode().equals(pcode)) {
                return true;
            }
            current = current.next;
        }
        return false;
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
        String bcode = validate.checkInputString();
        System.out.println("Enter pcode: ");
        String pcode = validate.checkInputString();

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
        Node<Booking> current = getHead();
        while (current != null) {
            if (current.info.getBcode().equals(bcode)) {
                Node<Booking> toDelete = current;
                current = current.next;
                remove(toDelete);
            } else {
                current = current.next;
            }
        }
    }

    public void deleteBookingsByPcode(String pcode) {
        Node<Booking> current = getHead();
        while (current != null) {
            if (current.info.getPcode().equals(pcode)) {
                Node<Booking> toDelete = current;
                current = current.next;
                remove(toDelete);
            } else {
                current = current.next;
            }
        }
    }

    public void displayPassengersForBus(String bcode, PassengerList passengerList) {
        boolean found = false;
        Node<Booking> current = getHead();

        while (current != null) {
            if (current.info.getBcode().equals(bcode)) {
                Passenger p = passengerList.searchByPcode(current.info.getPcode());
                if (p != null) {
                    System.out.println(p + " (Seats: " + current.info.getSeat() +
                            ", Paid: " + (current.info.isPaid() ? "Yes" : "No") + ")");
                    found = true;
                }
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No passengers found for this bus.");
        }
    }

}
