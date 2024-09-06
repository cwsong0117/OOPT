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
                    addStockMenu();
                    break;
                case 2:
                    System.out.print("Display Stock Option Selected...\n");
                    displayStockMenu();
                    break;
                case 3:
                    System.out.print("Remove Stock Level Option Selected...\n");
                    removeStockMenu();
                    break;
                case 4:
                    System.out.print("Search Stock Level Option Selected...\n");
                    searchStockMenu();
                    break;
                case 5:
                    System.out.print("Stock Level Checking...\n");
                    stockBalanceAlert();
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
    
    public static void addStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        int option;
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                    System.out.println("Food Category Selected...");
                    StockAddFood.foodIn(option);
                    break;
                case 2:
                    System.out.println("Beverage Category Selected...");
                    StockAddBeverage.beverageIn(option);
                    break;
                case 3:
                    System.out.println("Ingredient Category Selected...");
                    StockAddIngredient.ingredientIn(option);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
    
    public static void displayStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        Display d = new Display();
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                    System.out.println("Food Category Selected...");
                    d.displayFood();
                    break;
                case 2:
                    System.out.println("Beverage Category Selected...");
                    d.displayBeverage();
                    break;
                case 3:
                    System.out.println("Ingredient Category Selected...");
                    d.displayIngredient();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
    
    public static void removeStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockRemove r = new StockRemove();
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                    System.out.println("Food Category Selected...");
                    r.removeFood();
                    break;
                case 2:
                    System.out.println("Beverage Category Selected...");
                    r.removeBeverage();
                    break;
                case 3:
                    System.out.println("Ingredient Category Selected...");
                    r.removeIngredient();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
    
    public static void searchStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockSearch s = new StockSearch();
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                    System.out.println("Food Category Selected...");
                    s.searchMenu(option);
                    break;
                case 2:
                    System.out.println("Beverage Category Selected...");
                    s.searchMenu(option);
                    break;
                case 3:
                    System.out.println("Ingredient Category Selected...");
                    s.searchMenu(option);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
    
    public static void updateStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockUpdate u = new StockUpdate();
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                case 2:
                case 3:
                    u.updateStock(option);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
    
    public static void stockBalanceAlert() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockAlert a = new StockAlert();
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                case 2:
                case 3:
                    a.displayLowStock(option);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
}
