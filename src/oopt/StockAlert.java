/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class StockAlert {
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
