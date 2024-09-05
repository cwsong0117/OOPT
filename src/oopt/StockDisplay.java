/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

/**
 *
 * @author User
 */
public class StockDisplay {
    
    public static void displayFoodTitle() {
        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                          "ID", "Name", "Quantity", "Price", "Supplier", 
                          "Expiry Date", "Arrival Date", "Location", 
                          "Organic", "Allergens", "Storage Temp");
    }
    
    public static void displayBeverageTitle() {
        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                          "ID", "Name", "Quantity", "Price", "Supplier", 
                          "Expiry Date", "Arrival Date", "Location", 
                          "Alcohol Content", "Carbonated", "Volume");
    }
    
    public static void displayIngredientTitle() {
        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                      "ID", "Name", "Quantity", "Price", "Supplier", 
                      "Expiry Date", "Arrival Date", "Location", 
                      "Ingredient Type", "Gluten", "Calory");
    }
        
    //the purpose of create those method is to ensure the reusability in other method
        
    public static void displayFood() {
        StockFile file = new StockFile();
        AddFood[] foods = file.readFood();
        StockMenu stockMenu = new StockMenu();
        
        displayFoodTitle();
    
        for (AddFood f : foods) {
            System.out.println(f.toString());
        }
        System.out.print("\n\n");
        stockMenu.stockMenu();
    }
    
    
    public static void displayBeverage() {
        StockFile file = new StockFile();
        AddBeverage[] beverages = file.readBeverage();
        StockMenu stockMenu = new StockMenu();

        displayBeverageTitle();
    
        for (AddBeverage b : beverages) {
            System.out.println(b.toString());
        }
        System.out.print("\n\n");
        stockMenu.stockMenu();
    }
    
    public static void displayIngredient() {
        StockFile file = new StockFile();
        AddIngredient[] ingredients = file.readIngredient();
        StockMenu stockMenu = new StockMenu();

        displayIngredientTitle();
    
        for (AddIngredient i : ingredients) {
            System.out.println(i.toString());
        }
        System.out.print("\n\n");
        stockMenu.stockMenu();
    }
}
