/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBSList;

import BBSmanager.Booking;
import BBSmanager.Bus;
import BBSmanager.Passenger;
import BStree.MyBSTree;
import BStree.Node;
import LinkedList.MyList;
import LinkedList.Validate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Minh & Hung
 */
public class PassengerList extends MyBSTree<Passenger> {
   public Scanner sc = new Scanner(System.in);
   public Validate vali = new Validate();

   public boolean loadFromFile() {
      try {
         BufferedReader br = new BufferedReader(new FileReader("./passengers.txt"));

         String line;
         while ((line = br.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts.length >= 3) {
               String pcode = parts[0].trim();
               String name = parts[1].trim();
               String phone = parts[2].trim();
               Passenger ps = new Passenger(pcode, name, phone);
               this.insert(ps);
            }
         }

         return true;
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("Not found");
         return false;
      }
   }

   public void inputAndAddToEnd() {
      String pcode;
      do {
         System.out.println("Enter Passenger code: ");
         pcode = sc.nextLine().trim();
      } while (!searchPcode(pcode));
      System.out.println("Enter Paseenger name: ");
      String name = vali.checkName();
      System.out.println("Enter Passenger phone: ");
      String phone = checkPhone();
      Passenger ps = new Passenger(pcode, name, phone);
      this.insert(ps);
   }

   public void display() {
      System.out.println("+------------------+----------------------+-----------------+");
      System.out.println("| Passenger Code   | Name                 | Phone           |");
      System.out.println("+------------------+----------------------+-----------------+");
      MyList<Passenger> passengersList = new MyList<>();
      inOrderToMyList(passengersList, root);
      passengersList.traverse();
      System.out.println("+------------------+----------------------+-----------------+");
   }

   public void saveToFile() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("./passengers.txt"))) {
         inOrder(root, bw);
         System.out.println("Save to file Successful");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void inOrder(Node<Passenger> p, BufferedWriter bw) throws IOException {
      if (p == null) {
         return;
      }
      inOrder(p.left, bw);
      bw.write(p.info.getPcode() + ", " + p.info.getName() + ", " + p.info.getPhone());
      bw.newLine();
      inOrder(p.right, bw);
   }

   public Passenger searchByPcode(String pcode) {
      Node<Passenger> current = root;
      while (current != null) {
         int comparison = current.info.getPcode().compareTo(pcode);
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

   // search pcode in this list
   public boolean searchPcode(String pcode) {
      Node<Passenger> current = root;
      while (current != null) {
         int comparison = current.info.getPcode().compareTo(pcode);
         if (comparison == 0) {
            System.out.println("Passenger code already exists. Please enter another:");
            return false;
         } else if (comparison > 0) {
            current = current.left;
         } else {
            current = current.right;
         }
      }
      return true;
   }

   

   public Passenger searchByName(String name) {
      return searchByNameInOrder(root, name);
   }

   public Passenger searchByNameInOrder(Node<Passenger> node, String name) {
      if (node == null) {
         return null;
      }
      Passenger found = searchByNameInOrder(node.left, name);
      if (found != null) {
         return found;
      }
      if (node.info.getName().equalsIgnoreCase(name)) {
         return node.info;
      }
      return searchByNameInOrder(node.right, name);
   }

   public String checkPhone() {
      while (true) {
         String phone = sc.nextLine().trim();
         if (phone.isEmpty()) {
            System.out.println("Phone number cannot be empty. Please enter again:");
            continue;
         }
         if (!phone.matches("\\d+")) {
            System.out.println("The phone number should contain only digits. Please enter again:");
            continue;
         }
         boolean exists = false;
         MyList<Passenger> n = new MyList<>();
         breathFirstToMyList(n, root);
         for (int i = 0; i < n.size(); i++) {
            if (n.get(i).info.getPhone().equals(phone)) {
               exists = true;
               break;
            }
         }
         if (!exists) {
            System.out.println("Phone number already exists. Please enter again:");
            return phone;
         }
      }
   }

   public Bus searchBusByPcode(String pcode, BookingList bookingList, BusList busList) {
      Passenger passenger = searchByPcode(pcode);
      if (passenger == null) {
         System.out.println("Passenger not found.");
         return null;
      }
      LinkedList.Node<Booking> currentBooking = bookingList.getHead();
      while (currentBooking != null) {
         Booking booking = currentBooking.info;
         if (booking.getPcode().equals(pcode)) {
            Bus bus = busList.searchByBcode(booking.getBcode());
            if (bus != null) {
               return bus;
            }
         }
         currentBooking = currentBooking.next;
      }
      System.out.println("No Bus found for this Passenger.");
      return null;
   }
   public boolean deleteByPcode(String pcode, BookingList bookingList) {
      Passenger passengerDelete = searchByPcode(pcode);

      if (passengerDelete != null) {
         bookingList.deleteBookingsByPcode(pcode);
         deleteByCopy(passengerDelete);
         return true;
      }
      return false;
   }
}
