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

    public static void PurchaseOrderMenu() {
        boolean valid = false;
        int choice = 0;
        PurchaseOrder po = new PurchaseOrder();

        System.out.println("\n\t\t*****Welcome to Staff, You can make an Order here for*****");
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
                    System.exit(0);
                }
            }
        } while (!valid);

        switch (choice) {
            case 1:
                po.displayAllpo();
                break;
            case 2:
                po.addPurchaseOrderMenu();
                break;
            case 3:
                po.cancelPurchaseOrder();
                break;
            default:
                System.exit(0);
        }

    }
}
