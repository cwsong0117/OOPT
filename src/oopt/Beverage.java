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
public class Beverage extends Stock {

    private double alcoholContent;
    private String isCarbonated;
    private int volume;

    public Beverage() {
        super();
        this.alcoholContent = alcoholContent;
        this.isCarbonated = isCarbonated;
        this.volume = volume;
    }

    public Beverage(String stockID, String name, int quantity, double price,
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
        return String.format("%s %-11s %-10s %-15d",
                super.toString(), // Calls the toString() from Stock
                alcoholContent, isCarbonated, volume);
    }
    
    public static void beverageIn() {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
                        
            Stock stock = stockIn();
            File file = new File();
                
            System.out.print("Alcohol Content(%) : ");
            double alcoholContent = scanner.nextDouble();
            
            System.out.print("Carbonated?(yes/no) : ");
            String isCarbonated = scanner.nextLine().toLowerCase();
            while (!isCarbonated.equals("yes") && !isCarbonated.equals("no")) {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
                isCarbonated = scanner.nextLine().toLowerCase();
            }
            
            System.out.print("Volume : ");
            int volume = scanner.nextInt();
            
            scanner.nextLine();
            
            Beverage beverage = new Beverage(stock.getStockID(), stock.getName(), stock.getQuantity(),
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
}
