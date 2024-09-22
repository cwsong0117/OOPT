/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PurchaseOrderMain {

    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    public static void PurchaseOrderMenu() {
        boolean valid = false;
        int choice = 0;

        System.out.println(PURPLE + " \t ____                 _                       ___          _           " + RESET);
        System.out.println(PURPLE + "\t|  _ \\ _   _ _ __ ___| |__   __ _ ___  ___   / _ \\ _ __ __| | ___ _ __ " + RESET);
        System.out.println(PURPLE + "\t| |_) | | | | '__/ __| '_ \\ / _` / __|/ _ \\ | | | | '__/ _` |/ _ \\ '__|" + RESET);
        System.out.println(PURPLE + "\t|  __/| |_| | | | (__| | | | (_| \\__ \\  __/ | |_| | | | (_| |  __/ |   " + RESET);
        System.out.println(PURPLE + "\t|_|    \\__,_|_|  \\___|_| |_|\\__,_|___/\\___|  \\___/|_|  \\__,_|\\___|_|   " + RESET);

        System.out.println(BLUE + "\n\t\t*****Welcome Staff, You can make an Order here for*****" + RESET);
        System.out.println("\t\t\t1.Check current purchase");
        System.out.println("\t\t\t2.Make an order");
        System.out.println("\t\t\t3.Cancel purchase order");
        System.out.println("\t\t\tAny number to exit here");
        do {
            System.out.print("\nChoice: ");

            Scanner input = new Scanner(System.in);
            try {
                choice = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("INVALID INPUT. Please enter a numeric value.");
                System.out.print("Try again(XXX to leave): ");
                String option = input.nextLine();
                if (option.equalsIgnoreCase("XXX")) {
                    System.out.println("\nGoodbye.....");
                    StaffLogin.staffMenu();
                }
            }
        } while (!valid);

        switch (choice) {
            case 1:
                PurchaseOrder.displayAllpo();
                break;
            case 2:
                PurchaseOrder.addPurchaseOrderMenu();
                break;
            case 3:
                PurchaseOrder.cancelPurchaseOrder();
                break;
            default:
                return;
        }

    }
}
