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
public class Food extends Stock{
    
    private String isOrganic;
    private String allergens;
    private double storageTemperature;
    
    public Food() {
        super();
        this.isOrganic = "";
        this.allergens = "";
        this.storageTemperature = 0.0;
    }
    
    public Food(String stockID, String name, int quantity, double price, 
            String supplier, String expiryDate, 
            String arrivalDate, String location, String isOrganic,
            String allergens, double storageTemperature) {
        super(stockID, name, quantity, price, supplier, expiryDate,
                arrivalDate, location);
        this.isOrganic = isOrganic;
        this.allergens = allergens;
        this.storageTemperature = storageTemperature;
    }
    
    public String getIsOrganic() {
        return isOrganic;
    }
    
    public String getAllergens() {
        return allergens;
    }
    
    public double getStorageTemperature() {
        return storageTemperature;
    }
    
    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }
    
    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }
    
    public void setStorageTemperature(double storageTemperature) {
        this.storageTemperature = storageTemperature;
    }
    
    @Override
    public String toString() {
        return String.format("%s %-11s %-10s %-15.2f",
                super.toString(), // Calls the toString() from Stock
                isOrganic, allergens, storageTemperature);
    }
    
    public static void displayFood() {
        File file = new File();
        Food[] foods = file.readFood();
        StockMenu stockMenu = new StockMenu();

        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                          "ID", "Name", "Quantity", "Price", "Supplier", 
                          "Expiry Date", "Arrival Date", "Location", 
                          "Organic", "Allergens", "Storage Temp");
    
        for (Food f : foods) {
            System.out.println(f.toString());
        }
        stockMenu.foodMenu();
    }
    
    public static void foodIn() {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
                        
            Stock stock = stockIn();
            File file = new File();
                
            System.out.print("Organic?(yes/no) : ");
            String isOrganic = scanner.nextLine().toLowerCase();
            while (!isOrganic.equals("yes") && !isOrganic.equals("no")) {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
                isOrganic = scanner.nextLine().toLowerCase();
            }
            
            System.out.print("Allergens : ");
            String allergens = scanner.nextLine();
            
            System.out.print("Storage Temperature : ");
            double storageTemperature = scanner.nextDouble();
            
            scanner.nextLine();
            
            Food food = new Food(stock.getStockID(), stock.getName(), stock.getQuantity(),
                                stock.getPrice(), stock.getSupplier(),
                                stock.getExpiryDate(), stock.getArrivalDate() ,stock.getLocation(),
                                isOrganic, allergens, storageTemperature);
            
            file.appendFood(food);
            
            System.out.print("\n");
            System.out.println("You can press any key to stop.");
            System.out.print("Continue?('y' to yes) : ");
            String response = scanner.nextLine();
            
            if(!response.equalsIgnoreCase("y")) {
                running = false;
            }
        }
        System.out.println("\n");
        displayFood();
    }
    
    public static Stock stockIn() {
    
        Scanner scanner = new Scanner(System.in);

        System.out.print("Stock ID : ");
        String stockID = scanner.nextLine();
        
        System.out.print("Name : ");
        String name = scanner.nextLine();

        System.out.print("Quantity : ");
        int quantity = scanner.nextInt();
        
        scanner.nextLine();

        System.out.print("Price : ");
        double price = scanner.nextDouble();
        
        scanner.nextLine();

        System.out.print("Supplier : ");
        String supplier = scanner.nextLine();

        System.out.print("Expiry Date : ");
        String expiryDate = scanner.nextLine();
        
        System.out.print("Arrival Date : ");
        String arrivalDate = scanner.nextLine();
        
        System.out.print("Location : ");
        String location = scanner.nextLine();
        
        return new Stock(stockID, name, quantity, price, supplier, expiryDate, arrivalDate, location);
        
    }
        
    public static void operationAfterSearch() {
        
        Scanner scanner = new Scanner(System.in);
        StockMenu stockMenu = new StockMenu();

        
        System.out.println("1. Update Stock Details");
        System.out.println("2. Remove Stock");
        System.out.println("3. Exit");
        System.out.print("Option > ");
        int option = scanner.nextInt();
        
        switch(option) {
            case 1:
                //updateStockDetails();
                break;
            case 2:
                Remove.removeFood();
                break;
            case 3:
                stockMenu.foodMenu();
                break;
        }
    }
    
}
