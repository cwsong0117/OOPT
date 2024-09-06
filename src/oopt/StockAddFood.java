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
    
    public static void foodIn(int num) {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
                        
            Stock stock = stockIn(num);
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
        Display.displayStock(num);
    }
    
    public static StockAddFood stockIn(int num) {
    
        Scanner scanner = new Scanner(System.in);

        String stockID = StockIDGenerator.generateFoodID();
        System.out.print("Stock ID : " + stockID + "\n");
        
        String name;
        do{
            System.out.print("Name : ");
            name = scanner.nextLine();
            if(name.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isValidName(name)) {
                System.out.println("Invalid Name format. Please try again.");
            }
            else if(!Validation.isValidName(name, num)) {
                System.out.println("Name already exists. Please select UPDATE or enter other name.");
            }
            else if(!Validation.isNotNullOrEmpty(name)) {
                System.out.println("Name cannot be empty. Please enter a name.");
            }
        }while(!Validation.isValidName(name, num) || !Validation.isValidName(name));

        int quantity = 0;
        do{
            System.out.print("Quantity : ");
            quantity = scanner.nextInt();
            if (quantity == -1) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
            }
            if(quantity <= 0) {
                System.out.println("The price cannot be zero or negative. Please enter a price");
            }
        }while(quantity <= 0);
        
        scanner.nextLine();

        String priceInput;
        double price = 0.0;
        do{
            System.out.print("Price : ");
            priceInput = scanner.nextLine();
            if(priceInput.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isNotNullOrEmpty(priceInput)) {
                System.out.println("Price cannot be empty.");
            }else{
                try{
                    price = Double.parseDouble(priceInput);
                    if(price <= 0.0) {
                        System.out.println("Price cannot be zero or negative.");
                    }
                }catch(NumberFormatException e) {
                    System.out.println("Invald price format. Please enter a valid price.");
                }
            }
        }while(price <= 0);
        
        String supplier;
        do{
            System.out.println("Supplier : ");
            supplier = scanner.nextLine();
            if(supplier.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isNotNullOrEmpty(supplier)) {
                System.out.println("Supplier cannot be empty. Please enter a supplier.");
            }
        }while(!Validation.isNotNullOrEmpty(supplier));
        
        String arrivalDate = Validation.getLocalDate();
        System.out.print("Arrival Date : " + arrivalDate + "\n");

        String expiryDate;
        do {
            System.out.print("Expiry Date(YYYY-MM-DD) : ");
            expiryDate = scanner.nextLine();
            if(expiryDate.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isValidDate(expiryDate)) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }while(!Validation.isValidDate(expiryDate));
        
        String location;
        do{
            System.out.print("Location : ");
            location = scanner.nextLine();
            if(location.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isValidLocation(location)) {
                System.out.println("Invalid location format. Please enter again.");
                System.out.println("Example: A001");
            }
        }while(!Validation.isValidLocation(location));
        
        return new StockAddFood(stockID, name, quantity, price, supplier, expiryDate, arrivalDate, location, "no", "", 0.0);
        
    }
}
