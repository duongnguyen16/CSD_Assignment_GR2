
package BBSList;

/**
 *
 * @author Son
 */
import BBSmanager.Bus;
import LinkedList.MyList;
import BStree.MyBSTree;
import BStree.MyQueue;
import BStree.Node;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import LinkedList.Validate;

public class BusList extends MyBSTree<Bus> {
   public Validate vali = new Validate();
   public Scanner scan;
   public Validate validate = new Validate();

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
               this.insert(bus);
            }
         }
         br.close();

         return true;
      } catch (Exception var16) {
         var16.printStackTrace();
         return false;
      }
   }

   public void inputAndAddBusEnd() {
      String bcode;
      do {
         System.out.println("Enter the code of the bus: ");
         bcode = scan.nextLine();
      } while (!searchBcode(bcode));

      System.out.println("Enter the number of the bus: ");
      String bnum = scan.nextLine();

      System.out.println("Enter the departing station of the bus: ");
      String dstation = scan.nextLine();

      System.out.println("Enter the arriving station of the bus: ");
      String astation = scan.nextLine();

      System.out.println("Enter the departing time of the bus: ");
      double dtime = validate.checkInputDoubleLimit(0, 24);

      System.out.println("Enter the number of seats in the bus: ");
      int seat = validate.checkInputIntLimit(0, Integer.MAX_VALUE);

      System.out.println("Enter the number of seats which are booked: ");
      int booked = validate.checkInputIntLimit(0, seat);

      System.out.println("Enter the arriving time of the bus: ");
      double atime = validate.checkInputDoubleLimit(dtime, 24);

      Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
      insert(bus);
   }

   public void displayBusListByInOrder() {
      System.out.printf("| %-10s | %-15s | %-25s | %-25s | %-20s | %-20s | %-25s | %-20s |\n", "Bus Code",
            "Number of Bus", "Departing Station", "Arriving Station", "Departing Time", "Number of Seats",
            "Number of seats booked", "Arriving Time");
      MyList<Bus> busList = new MyList<>();
      inOrderToMyList(busList, root);
      busList.traverse();
   }

   public void displayBusListByPostOrder() {
      System.out.printf("| %-10s | %-15s | %-25s | %-25s | %-20s | %-20s | %-25s | %-20s |\n", "Bus Code",
            "Number of Bus", "Departing Station", "Arriving Station", "Departing Time", "Number of Seats",
            "Number of seats booked", "Arriving Time");
      MyList<Bus> busList = new MyList<>();
      postOrderToMyList(busList, root);
      busList.traverse();
   }

   public void displayBusListByBreath() {
      System.out.printf("| %-10s | %-15s | %-25s | %-25s | %-20s | %-20s | %-25s | %-20s |\n", "Bus Code",
            "Number of Bus", "Departing Station", "Arriving Station", "Departing Time", "Number of Seats",
            "Number of seats booked", "Arriving Time");
      MyList<Bus> busList = new MyList<>();
      breathFirstToMyList(busList, root);
      busList.traverse();
   }

   public void saveToFile() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("./buses.txt"))) {
         postOrder(root, bw);
         System.out.println("Save to file Successful");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void postOrder(Node<Bus> p, BufferedWriter bw) throws IOException {
      if (p == null) {
         return;
      }
      postOrder(p.left, bw);
      postOrder(p.right, bw);
      bw.write(p.info.getBcode() + ", " +
            p.info.getBnum() + ", " +
            p.info.getDstation() + ", " +
            p.info.getAstation() + ", " +
            p.info.getDtime() + ", " +
            p.info.getSeat() + ", " +
            p.info.getBooked() + ", " +
            p.info.getAtime());
      bw.newLine();
   }

   public Bus searchByBcode(String bcode) {
      Node<Bus> current = root;
      while (current != null) {
         int comparison = current.info.getBcode().toLowerCase().compareTo(bcode.toLowerCase());
         if (comparison == 0) {
            return current.info;
         } else if (comparison > 0) {
            current = current.left;
         } else {
            current = current.right;
         }
      }
      return null;
   }

   public boolean deleteByBcodeCopying(String bcode, BookingList bookingList) {

      bookingList.deleteBookingsByBcode(bcode);
      Bus busToDelete = searchByBcode(bcode);
      if (busToDelete != null) {
         deleteByCopy(busToDelete);
         return true;
      }
      return false;
   }

   public boolean deleteByBcodeMerging(String bcode, BookingList bookingList) {
      bookingList.deleteBookingsByBcode(bcode);
      Bus busToDelete = searchByBcode(bcode);
      if (busToDelete != null) {
         deleteByMerging(busToDelete);
         return true;
      }
      return false;
   }

   public void balance() {
      MyList<Bus> n = new MyList<>();
      breathFirstToMyList(n, root);
      clear();
      balance(n, 0, n.size() - 1);
   }

   public int countBuses() {
      return countBuses(root);
   }

   private int countBuses(Node<Bus> node) {
      if (node == null) {
         return 0;
      }
      return 1 + countBuses(node.left) + countBuses(node.right);
   }

   public boolean searchBcode(String name) {
      Node<Bus> current = root;
      while (current != null) {
         int comparison = current.info.getBcode().compareTo(name);

         if (comparison == 0) {
            System.err.println("Code already exists");
            return false;
         } else if (comparison > 0) {
            current = current.left;
         } else {
            current = current.right;
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
