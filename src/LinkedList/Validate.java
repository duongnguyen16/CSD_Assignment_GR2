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
public class Validate{
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

    public int getValidNumber(Scanner scanner, String message) {
        int number = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(message);
                number = scanner.nextInt();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println(message + " must be digit.");
                scanner.next();
            }
        }
        return number;
    }

    public double getValidTime(Scanner scanner, String message) {
        double time = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(message);
                time = scanner.nextDouble();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid time. Please enter again");
                scanner.next();
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
