package csdassignment;

import BBSList.PassengerList;
import BBSmanager.Bus;
import BBSmanager.Passenger;
import java.util.Scanner;
import java.util.InputMismatchException;

import BBSList.BookingList;
import BBSList.BusList;

public class CSDAssignment {

    private static Scanner sc = new Scanner(System.in);
    private static BookingList bookingList = new BookingList();
    private static BusList busList = new BusList();
    private static PassengerList passengerList = new PassengerList();

    public static void main(String[] args) {
        bookingList.loadFromFile();
        busList.loadFromFile();
        passengerList.loadFromFile();

        while (true) {
            clearScreen();
            System.out.println("=== Bus Booking System ===");
            System.out.println("1. Manage Booking");
            System.out.println("2. Manage Bus");
            System.out.println("3. Manage Passenger");
            System.out.println("4. Save all data");
            System.out.println("0. Exit");

            int choice = getIntInput("Enter your choice: ", 0, 4);

            switch (choice) {
                case 1:
                    manageBooking();
                    break;
                case 2:
                    manageBus();
                    break;
                case 3:
                    managePassenger();
                    break;
                case 4:
                    bookingList.saveToFile();
                    busList.saveToFile();
                    passengerList.saveToFile();
                    System.out.println("Data saved successfully.");
                    waitForEnter();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private static void manageBooking() {
        while (true) {
            clearScreen();
            System.out.println("\n=== Manage Booking ===");
            bookingList.display();
            System.out.println("\n1. Book a Bus");
            System.out.println("2. Sort Booking by Bcode and Pcode");
            System.out.println("3. Pay Booking by Bcode and Pcode");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ", 0, 3);

            switch (choice) {
                case 1:
                    bookingList.bookBus(busList, passengerList);
                    break;
                case 2:
                    bookingList.sortByBcodePcode();
                    System.out.println("Bookings sorted by Bcode and Pcode.");
                    waitForEnter();
                    break;
                case 3:
                    bookingList.payByBcodePcode();
                    break;
                case 0:
                    bookingList.saveToFile();
                    return;
            }
            bookingList.saveToFile();
        }
    }

    private static void manageBus() {
        while (true) {
            clearScreen();
            System.out.println("\n=== Manage Bus ===");
            System.out.println("1. Display Bus List (In-order)");
            System.out.println("2. Display Bus List (Post-order)");
            System.out.println("3. Display Bus List (Breadth-first)");
            System.out.println("4. Add a Bus");
            System.out.println("5. Search Bus by Code");
            System.out.println("6. Delete Bus by Code (Copying)");
            System.out.println("7. Delete Bus by Code (Merging)");
            System.out.println("8. Balance the BST");
            System.out.println("9. Count Total Buses");
            System.out.println("10. Search Booked Seats by Code");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ", 0, 10);

            switch (choice) {
                case 1:
                    busList.displayBusListByInOrder();
                    waitForEnter();
                    break;
                case 2:
                    busList.displayBusListByPostOrder();
                    waitForEnter();
                    break;
                case 3:
                    busList.displayBusListByBreath();
                    waitForEnter();
                    break;
                case 4:
                    busList.inputAndAddBusEnd();
                    break;
                case 5:
                    searchBusByCode();
                    break;
                case 6:
                    deleteByBcodeCopying();
                    break;
                case 7:
                    deleteByBcodeMerging();
                    break;
                case 8:
                    busList.balance();
                    System.out.println("BST has been balanced.");
                    waitForEnter();
                    break;
                case 9:
                    System.out.println("Total number of buses: " + busList.countBuses());
                    waitForEnter();
                    break;
                case 10:
                    searchBookedSeatsByCode();
                    break;
                case 0:
                    busList.saveToFile();
                    return;
            }
            busList.saveToFile();
        }
    }

    private static void managePassenger() {
        while (true) {
            clearScreen();
            System.out.println("\n=== Manage Passenger ===");
            passengerList.display();
            System.out.println("\n1. Add a Passenger");
            System.out.println("2. Search Passenger by Code");
            System.out.println("3. Delete Passenger by Code");
            System.out.println("4. Search Passenger by Name");
            System.out.println("5. Search Bus by Passenger Code");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ", 0, 5);

            switch (choice) {
                case 1:
                    passengerList.inputAndAddToEnd();
                    break;
                case 2:
                    searchPassengerByCode();
                    break;
                case 3:
                    deletePassengerByCode();
                    break;
                case 4:
                    searchPassengerByName();
                    break;
                case 5:
                    searchBusByPassengerCode();
                    break;
                case 0:
                    passengerList.saveToFile();
                    return;
            }
            passengerList.saveToFile();
        }
    }

    private static void searchPassengerByCode() {
        System.out.print("Enter Passenger Code: ");
        String pcode = sc.nextLine().trim();
        Passenger passenger = passengerList.searchByPcode(pcode);
        if (passenger != null) {
            System.out.println("+------------------+----------------------+-----------------+");
            System.out.println("| Passenger Code   | Name                 | Phone           |");
            System.out.println("+------------------+----------------------+-----------------+");
            System.out.println(passenger);
            System.out.println("+------------------+----------------------+-----------------+");
        } else {
            System.out.println("Passenger not found.");
        }
        waitForEnter();
    }

    private static void searchPassengerByName() {
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine().trim();
        Passenger passenger = passengerList.searchByName(name);
        if (passenger != null) {
            System.out.println("+------------------+----------------------+-----------------+");
            System.out.println("| Passenger Code   | Name                 | Phone           |");
            System.out.println("+------------------+----------------------+-----------------+");
            System.out.println(passenger);
            System.out.println("+------------------+----------------------+-----------------+");
        } else {
            System.out.println("Passenger not found.");
        }
        waitForEnter();
    }

    private static void deletePassengerByCode() {
        System.out.print("Enter the passenger code to delete: ");
        String pcode = sc.nextLine().trim();
        if (passengerList.deleteByPcode(pcode, bookingList)) {
            System.out.println("Passenger and related bookings deleted successfully.");
        } else {
            System.out.println("Passenger not found.");
        }
        waitForEnter();
    }

    private static void searchBusByPassengerCode() {
        System.out.print("Enter Passenger Code: ");
        String pcode = sc.nextLine().trim();
        Bus foundBus = passengerList.searchBusByPcode(pcode, bookingList, busList);
        if (foundBus != null) {
            System.out.println("\nFound bus for passenger " + pcode + ":");
            System.out.println(foundBus);
        }
        waitForEnter();
    }

    private static void searchBusByCode() {
        System.out.print("Enter Bus Code: ");
        String bcode = sc.nextLine().trim();
        Bus bus = busList.searchByBcode(bcode);
        if (bus != null) {
            System.out.println(bus);
        } else {
            System.out.println("Bus not found.");
        }
        waitForEnter();
    }

    private static void searchBookedSeatsByCode() {
        System.out.print("Enter Bus Code to search for booked seats: ");
        String bcode = sc.nextLine().trim();
        busList.searchBookedByBcode(bcode, bookingList, passengerList);
        waitForEnter();
    }

    private static void deleteByBcodeCopying() {
        System.out.print("Enter the bus code to delete: ");
        String bcode = sc.nextLine().trim();
        if (busList.deleteByBcodeCopying(bcode, bookingList)) {
            System.out.println("Bus and related bookings deleted successfully.");
        } else {
            System.out.println("Bus not found.");
        }
        waitForEnter();
    }

    private static void deleteByBcodeMerging() {
        System.out.print("Enter the bus code to delete: ");
        String bcode = sc.nextLine().trim();
        if (busList.deleteByBcodeMerging(bcode, bookingList)) {
            System.out.println("Bus and related bookings deleted successfully.");
        } else {
            System.out.println("Bus not found.");
        }
        waitForEnter();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
    }

    private static int getIntInput(String prompt, int min, int max) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume invalid input
            }
        }
    }
}