/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import BBSList.BusList;
import BBSList.PassengerList;
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
    private final Scanner in = new Scanner(System.in);

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

    // check user input int
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

    public String searchPcode(PassengerList pas) {
        while (true) {
            String name = in.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Passenger code cannot be empty. Please enter again:");
                continue;
            }
            if (!name.matches("^P\\d+$")) {
                System.out.println(
                        "The passenger code should be in format P00X (where X is a digit). Please enter again:");
                continue;
            }
            boolean exists = false;
            for (Node<Passenger> current = pas.getHead(); current != null; current = current.next) {
                if (current.info.getPcode().equals(name)) {
                    System.out.println("The Passenger code already exists. Please enter another:");
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                return name;
            }
        }
    }

    public String checkPhone(PassengerList pas) {
        while (true) {
            String phone = in.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Phone number cannot be empty. Please enter again:");
                continue;
            }
            if (!phone.matches("\\d+")) {
                System.out.println("The phone number should contain only digits. Please enter again:");
                continue;
            }
            boolean exists = false;
            for (Node<Passenger> current = pas.getHead(); current != null; current = current.next) {
                if (current.info.getPhone().equals(phone)) {
                    System.out.println("The Passenger phone already exists. Please enter another:");
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                return phone;
            }
        }
    }

    public String searchBcode(BusList bus) {
        while (true) {
            String name = in.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Bus code cannot be empty. Please enter again:");
                continue;
            }

            if (!name.matches("^B00\\d+$")) {
                System.out.println("The bus code should be in format B00X (where X is a digit). Please enter again:");
                continue;
            }
            boolean exists = false;
            for (Node<Bus> current = bus.getHead(); current != null; current = current.next) {
                if (current.info.getBcode().equals(name)) {
                    System.out.println("The Bus code already exists. Please enter another:");
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                return name;
            }
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

    public int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result <= min || result >= max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public double checkInputDoubleLimit(double min, double max) {
        while (true) {
            try {
                double result = Double.parseDouble(in.nextLine().trim());
                if (result <= min || result >= max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    // check user input string
    public String checkInputString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    // check user input int
    public int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number integer");
                System.out.print("Enter again: ");
            }
        }
    }

    public boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

}
