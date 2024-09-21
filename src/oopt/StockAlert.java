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
        StockMenu s = new StockMenu();
        
        System.out.println("Item(s) will be displayed where stock balance is lower than the quantity entered.");
        System.out.print("Enter The Quantity: ");
        int lowBalance = scanner.nextInt();
        
        if(n == 1) {
        //food low balance display
            List<StockFood> lowStockItems = getFoodBalance(lowBalance);
            
            if(lowStockItems.isEmpty()) {
                System.out.println("No items with low stock...\n");
            }else {
                System.out.println("Items with stock less than " + lowBalance + " : \n");
                Display.displayFoodTitle();
                for(StockFood f : lowStockItems) {
                    System.out.println(f.toString());
                }
            }
        }
        else if(n == 2) {
        //beverage low balance display
            List<StockBeverage> lowStockItems = getBeverageBalance(lowBalance);

                if(lowStockItems.isEmpty()) {
                    System.out.println("No items with low stock...\n");
                }else {
                    System.out.println("Items with stock less than " + lowBalance + " : \n");
                    Display.displayFoodTitle();
                    for(StockBeverage b : lowStockItems) {
                        System.out.println(b.toString());
                    }
                }
            }
        else {
        //ingredient low balance display
            List<StockIngredient> lowStockItems = getIngredientBalance(lowBalance);
            
            if(lowStockItems.isEmpty()) {
                System.out.println("No items with low stock...\n");
            }else {
                System.out.println("Items with stock less than " + lowBalance + " : \n");
                Display.displayFoodTitle();
                for(StockIngredient i : lowStockItems) {
                    System.out.println(i.toString());
                }
            }
        }
        System.out.println("\n");
        s.stockMenu();
    }
    
    private static List<StockFood> getFoodBalance(int quantity) {
        List<StockFood> lowStockItems = new ArrayList<>();
        StockFood[] Foods = StockFile.readFood();

        for(StockFood f : Foods) {
            if(f.getQuantity() <= quantity) {
                lowStockItems.add(f);
            }
        }
        return lowStockItems;
    }
    
    private static List<StockBeverage> getBeverageBalance(int quantity) {
        List<StockBeverage> lowStockItems = new ArrayList<>();
        StockBeverage[] Beverages = StockFile.readBeverage();

        for(StockBeverage b : Beverages) {
            if(b.getQuantity() <= quantity) {
                lowStockItems.add(b);
            }
        }
        return lowStockItems;
    }
    
    private static List<StockIngredient> getIngredientBalance(int quantity) {
        List<StockIngredient> lowStockItems = new ArrayList<>();
        StockIngredient[] Ingredients = StockFile.readIngredient();

        for(StockIngredient i : Ingredients) {
            if(i.getQuantity() <= quantity) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }
}
