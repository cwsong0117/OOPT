/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import static oopt.Food.displayFood;
import static oopt.Food.foodIn;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class StockMenu {
    public static void foodMenu() {
    
        Boolean running = true;
        Scanner scanner = new Scanner(System.in);
        
        while(running) {
            System.out.println("1: ADD Stock");
            System.out.println("2. DISPLAY Stock");
            System.out.println("3. REMOVE Stock");
            System.out.println("4. SEARCH Stock");
            System.out.println("5. SHIP Stock");
            System.out.println("6. LOW-LEVEL Stock Check");
            System.out.println("7. EXIT");
            System.out.print("Your Option : ");
            int option = scanner.nextInt();
            System.out.println("\n");
            
            switch(option) {

                case 1:
                    System.out.println("Add Stock Option Selected...\n");
                    foodIn();
                    break;
                case 2:
                    System.out.println("Display Stock Option Selected...\n");
                    displayFood();
                    break;
                case 3:
                    System.out.println("Remove Stock Level Option Selected...\n");
                    Remove.removeFood();
                    break;
                case 4:
                    System.out.println("Search Stock Level Option Selected...\n");
                    Search.searchFoodMenu();
                    break;
                case 5:
                    System.out.println("Ship Stock Option Selected...\n");
                    break;
                case 6:
                    System.out.println("Stock Level Checking...\n");
                case 7:
                    System.out.println("Exiting...\n");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...\n");
                    break;
            }
            return;
        }
        scanner.close();
    }
}
