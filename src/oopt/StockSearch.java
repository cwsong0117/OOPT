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
public class StockSearch {
    
    public static final String RESET = "\u001B[0m";
    
    public static void searchMenu(int opt) {
        //opt represent the category as parameter that pass to this method
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        StockMenu stockMenu = new StockMenu();
        
        while(running) {
        
            System.out.println("Search By :");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Supplier");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            
            System.out.printf("\n\n");
        
            switch(option) {
            
                //pass the value into the method
                case 1:
                    searchByID(opt);
                    break;
                case 2:
                    searchByName(opt);
                    break;
                case 3:
                    searchBySupplier(opt);
                    break;
                case 4:
                    System.out.println("\033[0;31mExiting...\n\n" + RESET);
                    running = false;
                    break;
                default:
                    System.out.println("\033[0;31mInvalid option...Please enter again...\n" + RESET);
                    break;
            }
        }
        stockMenu.stockMenu();
    }
    
    public static String searchByID(int n) {
        //n means the number entered by user,food, beverage or ingredient
        //search and display the relevant id
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        Display d = new Display();
        
        System.out.print("Enter the ID : ");
        String id = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockFood f : foods) {
                if(f.getStockID().equalsIgnoreCase(id)) {
                    System.out.println(f.toString() + "\n");
                    found = true;
                }
            }
        }else if(n == 2) {
            StockBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockBeverage b : beverages) {
                if(b.getStockID().equalsIgnoreCase(id)) {
                    System.out.println(b.toString() + "\n");
                    found = true;
                }
            }
        }else if(n == 3) {
            StockIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockIngredient i : ingredients) {
                if(i.getStockID ().equalsIgnoreCase(id)) {
                    System.out.println(i.toString() + "\n");
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("\033[0;31mNo Such ID in file.\n" + RESET);
            operationAfterSearch();
        }
        return id;
    }
    
    public static void searchByName(int n) {
    
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Name : ");
        String name = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockFood f : foods) {
                if(f.getName().equalsIgnoreCase(name)) {
                    System.out.println(f.toString() + "\n");
                    found = true;
                }
            }
        }else if(n == 2) {
            StockBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockBeverage b : beverages) {
                if(b.getName().equalsIgnoreCase(name)) {
                    System.out.println(b.toString() + "\n");
                    found = true;
                }
            }
        }else if(n == 3) {
            StockIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockIngredient i : ingredients) {
                if(i.getName().equalsIgnoreCase(name)) {
                    System.out.println(i.toString() + "\n");
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("\033[0;31mNo Such Name in file.\n" + RESET);
        }
        operationAfterSearch();
    }
    
    public static void searchBySupplier(int n) {
        
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Supplier : ");
        String supplier = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockFood f : foods) {
                if(f.getSupplier().equalsIgnoreCase(supplier)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockBeverage b : beverages) {
                if(b.getSupplier().equalsIgnoreCase(supplier)) {
                    System.out.println(b.toString() + "\n");
                    found = true;
                }
            }
        }else if(n == 3) {
            StockIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockIngredient i : ingredients) {
                if(i.getSupplier().equalsIgnoreCase(supplier)) {
                    System.out.println(i.toString() + "\n");
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("\033[0;31mNo Such Supplier in file.\n" + RESET);
        }
        operationAfterSearch();
    }
    
    public static void operationAfterSearch() {
        
        Scanner scanner = new Scanner(System.in);
        StockMenu stockMenu = new StockMenu();
        
        System.out.println("\n==============================");
        System.out.println("1. Update Stock Details");
        System.out.println("2. Exit");
        System.out.println("==============================");
        System.out.print("Option > ");
        int option = scanner.nextInt();
        
        switch(option) {
            case 1:
                stockMenu.updateStockMenu();
                break;
            case 2:
                stockMenu.stockMenu();
                break;
            default:
                System.out.println("\033[0;31mInvalid Option!Please try again...\n" + RESET);
        }
    }
}
