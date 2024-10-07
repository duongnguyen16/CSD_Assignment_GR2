
package BBSList;

/**
 *
 * @author Son
 */
import BBSmanager.Bus;
import LinkedList.MyList;
import LinkedList.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import LinkedList.Validate;

public class BusList extends MyList<Bus> {
   public Validate vali = new Validate();
   public Scanner scan;

   public BusList() {
      this.scan = new Scanner(System.in);
   }

   public boolean loadFromFile() {
      try {
         BufferedReader br = new BufferedReader(new FileReader("./buses.txt"));

         String line;
         while ((line = br.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts.length >= 3) {
               String bcode = parts[0].trim();
               String bnum = parts[1].trim();
               String dstation = parts[2].trim();
               String astation = parts[3].trim();
               double dtime = Double.parseDouble(parts[4].trim());
               int seat = Integer.parseInt(parts[5].trim());
               int booked = Integer.parseInt(parts[6].trim());
               double atime = Double.parseDouble(parts[7].trim());
               Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
               this.addLast(bus);
            }
         }

         return true;
      } catch (Exception var16) {
         var16.printStackTrace();
         return false;
      }
   }

   public void inputAndAddBusEnd() {
      System.out.println("Enter the bus code: ");
      String bcode = vali.searchBcode();
      System.out.println("Enter the number of the bus: ");
      String bnum = scan.nextLine();

      System.out.println("Enter the departing station of the bus: ");
      String dstation = scan.nextLine();

      System.out.println("Enter the arriving station of the bus: ");
      String astation = scan.nextLine();
      double dtime;
      do {
         dtime = vali.getValidTime(scan, "Enter the departing time of the bus (0-24): ");
         if (dtime < 0 || dtime > 24) {
            System.out.println("The departing time of the bus must be greater than 0 and smaller than or equal to 24.");
         }
      } while (dtime < 0 || dtime > 24);
      int seat;
      do {
         seat = vali.getValidNumber(scan, "Enter the number of seats in the bus (greater than 0): ");
         if (seat <= 0) {
            System.out.println("The number of seats must be greater than 0.");
         }
      } while (seat <= 0);
      int booked;
      do {
         booked = vali.getValidNumber(scan, "Enter the number of seats which are booked (0 to number of seats): ");
         if (booked < 0 || booked > seat) {
            System.out.println("The number of seats which are booked must be between 0 and the number of seats.");
         }
      } while (booked < 0 || booked > seat);
      double atime;
      do {
         atime = vali.getValidTime(scan, "Enter the arriving time of the bus (greater than departing time and 0-24): ");
         if (atime < dtime || atime > 24) {
            System.out.println(
                  "The arriving time of the bus must be greater than the departing time and less than or equal to 24.");
         }
      } while (atime < dtime || atime > 24);

      Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
      addLast(bus);
   }

   public void displayBusList() {
      System.out.printf("| %-10s | %-15s | %-25s | %-25s | %-20s | %-20s | %-25s | %-20s |\n", "Bus Code",
            "Number of Bus", "Departing Station", "Arriving Station", "Departing Time", "Number of Seats",
            "Number of seats booked", "Arriving Time");
      this.traverse();
   }

   public boolean saveToFile(String filename) {
      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
         boolean var4;
         try {
            Node<Bus> current = this.getHead();

            while (true) {
               if (current == null) {
                  var4 = true;
                  break;
               }

               bw.write(((Bus) current.info).saveFile());
               bw.newLine();
               current = current.next;
            }
         } catch (Throwable var6) {
            try {
               bw.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         bw.close();
         return var4;
      } catch (IOException var7) {
         var7.printStackTrace();
         return false;
      }
   }

   public Bus searchByBcode(String bcode) {
      for (Node<Bus> current = this.getHead(); current != null; current = current.next) {
         if (((Bus) current.info).getBcode().equalsIgnoreCase(bcode)) {
            return (Bus) current.info;
         }
      }
      return null;
   }

   public boolean deleteByBcode(String bcode, BookingList bookingList) {
      Node<Bus> current = this.getHead();
      while (current != null) {
         if (current.info.getBcode().equalsIgnoreCase(bcode)) {
            // Delete related bookings first
            bookingList.deleteBookingsByBcode(bcode);
            // Then remove the bus
            this.remove(current);
            return true;
         }
         current = current.next;
      }
      return false;
   }

   public void sortByBcode() {
      Node<Bus> head = this.getHead();
      if (head != null && head.next != null) {
         boolean swapped;
         do {
            swapped = false;

            for (Node<Bus> current = head; current.next != null; current = current.next) {
               if (((Bus) current.info).getBcode().compareTo(((Bus) current.next.info).getBcode()) > 0) {
                  Bus temp = (Bus) current.info;
                  current.info = current.next.info;
                  current.next.info = temp;
                  swapped = true;
               }
            }
         } while (swapped);

      }
   }

   public void inputAndAddBusBeginning() {
      String bcode;
      do {
         System.out.println("Enter the code of the bus: ");
         bcode = scan.nextLine();
         if (!searchCode(bcode)) {
            System.out.println("Bus Code already Exist");
         }
      } while (!searchCode(bcode));
      System.out.println("Enter the number of the bus: ");
      String bnum = scan.nextLine();
      System.out.println("Enter the departing station of the bus: ");
      String dstation = scan.nextLine();
      System.out.println("Enter the arriving station of the bus: ");
      String astation = scan.nextLine();
      System.out.println("Enter the departing time of the bus: ");
      double dtime;
      while (true) {
         dtime = scan.nextDouble();
         if (dtime >= 0 && dtime <= 24) {
            break;
         }
         System.out.println("the departing time of the bus must be greater than 0 and smaller than 24");
      }
      System.out.println("Enter the number of seats in the bus: ");
      int seat;
      while (true) {
         seat = scan.nextInt();
         if (seat > 0) {
            break;
         }
         System.out.println("the number of seats must be greater than 0");
      }
      System.out.println("Enter the number of seats which are booked: ");
      int booked;
      while (true) {
         booked = scan.nextInt();
         if (booked >= 0 && booked <= seat) {
            break;
         }
         System.out
               .println("the number of seats which are booked must be greater than 0 and smaller than number of seats");
      }
      System.out.println("Enter the arriving time of the bus: ");
      double atime;
      while (true) {
         atime = scan.nextDouble();
         if (atime >= dtime && atime <= 24) {
            break;
         }
         System.out.println("the arriving time of the bus must be greater than departing time and smaller than 24");
      }

      Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
      addFirst(bus);
   }

   public void addAfterPosition() {
      System.out.println("Enter position to add after: ");
      int k = scan.nextInt();
      scan.nextLine();
      String bcode;
      do {
         System.out.println("Enter the code of the bus: ");
         bcode = scan.nextLine();
         if (!searchCode(bcode)) {
            System.out.println("Bus Code already Exist");
         }
      } while (!searchCode(bcode));
      System.out.println("Enter the number of the bus: ");
      String bnum = scan.nextLine();
      System.out.println("Enter the departing station of the bus: ");
      String dstation = scan.nextLine();
      System.out.println("Enter the arriving station of the bus: ");
      String astation = scan.nextLine();
      System.out.println("Enter the departing time of the bus: ");
      double dtime;
      while (true) {
         dtime = scan.nextDouble();
         if (dtime >= 0 && dtime <= 24) {
            break;
         }
         System.out.println("the departing time of the bus must be greater than 0 and smaller than 24");
      }
      System.out.println("Enter the number of seats in the bus: ");
      int seat;
      while (true) {
         seat = scan.nextInt();
         if (seat > 0) {
            break;
         }
         System.out.println("the number of seats must be greater than 0");
      }
      System.out.println("Enter the number of seats which are booked: ");
      int booked;
      while (true) {
         booked = scan.nextInt();
         if (booked >= 0 && booked <= seat) {
            break;
         }
         System.out
               .println("the number of seats which are booked must be greater than 0 and smaller than number of seats");
      }
      System.out.println("Enter the arriving time of the bus: ");
      double atime;
      while (true) {
         atime = scan.nextDouble();
         if (atime >= dtime && atime <= 24) {
            break;
         }
         System.out.println("the arriving time of the bus must be greater than departing time and smaller than 24");
      }

      Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
      insertAfter(k, bus);
   }

   public void deleteAtPosition(int k) {
      if (this.remove(k)) {
         System.out.println("Bus removed successfully");
      } else {
         System.out.println("Bus not found or cannot be removed");
      }

   }

   public boolean searchCode(String name) {
      for (Node<Bus> current = this.getHead(); current != null; current = current.next) {
         if (current.info.getBcode().equalsIgnoreCase(name)) {
            return false;
         }
      }
      return true;
   }

   public void searchBookedByBcode(String bcode, BookingList bookingList, PassengerList passengerList) {
      Bus bus = this.searchByBcode(bcode);
      if (bus != null) {
         System.out.println("Bus found:");
         System.out.println(bus);
         System.out.println("\nPassengers who booked this bus:");
         bookingList.displayPassengersForBus(bcode, passengerList);
      } else {
         System.out.println("Bus not found.");
      }
   }
}
