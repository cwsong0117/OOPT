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
        Display display = new Display();
        
        //Search Stock that need to update using ID
        System.out.print("Enter the ID : ");
        String id = scanner.nextLine();
        
        //Enter the amount of stock receive
        int quantity = 0;
        do{
            System.out.print("Stock Received: ");
            quantity = scanner.nextInt();
            if(quantity == -1) {
                System.out.println("Exiting...\n\n");
                StockMenu.stockMenu();
            }else if(quantity <= 0){
                System.out.println("Cannot be zero or negative...\n");
            }
        }while(quantity <= 0);
        
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.println("\n");
        
        if (n == 1) { // Update stock level for food
            StockAddFood[] foods = file.readFood();
            display.displayFoodTitle();
            for(StockAddFood f : foods) {
                if(f.getStockID().equalsIgnoreCase(id)) {
                    int newStock = f.getQuantity() + quantity; // Add received stock to current stock
                    f.setQuantity(newStock);
                    System.out.println(f.toString());
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeFood(foods); // Save updated data back to file
                System.out.println("\nStock level updated successfully.\n");
            }
        } else if (n == 2) { // Update stock level for beverage
            StockAddBeverage[] beverages = file.readBeverage();
            display.displayBeverageTitle();
            for(StockAddBeverage b : beverages) {
                if(b.getStockID().equalsIgnoreCase(id)) {
                    int newStock = b.getQuantity() + quantity; // Add received stock to current stock
                    b.setQuantity(newStock);
                    System.out.println(b.toString());
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeBeverage(beverages); // Save updated data back to file
                System.out.println("\nStock level updated successfully.\n");
            }
        } else if (n == 3) { // Update stock level for ingredient
            StockAddIngredient[] ingredients = file.readIngredient();
            display.displayIngredientTitle();
            for(StockAddIngredient i : ingredients) {
                if(i.getStockID().equalsIgnoreCase(id)) {
                    int newStock = i.getQuantity() + quantity; // Add received stock to current stock
                    i.setQuantity(newStock);
                    System.out.println(i.toString());
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeIngredient(ingredients); // Save updated data back to file
                System.out.println("\nStock level updated successfully.\n");
            }
        }
        
        if (!found) {
            System.out.println("\nFailed to update stock level. ID not found.\n");
        }
    }
    
    public static void updateStock(int n, String stockID, int quantity) {
        
        StockFile file = new StockFile();
        boolean found = false;
        
        if (n == 1) { // Update stock level for food
            StockAddFood[] foods = file.readFood();
            for (StockAddFood f : foods) {
                if (f.getStockID().equalsIgnoreCase(stockID)) {
                    if(f.getQuantity() - quantity >= 0){
                    int newStock = f.getQuantity() - quantity; // Add received stock to current stock
                    f.setQuantity(newStock);
                    found = true;
                    break;
                    }else{
                        //jump back to the ship menu;
                    }
                }
            }
            if (found) {
                file.writeFood(foods); // Save updated data back to file
                System.out.println("\nStock level updated successfully.\n");
            }
        } else if (n == 2) { // Update stock level for beverage
            StockAddBeverage[] beverages = file.readBeverage();
            for (StockAddBeverage b : beverages) {
                if (b.getStockID().equalsIgnoreCase(stockID)) {
                    if(b.getQuantity() - quantity >= 0){
                        int newStock = b.getQuantity() - quantity; // Add received stock to current stock
                        b.setQuantity(newStock);
                        found = true;
                        break;
                    }else{
                        //jump back to the ship menu;
                    }
                }
            }
            if (found) {
                file.writeBeverage(beverages); // Save updated data back to file
                System.out.println("\nStock level updated successfully.\n");
            }
        } else if (n == 3) { // Update stock level for ingredient
            StockAddIngredient[] ingredients = file.readIngredient();
            for (StockAddIngredient i : ingredients) {
                if (i.getStockID().equalsIgnoreCase(stockID)) {
                    if(i.getQuantity() - quantity >= 0){
                        int newStock = i.getQuantity() - quantity; // Add received stock to current stock
                        i.setQuantity(newStock);
                        found = true;
                        break;
                    }else{
                        //jump back to the ship menu;
                    }
                }
            }
            if (found) {
                file.writeIngredient(ingredients); // Save updated data back to file
                System.out.println("\nStock level updated successfully.\n");
            }
        }
        
        if (!found) {
            System.out.println("\nFailed to update stock level. ID not found.\n");
        }
            
    
    }
}
