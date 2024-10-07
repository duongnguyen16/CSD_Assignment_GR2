/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBSList;

import BBSmanager.Booking;
import BBSmanager.Bus;
import BBSmanager.Passenger;
import LinkedList.MyList;
import LinkedList.Node;
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
public class PassengerList extends MyList<Passenger> {
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
               this.addLast(ps);
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
      System.out.println("Enter Passenger code: ");
      String pcode = vali.searchPcode();
      System.out.println("Enter Paseenger name: ");
      String name = vali.checkName();
      System.out.println("Enter Passenger phone: ");
      String phone = vali.checkPhone();
      Passenger ps = new Passenger(pcode, name, phone);
      this.addLast(ps);
   }

   public void display() {
      System.out.println("+------------------+----------------------+-----------------+");
      System.out.println("| Passenger Code    | Name                 | Phone           |");
      System.out.println("+------------------+----------------------+-----------------+");
      this.traverse();
   }

   public void saveToFile() {

      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter("passengers.txt"));
         Node<Passenger> current = this.getHead();
         while (current != null) {
            Passenger p = current.info;
            bw.write(p.getPcode() + ", " + p.getName() + ", " + p.getPhone());
            bw.newLine();
            current = current.next;
         }
         System.out.println("Save to file success");
         bw.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public Passenger searchByPcode(String pcode) {
      Node<Passenger> current = this.getHead();
      while (current != null) {
         if (current.info.getPcode().equals(pcode)) {
            return current.info;
         }
         current = current.next;
      }

      return null;
   }

   public boolean deleteByPcode(String pcode, BookingList bookingList) {
      Node<Passenger> current = this.getHead();
      while (current != null) {
         if (current.info.getPcode().equals(pcode)) {
            // Delete related bookings first
            bookingList.deleteBookingsByPcode(pcode);
            // Then remove the passenger
            this.remove(current);
            return true;
         }
         current = current.next;
      }
      return false;
   }

   public Passenger searchByName(String name) {
      Node<Passenger> current = this.getHead();
      while (current != null) {
         if (current.info.getName().equals(name)) {
            return current.info;
         }
         current = current.next;
      }

      return null;
   }

   public Bus searchBusByPcode(String pcode, BookingList bookingList, BusList busList) {
      Passenger passenger = searchByPcode(pcode);
      if (passenger == null) {
         System.out.println("Passenger not found.");
         return null;
      }
      Node<Booking> currentBooking = bookingList.getHead();
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
}
