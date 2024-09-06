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
public class StockAddFood extends Stock{
    
    private String isOrganic;
    private String allergens;
    private double storageTemperature;
    
    public StockAddFood() {
        super();
        this.isOrganic = "";
        this.allergens = "";
        this.storageTemperature = 0.0;
    }
    
    public StockAddFood(String stockID, String name, int quantity, double price, 
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
    
    public static void foodIn() {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
                        
            Stock stock = stockIn();
            StockFile file = new StockFile();
                
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
            
            StockAddFood food = new StockAddFood(stock.getStockID(), stock.getName(), stock.getQuantity(),
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
        Display.displayFood();
    }
    
    public static Stock stockIn() {
    
        Scanner scanner = new Scanner(System.in);
        
        String foodID = StockIDGenerator.generateFoodID();
        System.out.print("Stock ID : " + foodID + "\n");
        
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

        String arrivalDate = Validation.getLocalDate();
        System.out.print("Arrival Date : " + arrivalDate + "\n");
        
        String expiryDate;
        do {
            System.out.print("Expiry Date(YYYY-MM-DD) : ");
            expiryDate = scanner.nextLine();
            if(!Validation.isValidDate(expiryDate)) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }while(!Validation.isValidDate(expiryDate));
        
        String location;
        do{
            System.out.print("Location : ");
            location = scanner.nextLine();
            if(!Validation.isValidLocation(location)) {
                System.out.println("Invalid location format. Please enter again.");
                System.out.println("Example: A001");
            }
        }while(!Validation.isValidLocation(location));
        
        return new Stock(foodID, name, quantity, price, supplier, expiryDate, arrivalDate, location);
        
    }
}
