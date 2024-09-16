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
            String supplier, double alcoholContent,
            String isCarbonated, int volume) {
        super(stockID, name, quantity, price, supplier);
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
        Display.displayStock(num);
    }
    
    public static StockAddBeverage stockIn(int num) {
    
        Scanner scanner = new Scanner(System.in);

        String stockID = StockIDGenerator.generateBeverageID();
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
        
        return new StockAddBeverage(stockID, name, quantity, price, supplier, 0.0, "no", 0);
        
    }
}
