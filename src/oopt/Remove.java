/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import static oopt.Food.operationAfterSearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Remove {
    public static void removeFood() {
    
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Food[] foods = file.readFood();
        boolean found = false;
        
        System.out.print("Please enter the Stock ID that need to deleted > ");
        String ID = scanner.nextLine();
                
        //A list to hold the remaining foods after deletion
        List<Food> remainFoods = new ArrayList<>(Arrays.asList(foods)); 
        
        Iterator<Food> iterator = remainFoods.iterator();
        while(iterator.hasNext()) {
            Food f = iterator.next();
            if(f.getStockID().equalsIgnoreCase(ID)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(f.toString() + "\n");
                System.out.print("Confirm to delete('y' = yes) > ");
                String confirm = scanner.nextLine();
                if(confirm.equalsIgnoreCase("y")) {
                    iterator.remove();//remove the item from list safely
                    System.out.println("\nFood Item Deleted Successful...\n");
                    found = true;
                }
                file.writeFood(remainFoods.toArray(new Food[0]));
            }
        }
        if(!found) {
            System.out.println("No Such ID in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void removeBeverage() {
    
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Beverage[] beverages = file.readBeverage();
        boolean found = false;
        
        System.out.print("Please enter the Stock ID that need to deleted > ");
        String ID = scanner.nextLine();
                
        //A list to hold the remaining foods after deletion
        List<Beverage> remainBeverages = new ArrayList<>(Arrays.asList(beverages)); 
        
        Iterator<Beverage> iterator = remainBeverages.iterator();
        while(iterator.hasNext()) {
            Beverage b = iterator.next();
            if(b.getStockID().equalsIgnoreCase(ID)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(b.toString() + "\n");
                System.out.print("Confirm to delete('y' = yes) > ");
                String confirm = scanner.nextLine();
                if(confirm.equalsIgnoreCase("y")) {
                    iterator.remove();//remove the item from list safely
                    System.out.println("\nFood Item Deleted Successful...\n");
                    found = true;
                }
                file.writeFood(remainBeverages.toArray(new Food[0]));
            }
        }
        if(!found) {
            System.out.println("No Such ID in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void removeIngredient() {
    
        Scanner scanner = new Scanner(System.in);
        File file = new File();
        Ingredient[] ingredients = file.readIngredient();
        boolean found = false;
        
        System.out.print("Please enter the Stock ID that need to deleted > ");
        String ID = scanner.nextLine();
                
        //A list to hold the remaining foods after deletion
        List<Ingredient> remainIngredients = new ArrayList<>(Arrays.asList(ingredients)); 
        
        Iterator<Ingredient> iterator = remainIngredients.iterator();
        while(iterator.hasNext()) {
            Ingredient i = iterator.next();
            if(i.getStockID().equalsIgnoreCase(ID)) {
                System.out.printf("%-7s %-12s %-9s %-7s %-14s %-23s %-14s %-15s %-11s %-10s %-15s\n", 
                              "ID", "Name", "Quantity", "Price", "Supplier", 
                              "Expiry Date", "Arrival Date", "Location", 
                              "Organic", "Allergens", "Storage Temp");
                System.out.println(i.toString() + "\n");
                System.out.print("Confirm to delete('y' = yes) > ");
                String confirm = scanner.nextLine();
                if(confirm.equalsIgnoreCase("y")) {
                    iterator.remove();//remove the item from list safely
                    System.out.println("\nFood Item Deleted Successful...\n");
                    found = true;
                }
                file.writeFood(remainIngredients.toArray(new Food[0]));
            }
        }
        if(!found) {
            System.out.println("No Such ID in file.\n");
        }
        operationAfterSearch();
    }
}
