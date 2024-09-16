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
    public static void main(String[] args) {
        // Invoke the readTextFile method
        SupplierMenu();
    }
    
    public static void SupplierMenu(){
        boolean valid = false;
        int choice = 0;
        System.out.println("\n\t\t******Welcome to Supplier Module******");
        System.out.println("\t\t\t1.Display supplier");
        System.out.println("\t\t\t2.Add supplier");
        System.out.println("\t\t\t3.Modify supplier");
        System.out.println("\t\t\t4.Remove supplier");
        System.out.println("\t\t\t5.Add supplier's items");
        System.out.println("\t\t\tAny number to leave here");
        
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
                    SupplierStock.addSupplierItems();
                    break;
                default:
                    System.exit(0);
            }

        }
    }
}
