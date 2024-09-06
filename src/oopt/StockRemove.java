/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class StockRemove {
    public static void removeStock(int n) {
    
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        String ID;
        
        switch(n) {
            case 1:
                StockAddFood[] foods = file.readFood();
                System.out.print("Please enter the Stock ID that need to deleted > ");
                ID = scanner.nextLine();
                //A list to hold the remaining foods after deletion
                List<StockAddFood> remainFoods = new ArrayList<>(Arrays.asList(foods)); 

                Iterator<StockAddFood> FoodIterator = remainFoods.iterator();
                while(FoodIterator.hasNext()) {
                    StockAddFood f = FoodIterator.next();
                    if(f.getStockID().equalsIgnoreCase(ID)) {
                        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                                      "ID", "Name", "Quantity", "Price", "Supplier", 
                                      "Expiry Date", "Arrival Date", "Location", 
                                      "Organic", "Allergens", "Storage Temp");
                        System.out.println(f.toString() + "\n");
                        System.out.print("Confirm to delete('y' = yes) > ");
                        String confirm = scanner.nextLine();
                        if(confirm.equalsIgnoreCase("y")) {
                            FoodIterator.remove();//remove the item from list safely
                            System.out.println("\nFood Item Deleted Successful...\n");
                            found = true;
                        }
                        file.writeFood(remainFoods.toArray(new StockAddFood[0]));
                    }
                }
                if(!found) {
                        System.out.println("No Such ID in file.\n");
                }
                StockMenu.stockMenu();
                break;
                
            case 2:
                StockAddBeverage[] beverages = file.readBeverage();
                System.out.print("Please enter the Stock ID that need to deleted > ");
                ID = scanner.nextLine();

                //A list to hold the remaining foods after deletion
                List<StockAddBeverage> remainBeverages = new ArrayList<>(Arrays.asList(beverages)); 

                Iterator<StockAddBeverage> BeverageIterator = remainBeverages.iterator();
                while(BeverageIterator.hasNext()) {
                    StockAddBeverage b = BeverageIterator.next();
                    if(b.getStockID().equalsIgnoreCase(ID)) {
                        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                                      "ID", "Name", "Quantity", "Price", "Supplier", 
                                      "Expiry Date", "Arrival Date", "Location", 
                                      "Organic", "Allergens", "Storage Temp");
                        System.out.println(b.toString() + "\n");
                        System.out.print("Confirm to delete('y' = yes) > ");
                        String confirm = scanner.nextLine();
                        if(confirm.equalsIgnoreCase("y")) {
                            BeverageIterator.remove();//remove the item from list safely
                            System.out.println("\nBeverage Item Deleted Successful...\n");
                            found = true;
                        }
                        file.writeBeverage(remainBeverages.toArray(new StockAddBeverage[0]));
                    }
                }
                if(!found) {
                    System.out.println("No Such ID in file.\n");
                }
                StockMenu.stockMenu();
                break;
                
            case 3:
                StockMenu s = new StockMenu();
                StockAddIngredient[] ingredients = file.readIngredient();

                System.out.print("Please enter the Stock ID that need to deleted > ");
                ID = scanner.nextLine();

                //A list to hold the remaining foods after deletion
                List<StockAddIngredient> remainIngredients = new ArrayList<>(Arrays.asList(ingredients)); 

                Iterator<StockAddIngredient> IngredientIterator = remainIngredients.iterator();
                while(IngredientIterator.hasNext()) {
                    StockAddIngredient i = IngredientIterator.next();
                    if(i.getStockID().equalsIgnoreCase(ID)) {
                        System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                                      "ID", "Name", "Quantity", "Price", "Supplier", 
                                      "Expiry Date", "Arrival Date", "Location", 
                                      "Organic", "Allergens", "Storage Temp");
                        System.out.println(i.toString() + "\n");
                        System.out.print("Confirm to delete('y' = yes) > ");
                        String confirm = scanner.nextLine();
                        if(confirm.equalsIgnoreCase("y")) {
                            IngredientIterator.remove();//remove the item from list safely
                            System.out.println("\nIngredient Item Deleted Successful...\n");
                            found = true;
                        }
                        file.writeIngredient(remainIngredients.toArray(new StockAddIngredient[0]));
                    }   
                }
                if(!found) {
                    System.out.println("No Such ID in file.\n");
                }
               StockMenu.stockMenu();
                break;
        }
    }
}

