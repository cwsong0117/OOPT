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
    
    private static final AtomicInteger FOOD_COUNTER = new AtomicInteger(0);
    private static final AtomicInteger BEVERAGE_COUNTER = new AtomicInteger(0);
    private static final AtomicInteger INGREDIENT_COUNTER = new AtomicInteger(0);
    
    public Stock() {
        this.stockID = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
        this.supplier = "";
    }
    
    public Stock(String stockID, String name, int quantity, double price, 
            String supplier) {
        this.stockID = stockID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
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
    
    @Override
    public String toString() {
        return String.format("%-7s %-12s %-9d %-7.2f %-14s", 
                    stockID, name, quantity, price, 
                    supplier);
    }
}
