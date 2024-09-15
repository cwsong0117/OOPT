/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author User
 */
public abstract class Stock {
    
    private String stockID;
    private String name;
    private int quantity;
    private double price;
    private String supplier;
    private String expiryDate;
    private String arrivalDate;
    private String location;
    
    private static final AtomicInteger FOOD_COUNTER = new AtomicInteger(0);
    private static final AtomicInteger BEVERAGE_COUNTER = new AtomicInteger(0);
    private static final AtomicInteger INGREDIENT_COUNTER = new AtomicInteger(0);
    
    public Stock() {
        this.stockID = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
        this.supplier = "";
        this.expiryDate = "";
        this.arrivalDate = "";
        this.location = "";
    }
    
    public Stock(String stockID, String name, int quantity, double price, 
            String supplier, String expiryDate, 
            String arrivalDate, String location) {
        this.stockID = stockID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.expiryDate = expiryDate;
        this.arrivalDate = arrivalDate;
        this.location = location;
    }
    
    public String getStockID() {
        return stockID;
    }
    
    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getSupplier() {
        return supplier;
    }
        
    public String getExpiryDate() {
        return expiryDate;
    }
    
    public String getArrivalDate() {
        return arrivalDate;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setStockID(String stockID) {
        this.stockID = stockID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
        
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        return String.format("%-7s %-12s %-9d %-7.2f %-14s %-23s %-14s %-15s", 
                    stockID, name, quantity, price, 
                    supplier, expiryDate, 
                    arrivalDate, location);
    }
    
    public static void displayLowStock(int n) {
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Item(s) will be displayed where stock balance is lower than the quantity entered.");
        System.out.print("Enter The Quantity: ");
        int lowBalance = scanner.nextInt();
        
        if(n == 1) {
        //food low balance display
            List<StockAddFood> lowStockItems = getFoodBalance(lowBalance);
            
            if(lowStockItems.isEmpty()) {
                System.out.println("No items with low stock...\n");
            }else {
                System.out.println("Items with stock less than " + lowBalance + " : ");
                Display.displayFoodTitle();
                for(StockAddFood f : lowStockItems) {
                    System.out.println(f.toString());
                }
            }
        }
        else if(n == 2) {
        //beverage low balance display
            List<StockAddBeverage> lowStockItems = getBeverageBalance(lowBalance);

                if(lowStockItems.isEmpty()) {
                    System.out.println("No items with low stock...\n");
                }else {
                    System.out.println("Items with stock less than " + lowBalance + " : ");
                    Display.displayFoodTitle();
                    for(StockAddBeverage b : lowStockItems) {
                        System.out.println(b.toString());
                    }
                }
            }
        else {
        //ingredient low balance display
            List<StockAddIngredient> lowStockItems = getIngredientBalance(lowBalance);
            
            if(lowStockItems.isEmpty()) {
                System.out.println("No items with low stock...\n");
            }else {
                System.out.println("Items with stock less than " + lowBalance + " : ");
                Display.displayFoodTitle();
                for(StockAddIngredient i : lowStockItems) {
                    System.out.println(i.toString());
                }
            }
        }
    }
    
    private static List<StockAddFood> getFoodBalance(int quantity) {
        List<StockAddFood> lowStockItems = new ArrayList<>();
        StockAddFood[] Foods = StockFile.readFood();

        for(StockAddFood f : Foods) {
            if(f.getQuantity() <= quantity) {
                lowStockItems.add(f);
            }
        }
        return lowStockItems;
    }
    
    private static List<StockAddBeverage> getBeverageBalance(int quantity) {
        List<StockAddBeverage> lowStockItems = new ArrayList<>();
        StockAddBeverage[] Beverages = StockFile.readBeverage();

        for(StockAddBeverage b : Beverages) {
            if(b.getQuantity() <= quantity) {
                lowStockItems.add(b);
            }
        }
        return lowStockItems;
    }
    
    private static List<StockAddIngredient> getIngredientBalance(int quantity) {
        List<StockAddIngredient> lowStockItems = new ArrayList<>();
        StockAddIngredient[] Ingredients = StockFile.readIngredient();

        for(StockAddIngredient i : Ingredients) {
            if(i.getQuantity() <= quantity) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }
}
