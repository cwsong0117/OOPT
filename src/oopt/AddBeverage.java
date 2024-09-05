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
public class AddBeverage extends Stock {

    private double alcoholContent;
    private String isCarbonated;
    private int volume;

    public AddBeverage() {
        super();
        this.alcoholContent = alcoholContent;
        this.isCarbonated = isCarbonated;
        this.volume = volume;
    }

    public AddBeverage(String stockID, String name, int quantity, double price,
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
    
    public static void beverageIn() {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
                        
            Stock stock = stockIn();
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
            
            AddBeverage beverage = new AddBeverage(stock.getStockID(), stock.getName(), stock.getQuantity(),
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
        StockDisplay.displayBeverage();
    }
    
    public static Stock stockIn() {
    
        Scanner scanner = new Scanner(System.in);

        String beverageID = StockIDGenerator.generateBeverageID();
        System.out.print("Stock ID : " + beverageID + "\n");
        
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
            System.out.print("Expiry Date : ");
            expiryDate = scanner.nextLine();
            if(!Validation.isValidDate(expiryDate)) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }while(!Validation.isValidDate(expiryDate));
        
        System.out.print("Location : ");
        String location = scanner.nextLine();
        
        return new Stock(beverageID, name, quantity, price, supplier, expiryDate, arrivalDate, location);
        
    }
}
