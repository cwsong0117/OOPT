/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class StockMenu {
    public static void stockMenu() {
    
        Boolean running = true;
        Scanner scanner = new Scanner(System.in);
        StockCategoryMenu c = new StockCategoryMenu();
        
        while(running) {
            System.out.println("1: ADD Stock");
            System.out.println("2. DISPLAY Stock");
            System.out.println("3. REMOVE Stock");
            System.out.println("4. SEARCH Stock");
            System.out.println("5. LOW-LEVEL Stock Check");
            System.out.println("6. EXIT");
            System.out.print("Your Option : ");
            int option = scanner.nextInt();
            System.out.print("\n");
            
            switch(option) {

                case 1:
                    System.out.print("Add Stock Option Selected...\n");
                    c.addStockMenu();
                    break;
                case 2:
                    System.out.print("Display Stock Option Selected...\n");
                    c.displayStockMenu();
                    break;
                case 3:
                    System.out.print("Remove Stock Level Option Selected...\n");
                    c.removeStockMenu();
                    break;
                case 4:
                    System.out.print("Search Stock Level Option Selected...\n");
                    c.searchStockMenu();
                    break;
                case 5:
                    System.out.print("Stock Level Checking...\n");
                    c.stockBalanceAlert();
                case 6:
                    System.out.print("Exiting...\n");
                    running = false;
                    break;
                default:
                    System.out.print("Invalid Option...\n");
                    break;
            }
            return;
        }
        scanner.close();
    }
}
