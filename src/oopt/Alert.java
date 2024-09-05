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
public class Alert {
    
    public static void displayLowStock(int n) {
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Item(s) will be displayed where stock balance is lower than the quantity entered.");
        System.out.print("Enter The Quantity: ");
        int lowBalance = scanner.nextInt();
        
        if(n == 1) {
        //food low balance display
            List<AddFood> lowStockItems = getFoodBalance(lowBalance);
            
            if(lowStockItems.isEmpty()) {
                System.out.println("No items with low stock...\n");
            }else {
                System.out.println("Items with stock less than " + lowBalance + " : ");
                Display.displayFoodTitle();
                for(AddFood f : lowStockItems) {
                    System.out.println(f.toString());
                }
            }
        }
        else if(n == 2) {
        //beverage low balance display
            List<AddBeverage> lowStockItems = getBeverageBalance(lowBalance);

                if(lowStockItems.isEmpty()) {
                    System.out.println("No items with low stock...\n");
                }else {
                    System.out.println("Items with stock less than " + lowBalance + " : ");
                    Display.displayFoodTitle();
                    for(AddBeverage b : lowStockItems) {
                        System.out.println(b.toString());
                    }
                }
            }
        else {
        //ingredient low balance display
            List<AddIngredient> lowStockItems = getIngredientBalance(lowBalance);
            
            if(lowStockItems.isEmpty()) {
                System.out.println("No items with low stock...\n");
            }else {
                System.out.println("Items with stock less than " + lowBalance + " : ");
                Display.displayFoodTitle();
                for(AddIngredient i : lowStockItems) {
                    System.out.println(i.toString());
                }
            }
        }
    }
    
    private static List<AddFood> getFoodBalance(int quantity) {
        List<AddFood> lowStockItems = new ArrayList<>();
        AddFood[] Foods = File.readFood();

        for(AddFood f : Foods) {
            if(f.getQuantity() <= quantity) {
                lowStockItems.add(f);
            }
        }
        return lowStockItems;
    }
    
    private static List<AddBeverage> getBeverageBalance(int quantity) {
        List<AddBeverage> lowStockItems = new ArrayList<>();
        AddBeverage[] Beverages = File.readBeverage();

        for(AddBeverage b : Beverages) {
            if(b.getQuantity() <= quantity) {
                lowStockItems.add(b);
            }
        }
        return lowStockItems;
    }
    
    private static List<AddIngredient> getIngredientBalance(int quantity) {
        List<AddIngredient> lowStockItems = new ArrayList<>();
        AddIngredient[] Ingredients = File.readIngredient();

        for(AddIngredient i : Ingredients) {
            if(i.getQuantity() <= quantity) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }
}
