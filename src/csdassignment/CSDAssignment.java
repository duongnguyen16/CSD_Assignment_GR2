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
            busList.displayBusList();
            System.out.println("\n1. Add a Bus to the End");
            System.out.println("2. Search Bus by Code");
            System.out.println("3. Delete Bus by Code");
            System.out.println("4. Sort Bus by Bcode");
            System.out.println("5. Add a Bus to the Beginning");
            System.out.println("6. Add after position");
            System.out.println("7. Delete bus at position");
            System.out.println("8. Search booked seat by code");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ", 0, 9);

            switch (choice) {
                case 1:
                    busList.inputAndAddBusEnd();
                    break;
                case 2:
                    System.out.print("Enter Bus Code: ");
                    String bcode = sc.nextLine();
                    Bus bus = busList.searchByBcode(bcode);
                    if (bus != null) {
                        System.out.println(bus);
                    } else {
                        System.out.println("Bus not found.");
                    }
                    waitForEnter();
                    break;
                case 3:
                    deleteByBcode();
                    break;
                case 4:
                    busList.sortByBcode();
                    System.out.println("Buses sorted by Bcode.");
                    waitForEnter();
                    break;
                case 5:
                    busList.inputAndAddBusBeginning();
                    break;
                case 6:
                    busList.addAfterPosition();
                    break;
                case 7:
                    int pos1 = getIntInput("Enter position to delete: ", 1, Integer.MAX_VALUE);
                    busList.deleteAtPosition(pos1);
                    waitForEnter();
                    break;
                case 8:
                    System.out.println("Enter Bus Code to search for booked seats: ");
                    String bs = sc.nextLine();
                    busList.searchBookedByBcode(bs, bookingList, passengerList);
                    waitForEnter();
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
                    System.out.print("Enter Passenger Code: ");
                    String pcode = sc.nextLine();
                    Passenger passenger = passengerList.searchByPcode(pcode);
                    if (passenger != null) {
                        System.out.println(passenger);
                    } else {
                        System.out.println("Passenger not found.");
                    }
                    waitForEnter();
                    break;
                case 3:
                    deleteByPcode();
                    break;
                case 4:
                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();
                    Passenger passengerName = passengerList.searchByName(name);
                    if (passengerName != null) {
                        System.out.println(passengerName);
                    } else {
                        System.out.println("Passenger not found.");
                    }
                    waitForEnter();
                    break;
                case 5:
                    System.out.print("Enter Passenger Code: ");
                    String pcodeToSearchBus = sc.nextLine();
                    Bus foundBus = passengerList.searchBusByPcode(pcodeToSearchBus, bookingList, busList);
                    if (foundBus != null) {
                        System.out.println("Found bus: " + foundBus);
                    } else {
                        System.out.println("No bus found for passenger with code " + pcodeToSearchBus + ".");
                    }
                    waitForEnter();
                    break;
                case 0:
                    passengerList.saveToFile();
                    return;
            }
            passengerList.saveToFile();
        }
    }

    private static void deleteByBcode() {
        System.out.print("Enter the bcode of the bus to delete: ");
        String bcode = sc.nextLine();

        if (busList.deleteByBcode(bcode, bookingList)) {
            System.out.println("Bus and related bookings deleted successfully.");
        } else {
            System.out.println("Bus not found.");
        }
        waitForEnter();
    }

    private static void searchBookedByBcode() {
        System.out.print("Enter the bcode to search: ");
        String bcode = sc.nextLine();

        busList.searchBookedByBcode(bcode, bookingList, passengerList);
        waitForEnter();
    }

    private static void deleteByPcode() {
        System.out.print("Enter the pcode of the passenger to delete: ");
        String pcode = sc.nextLine();

        if (passengerList.deleteByPcode(pcode, bookingList)) {
            System.out.println("Passenger and related bookings deleted successfully.");
        } else {
            System.out.println("Passenger not found.");
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
                sc.nextLine();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}