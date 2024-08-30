/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import static oopt.Food.operationAfterSearch;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Search {
    public static void searchFoodMenu() {
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        StockMenu stockMenu = new StockMenu();
        
        while(running) {
        
            System.out.println("Search By :");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Supplier");
            System.out.println("4. Expiry Date");
            System.out.println("5. Arrival Date");
            System.out.println("6. Location");
            System.out.println("7. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            
            System.out.printf("\n\n");
        
            switch(option) {
            
                case 1:
                    searchByID();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    searchBySupplier();
                    break;
                case 4:
                    searchByExpiryDate();
                    break;
                case 5:
                    searchByArrivalDate();
                    break;
                case 6:
                    searchByLocation();
                    break;
                case 7:
                    System.out.println("Exiting...\n\n");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option...Please enter again...\n\n");
                    break;
            }
        }
        stockMenu.foodMenu();
    }
    
    public static void searchByID() {
    
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.println("Enter the ID : ");
        String id = scanner.nextLine();
        
        for(Food f : foods) {
            if(f.getStockID().equalsIgnoreCase(id)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No Such ID in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchByName() {
    
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.println("Enter the Name : ");
        String name = scanner.nextLine();
        
        for(Food f : foods) {
            if(f.getName().equalsIgnoreCase(name)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No Such Name in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchBySupplier() {
        
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.println("Enter the Supplier : ");
        String supplier = scanner.nextLine();
        
        for(Food f : foods) {
            if(f.getSupplier().equalsIgnoreCase(supplier)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No Such Supplier in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchByExpiryDate() {
    
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.println("Enter the Expiry Date : ");
        String expiryDate = scanner.nextLine();
        
        for(Food f : foods) {
            if(f.getExpiryDate().equalsIgnoreCase(expiryDate)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No Such Expiry Date in file.\n");
        }
        operationAfterSearch();
    }
        
    public static void searchByArrivalDate() {
        
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.println("Enter the Arrival Date : ");
        String arrivalDate = scanner.nextLine();
        
        for(Food f : foods) {
            if(f.getArrivalDate().equalsIgnoreCase(arrivalDate)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No Such Arrival Date in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchByLocation() {
        
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.println("Enter the Location : ");
        String location = scanner.nextLine();
        
        for(Food f : foods) {
            if(f.getLocation().equalsIgnoreCase(location)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No Such Location in file.\n");
        }
        operationAfterSearch();
    }
}
