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


public class StockUpdate {
    public static void updateStock(int n){
        
        Scanner scanner = new Scanner(System.in);
        StockSearch s = new StockSearch();
        
        //Search Stock that need to update using ID
        String id = s.searchByID(n);
        
        //Enter the amount of stock receive
        System.out.println("\nStock Received: ");
        int receivedStock = scanner.nextInt();
        
        StockFile file = new StockFile();
        boolean found = false;
            
        if (n == 1) { // Update stock level for food
            StockAddFood[] foods = file.readFood();
            for (StockAddFood f : foods) {
                if (f.getStockID().equalsIgnoreCase(id)) {
                    int newStock = f.getQuantity() + receivedStock; // Add received stock to current stock
                    f.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeFood(foods); // Save updated data back to file
                System.out.println("Stock level updated successfully.");
            }
        } else if (n == 2) { // Update stock level for beverage
            StockAddBeverage[] beverages = file.readBeverage();
            for (StockAddBeverage b : beverages) {
                if (b.getStockID().equalsIgnoreCase(id)) {
                    int newStock = b.getQuantity() + receivedStock; // Add received stock to current stock
                    b.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeBeverage(beverages); // Save updated data back to file
                System.out.println("Stock level updated successfully.");
            }
        } else if (n == 3) { // Update stock level for ingredient
            StockAddIngredient[] ingredients = file.readIngredient();
            for (StockAddIngredient i : ingredients) {
                if (i.getStockID().equalsIgnoreCase(id)) {
                    int newStock = i.getQuantity() + receivedStock; // Add received stock to current stock
                    i.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeIngredient(ingredients); // Save updated data back to file
                System.out.println("Stock level updated successfully.");
            }
        }
        
        if (!found) {
            System.out.println("Failed to update stock level. ID not found.");
        }
    }
}
