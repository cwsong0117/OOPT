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
public class SupplierItemsMain {
    public static void SupplierItemsMenu(){
        boolean valid = false;
        int choice = 0;
        System.out.println("\n\t\t******Welcome to Supplier's Items Module******");
        System.out.println("\t\t\t1.Display supplier's items");
        System.out.println("\t\t\t2.Add supplier's item(s)");
        System.out.println("\t\t\t3.Remove supplier's item(s)");
        System.out.println("\t\t\tAny number to go back to Supplier Module");
        
        do{
        System.out.print("Choice: ");
        
            Scanner input = new Scanner(System.in);
            try {
                choice = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("\nInvalid input. Please enter a numeric value.");
                System.out.println("**Try again(XXX to leave)**");
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
                    SupplierItems.displayAllItems();
                    break;
                case 2:
                    SupplierItems.addSupplierItems();
                    break;
                case 3:
                    SupplierItems.removeSupplierItem();
                    break;
                default:
                    SupplierMain.SupplierMenu();
            }

    }
    }
}
