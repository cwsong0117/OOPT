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
public class SupplierMain {

    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    public static void SupplierMenu() {
        boolean valid = false;
        int choice = 0;

        System.out.println(PURPLE + "\t\t ____  _   _ ____  ____  _     ___ _____ ____  " + RESET);
        System.out.println(PURPLE + "\t\t/ ___|| | | |  _ \\|  _ \\| |   |_ _| ____|  _ \\ " + RESET);
        System.out.println(PURPLE + "\t\t\\___ \\| | | | |_) | |_) | |    | ||  _| | |_) |" + RESET);
        System.out.println(PURPLE + "\t\t ___) | |_| |  __/|  __/| |___ | || |___|  _ < " + RESET);
        System.out.println(PURPLE + "\t\t|____/ \\___/|_|   |_|   |_____|___|_____|_| \\_\\" + RESET);

        System.out.println(BLUE + "\n\t\t******Welcome to Supplier Module******" + RESET);
        System.out.println("\t\t\t1.Display supplier");
        System.out.println("\t\t\t2.Add supplier");
        System.out.println("\t\t\t3.Modify supplier");
        System.out.println("\t\t\t4.Remove supplier");
        System.out.println("\t\t\t5.Manage Supplier Items/Products");
        System.out.println("\t\t\tAny number to leave here");

        do {
            System.out.print("Choice: ");

            Scanner input = new Scanner(System.in);
            try {
                choice = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("\nInvalid input. Please enter a numeric value.");
                System.out.println("**Try again(XXX to end program)**");
                String option = input.nextLine();
                if (option.equalsIgnoreCase("XXX")) {
                    System.exit(0);
                }
            }
        } while (!valid);

        if (valid) {
            switch (choice) {
                case 1:
                    System.out.println("\n");
                    Supplier.displayAllsupplier();
                    break;
                case 2:
                    Supplier.AddSupplier();
                    break;
                case 3:
                    Supplier.ModifySupplier();
                    break;
                case 4:
                    Supplier.RemoveSupplier();
                    break;
                case 5:
                    SupplierItemsMain.SupplierItemsMenu();
                    break;
                default:
                    return;
            }
        }
    }
}
