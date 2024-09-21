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
    public static final String RESET = "\u001B[0m";
    public static void removeStock(int n) {
    
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        Display title = new Display();
        boolean found = false;
        String ID;
        
        try {
            switch (n) {
                case 1:
                    StockFood[] foods = file.readFood();
                    System.out.print("Please enter the Stock ID that needs to be deleted > ");
                    ID = scanner.nextLine();

                    // A list to hold the remaining foods after deletion
                    List<StockFood> remainFoods = new ArrayList<>(Arrays.asList(foods));

                    Iterator<StockFood> FoodIterator = remainFoods.iterator();
                    while (FoodIterator.hasNext()) {
                        StockFood f = FoodIterator.next();
                        if (f.getStockID().equalsIgnoreCase(ID)) {
                            title.displayFoodTitle();
                            System.out.println(f.toString() + "\n");
                            System.out.print("Confirm to delete ('y' = yes) > ");
                            String confirm = scanner.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                FoodIterator.remove(); // Remove the item from the list safely
                                System.out.println("\nFood Item Deleted Successfully...\n");
                                found = true;
                            }
                            file.writeFood(remainFoods.toArray(new StockFood[0]));
                        }
                    }
                    if (!found) {
                        System.out.println("\033[0;31mNo Such ID in file.\n" + RESET);
                    }
                    StockMenu.stockMenu();
                    break;

                case 2:
                    StockBeverage[] beverages = file.readBeverage();
                    System.out.print("Please enter the Stock ID that needs to be deleted > ");
                    ID = scanner.nextLine();

                    // A list to hold the remaining beverages after deletion
                    List<StockBeverage> remainBeverages = new ArrayList<>(Arrays.asList(beverages));

                    Iterator<StockBeverage> BeverageIterator = remainBeverages.iterator();
                    while (BeverageIterator.hasNext()) {
                        StockBeverage b = BeverageIterator.next();
                        if (b.getStockID().equalsIgnoreCase(ID)) {
                            title.displayBeverageTitle();
                            System.out.println(b.toString() + "\n");
                            System.out.print("Confirm to delete ('y' = yes) > ");
                            String confirm = scanner.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                BeverageIterator.remove(); // Remove the item from the list safely
                                System.out.println("\nBeverage Item Deleted Successfully...\n");
                                found = true;
                            }
                            file.writeBeverage(remainBeverages.toArray(new StockBeverage[0]));
                        }
                    }
                    if (!found) {
                        System.out.println("\033[0;31mNo Such ID in file.\n" + RESET);
                    }
                    StockMenu.stockMenu();
                    break;

                case 3:
                    StockIngredient[] ingredients = file.readIngredient();
                    System.out.print("Please enter the Stock ID that needs to be deleted > ");
                    ID = scanner.nextLine();

                    // A list to hold the remaining ingredients after deletion
                    List<StockIngredient> remainIngredients = new ArrayList<>(Arrays.asList(ingredients));

                    Iterator<StockIngredient> IngredientIterator = remainIngredients.iterator();
                    while (IngredientIterator.hasNext()) {
                        StockIngredient i = IngredientIterator.next();
                        if (i.getStockID().equalsIgnoreCase(ID)) {
                            title.displayIngredientTitle();
                            System.out.println(i.toString() + "\n");
                            System.out.print("Confirm to delete ('y' = yes) > ");
                            String confirm = scanner.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                IngredientIterator.remove(); // Remove the item from the list safely
                                System.out.println("\nIngredient Item Deleted Successfully...\n");
                                found = true;
                            }
                            file.writeIngredient(remainIngredients.toArray(new StockIngredient[0]));
                        }
                    }
                    if (!found) {
                        System.out.println("\033[0;31mNo Such ID in file.\n" + RESET);
                    }
                    StockMenu.stockMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\033[0;31mAn error occurred: " + e.getMessage() + "\n" + RESET);
        }
    }
}