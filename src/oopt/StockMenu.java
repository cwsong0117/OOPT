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
public class StockMenu {
    public static final String RESET = "\u001B[0m";
    public static void stockMenu() {
    
        Boolean running = true;
        Scanner scanner = new Scanner(System.in);
        
        while(running) {
            System.out.println("\n");
            System.out.println("\033[34m  ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ " + RESET);
            System.out.println("\033[1;34m |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|" + RESET);
            System.out.println("\033[94;35m | |      / ____| |           | |           |  \\/  |                                                 | |       | |" + RESET);
            System.out.println("\033[34;35m | |     | (___ | |_ ___   ___| | __        | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_      | |" + RESET);
            System.out.println("\033[34;35m | |      \\___ \\| __/ _ \\ / __| |/ /        | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|     | |" + RESET);
            System.out.println("\033[94;35m | |      ____) | || (_) | (__|   <         | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_      | |" + RESET);
            System.out.println("\033[34;35m | |     |_____/ \\__\\___/ \\___|_|\\_\\        |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__|     | |" + RESET);
            System.out.println("\033[94;35m | |                                                                   __/ |                                   | |" + RESET);
            System.out.println("\033[1;34m |_|____ ______ ______ ______ ______ ______ ______ ______ ______ _____|___/___ ______ ______ ______ ______ ____|_|" + RESET);
            System.out.println("\033[34m |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|" + RESET);
            System.out.println("\n");
            System.out.println("\033[33mEnter '-1' to back to this menu." + RESET);
            System.out.println("====================================\n");
            System.out.println("1: ADD NEW Stock");
            System.out.println("2. UPDATE Stock Level");
            System.out.println("3. DISPLAY Stock");
            System.out.println("4. REMOVE Stock");
            System.out.println("5. SEARCH Stock");
            System.out.println("6. LOW-LEVEL Stock Check");
            System.out.println("7. EXIT");
            System.out.println("\n====================================");
            System.out.print("Your Option : ");
            try {
                int option = scanner.nextInt();  // Read input
                System.out.println("\n=====================================");

                switch(option) {
                    case 1:
                        System.out.print("\033[5;34mAdd Stock Option Selected...\n" + RESET);
                        System.out.println("=====================================\n");
                        addStockMenu();
                        break;
                    case 2:
                        System.out.println("\033[5;34mUpdate Stock Option Selected...\n" + RESET);
                        System.out.println("=====================================\n");
                        updateStockMenu();
                        break;
                    case 3:
                        System.out.print("\033[5;34mDisplay Stock Option Selected...\n" + RESET);
                        System.out.println("=====================================\n");
                        displayStockMenu();
                        break;
                    case 4:
                        System.out.print("\033[5;34mRemove Stock Level Option Selected...\n" + RESET);
                        System.out.println("=====================================\n");
                        removeStockMenu();
                        break;
                    case 5:
                        System.out.print("\033[5;34mSearch Stock Level Option Selected...\n" + RESET);
                        System.out.println("=====================================\n");
                        searchStockMenu();
                        break;
                    case 6:
                        System.out.print("\033[5;34mStock Level Checking...\n" + RESET);
                        System.out.println("=====================================\n");
                        stockBalanceAlert();
                        break;
                    case 7:
                        System.out.print("\033[0;31mExiting...\n\n" + RESET);
                        System.out.println("=====================================\n");
                        running = false;
                        break;
                    default:
                        System.out.print("\033[0;31mInvalid Option...\n" + RESET);
                        System.out.println("=====================================\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid option number.");
                scanner.next();  // Consume the invalid token to avoid infinite loop
            }
            return;
        }
        scanner.close();
    }
    
    public static void displayChoice() {
        System.out.println("\n==================");
        System.out.println("1. Food");
        System.out.println("2. Beverage");
        System.out.println("3. Iegredient");
        System.out.println("4. Exit");
        System.out.println("==================");
        System.out.print("Option > ");
    }
    
    public static void addStockMenu() {
        displayChoice();
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        int option;
        
        while(running) {
            option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                    System.out.println("\033[0;32mFood Category Selected...\n" + RESET);
                    StockFood.foodIn(option);
                    break;
                case 2:
                    System.out.println("\033[0;32mBeverage Category Selected...\n" + RESET);
                    StockBeverage.beverageIn(option);
                    break;
                case 3:
                    System.out.println("\033[0;32mIngredient Category Selected...\n" + RESET);
                    StockIngredient.ingredientIn(option);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting..." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid Option..." + RESET);
                    break;

            }
            stockMenu();;
        }
        scanner.close();
    }
    
    public static void displayStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        Display d = new Display();
        
        while(running) {
            displayChoice();
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                case 2:
                case 3:
                    d.displayStock(option);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting..." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid Option..." + RESET);
                    break;

            }
            stockMenu();;
        }
        scanner.close();
    }
    
    public static void removeStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockRemove r = new StockRemove();
        
        while(running) {
            displayChoice();
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                case 2:
                case 3:
                    r.removeStock(option);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting..." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid Option..." + RESET);
                    break;

            }
            stockMenu();;
        }
        scanner.close();
    }
    
    public static void searchStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockSearch s = new StockSearch();
        
        while(running) {
            displayChoice();
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                    System.out.println("\033[0;32mFood Category Selected...\n" + RESET);
                    s.searchMenu(option);
                    break;
                case 2:
                    System.out.println("\033[0;32mBeverage Category Selected...\n" + RESET);
                    s.searchMenu(option);
                    break;
                case 3:
                    System.out.println("\033[0;32mIngredient Category Selected...\n" + RESET);
                    s.searchMenu(option);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting..." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid Option..." + RESET);
                    break;

            }
            stockMenu();;
        }
        scanner.close();
    }
    
    public static void updateStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockUpdate u = new StockUpdate();
        
        while(running) {
            displayChoice();
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                case 2:
                case 3:
                    u.updateStock(option);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting..." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid Option..." + RESET);
                    break;

            }
            stockMenu();
        }
        scanner.close();
    }
    
    public static void stockBalanceAlert() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        StockAlert a = new StockAlert();
        
        while(running) {
            displayChoice();
            int option = scanner.nextInt();
            System.out.print("\n");

            switch(option) {
                case 1:
                case 2:
                case 3:
                    a.displayLowStock(option);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting..." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid Option..." + RESET);
                    break;

            }
            stockMenu();
        }
        scanner.close();
    }
}
