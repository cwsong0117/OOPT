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
    
    public static void displayFoodTitle() {
        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-11s %-10s %-15s\n", 
                          "ID", "Name", "Quantity", "Price", "Supplier", 
                          "Organic", "Allergens", "Storage Temp");
    }
    
    public static void displayBeverageTitle() {
        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-11s %-10s %-15s\n", 
                          "ID", "Name", "Quantity", "Price", "Supplier", 
                          "Alcohol Content", "Carbonated", "Volume");
    }
    
    public static void displayIngredientTitle() {
        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-11s %-10s %-15s\n", 
                      "ID", "Name", "Quantity", "Price", "Supplier",
                      "Ingredient Type", "Gluten", "Calory");
    }
        
    //the purpose of create those method is to ensure the reusability in other method
        
    public static void displayStock(int n) {
        
        StockFile file = new StockFile();
        StockMenu stockMenu = new StockMenu();
        
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            displayFoodTitle();
    
            for (StockAddFood f : foods) {
                System.out.println(f.toString());
            }
            System.out.print("\n\n");
            stockMenu.stockMenu();
        }
        else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            displayBeverageTitle();
    
            for (StockAddBeverage b : beverages) {
                System.out.println(b.toString());
            }
            System.out.print("\n\n");
            stockMenu.stockMenu();
        }
        else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            displayIngredientTitle();
    
            for (StockAddIngredient i : ingredients) {
                System.out.println(i.toString());
            }
            System.out.print("\n\n");
            stockMenu.stockMenu();
        } 
    }
}
