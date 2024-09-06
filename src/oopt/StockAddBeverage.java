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
public class StockAddBeverage extends Stock {

    private double alcoholContent;
    private String isCarbonated;
    private int volume;

    public StockAddBeverage() {
        super();
        this.alcoholContent = alcoholContent;
        this.isCarbonated = isCarbonated;
        this.volume = volume;
    }

    public StockAddBeverage(String stockID, String name, int quantity, double price,
            String supplier, String expiryDate,
            String arrivalDate, String location, double alcoholContent,
            String isCarbonated, int volume) {
        super(stockID, name, quantity, price, supplier, expiryDate,
                arrivalDate, location);
        this.alcoholContent = alcoholContent;
        this.isCarbonated = isCarbonated;
        this.volume = volume;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public String getIsCarbonated() {
        return isCarbonated;
    }

    public int getVolume() {
        return volume;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public void setIsCarbonated(String isCarbonated) {
        this.isCarbonated = isCarbonated;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("%s %-15s %-10s %-15d",
                super.toString(), // Calls the toString() from Stock
                alcoholContent, isCarbonated, volume);
    }
    
    public static void beverageIn(int num) {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
            
            Stock stock = stockIn(num);
            StockFile file = new StockFile();
                
            System.out.print("Alcohol Content(%) : ");
            double alcoholContent = scanner.nextDouble();
            
            scanner.nextLine();
            
            System.out.print("Carbonated?(yes/no) : ");
            String isCarbonated = scanner.nextLine().toLowerCase();
            while (!isCarbonated.equals("yes") && !isCarbonated.equals("no")) {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
                isCarbonated = scanner.nextLine().toLowerCase();
            }
            
            System.out.print("Volume : ");
            int volume = scanner.nextInt();
            
            scanner.nextLine();
            
            StockAddBeverage beverage = new StockAddBeverage(stock.getStockID(), stock.getName(), stock.getQuantity(),
                                stock.getPrice(), stock.getSupplier(),
                                stock.getExpiryDate(), stock.getArrivalDate() ,stock.getLocation(),
                                alcoholContent, isCarbonated, volume);
            
            file.appendBeverage(beverage);
            
            System.out.print("\n");
            System.out.println("You can press any key to stop.");
            System.out.print("Continue?('y' to yes) : ");
            String response = scanner.nextLine();
            
            if(!response.equalsIgnoreCase("y")) {
                running = false;
            }
        }
        System.out.println("\n");
        Display.displayBeverage();
    }
    
    public static StockAddBeverage stockIn(int num) {
    
        Scanner scanner = new Scanner(System.in);

        String beverageID = StockIDGenerator.generateBeverageID();
        System.out.print("Stock ID : " + beverageID + "\n");
        
        String name;
        do{
            System.out.print("Name : ");
            name = scanner.nextLine();
            if(!Validation.isValidName(name, num)) {
                System.out.println("Name already exists. Please select UPDATE or enter other name.");
            }
        }while(!Validation.isValidName(name, num));

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
        
        return new StockAddBeverage(beverageID, name, quantity, price, supplier, expiryDate, arrivalDate, location, 0.0, "no", 0);
        
    }
}
