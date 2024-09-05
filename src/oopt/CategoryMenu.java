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
public class CategoryMenu {
    public static void addStockMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
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
                    AddFood.foodIn();
                    break;
                case 2:
                    System.out.println("Beverage Category Selected...");
                    AddBeverage.beverageIn();
                    break;
                case 3:
                    System.out.println("Ingredient Category Selected...");
                    AddIngredient.ingredientIn();
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
        Remove r = new Remove();
        
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
        Search s = new Search();
        
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
        Update u = new Update();
        
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
        Alert a = new Alert();
        
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
