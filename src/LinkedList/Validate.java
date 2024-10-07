/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import BBSList.BusList;
import LinkedList.MyList;
import BBSmanager.Bus;
import BBSmanager.Passenger;
import java.util.Scanner;
import BBSmanager.Booking;
import BBSmanager.Passenger;
import LinkedList.Node;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Validate extends MyList {
    private final static Scanner in = new Scanner(System.in);

    // check user input string
    public String checkName() {
        String name = in.nextLine().trim();
        while (true) {
            if (name.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
                name = in.nextLine().trim();
                continue;
            }
            if (name.matches(".*\\d.*")) {
                System.out.println("Name cannot contain numbers. Please enter again.");
                name = in.nextLine().trim();
                continue;
            } else {
                return name;
            }
        }
    }

    public long checkInputLong() {
        while (true) {
            try {
                long result = Integer.parseInt(in.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number integer");
                System.out.print("Enter again: ");
            }
        }
    }

    public String searchPcode() {
        String name = in.nextLine().trim();
        while (true) {
            if (name.isEmpty()) {
                System.out.println("Passenger code cannot be empty. Please enter again:");
                name = in.nextLine().trim();
                continue;
            }

            if (!name.matches("^P00\\d+$")) {
                System.out.println(
                        "The passenger code should be in format P00X (where X is a digit). Please enter again:");
                name = in.nextLine().trim();
                continue;
            }
            for (Node<Passenger> current = this.getHead(); current != null; current = current.next) {
                if (current.info.getPcode().equalsIgnoreCase(name)) {
                    System.out.println("The Passenger code already exists. Please enter another:");
                    name = in.nextLine().trim();
                    continue;
                }
            }
            return name;
        }
    }

    public String checkPhone() {
        String phone = in.nextLine().trim();
        while (true) {
            if (phone.isEmpty()) {
                System.out.println("Phone number cannot be empty. Please enter again:");
                phone = in.nextLine().trim();
                continue;
            }
            if (!phone.matches("\\d+")) {
                System.out.println("The phone number should contain only digits. Please enter again:");
                phone = in.nextLine().trim();
                continue;
            }
            for (Node<Passenger> current = this.getHead(); current != null; current = current.next) {
                if (current.info.getPhone().equals(phone)) {
                    System.out.println("The phone number already exists. Please enter another:");
                    phone = in.nextLine().trim();
                    continue;
                }
            }
            return phone;
        }
    }

    public String searchBcode() {
        String name = in.nextLine().trim();
        while (true) {
            if (name.isEmpty()) {
                System.out.println("Bus code cannot be empty. Please enter again:");
                name = in.nextLine().trim();
                continue;
            }

            if (!name.matches("^B00\\d+$")) {
                System.out.println("The bus code should be in format B00X (where X is a digit). Please enter again:");
                name = in.nextLine().trim();
                continue;
            }
            for (Node<Bus> current = this.getHead(); current != null; current = current.next) {
                if (current.info.getBcode().equalsIgnoreCase(name)) {
                    System.out.println("The Bus code already exists. Please enter another:");
                    name = in.nextLine().trim();
                    continue;
                }
            }
            return name;
        }
    }

    // Check time(dtime,atime)
    public int getValidNumber(Scanner scanner, String message) {
        int number = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(message);
                number = scanner.nextInt();
                scanner.nextLine();
                valid = true; // Nếu nhập đúng, thoát khỏi vòng lặp
            } catch (InputMismatchException e) {
                System.out.println(message + " must be digit.");
                scanner.next(); // Loại bỏ đầu vào không hợp lệ
            }
        }
        return number;
    }

    // Check time(dtime,atime)
    public double getValidTime(Scanner scanner, String message) {
        double time = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(message);
                time = scanner.nextDouble();
                scanner.nextLine();
                valid = true; // Nếu nhập đúng, thoát khỏi vòng lặp
            } catch (InputMismatchException e) {
                System.out.println("Invalid time. Please enter again");
                scanner.next(); // Loại bỏ đầu vào không hợp lệ
            }
        }
        return time;
    }
}
