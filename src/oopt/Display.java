/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

/**
 *
 * @author User
 */
public class Display {
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
        System.out.println("\n\n");
        stockMenu.stockMenu();
    }
    
    
    public static void displayBeverage() {
        File file = new File();
        Beverage[] beverages = file.readBeverage();
        StockMenu stockMenu = new StockMenu();

        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                          "ID", "Name", "Quantity", "Price", "Supplier", 
                          "Expiry Date", "Arrival Date", "Location", 
                          "Alcohol Content", "Carbonated", "Volume");
    
        for (Beverage b : beverages) {
            System.out.println(b.toString());
        }
        System.out.println("\n\n");
        stockMenu.stockMenu();
    }
    
    public static void displayIngredient() {
        File file = new File();
        Ingredient[] ingredients = file.readIngredient();
        StockMenu stockMenu = new StockMenu();

        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                      "ID", "Name", "Quantity", "Price", "Supplier", 
                      "Expiry Date", "Arrival Date", "Location", 
                      "Ingredient Type", "Gluten", "Calory");
    
        for (Ingredient i : ingredients) {
            System.out.println(i.toString());
        }
        System.out.println("\n\n");
        stockMenu.stockMenu();
    }
}
