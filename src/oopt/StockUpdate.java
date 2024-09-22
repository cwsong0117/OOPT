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
    
    public static final String RESET = "\u001B[0m";

    public static void updateStock(int n) {

        Scanner scanner = new Scanner(System.in);
        StockSearch s = new StockSearch();

        //Search Stock that need to update using ID
        String id = s.searchByID(n);

        //Enter the amount of stock receive
        int quantity = 0;
        do {
            System.out.print("\nStock Received: ");
            quantity = scanner.nextInt();
            if (quantity == -1) {
                System.out.println("\033[0;31mExiting...\n" + RESET);
                StockMenu.stockMenu();
            } else if (quantity <= 0) {
                System.out.println("\033[0;31mCannot be zero or negative...\n" + RESET);
            }
        } while (quantity <= 0);

        StockFile file = new StockFile();
        boolean found = false;

        if (n == 1) { // Update stock level for food
            StockFood[] foods = file.readFood();
            for (StockFood f : foods) {
                if (f.getStockID().equalsIgnoreCase(id)) {
                    int newStock = f.getQuantity() + quantity; // Add received stock to current stock
                    f.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeFood(foods); // Save updated data back to file
                System.out.println("\033[0;31mStock level updated successfully." + RESET);
            }
        } else if (n == 2) { // Update stock level for beverage
            StockBeverage[] beverages = file.readBeverage();
            for (StockBeverage b : beverages) {
                if (b.getStockID().equalsIgnoreCase(id)) {
                    int newStock = b.getQuantity() + quantity; // Add received stock to current stock
                    b.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeBeverage(beverages); // Save updated data back to file
                System.out.println("\033[0;31mStock level updated successfully." + RESET);
            }
        } else if (n == 3) { // Update stock level for ingredient
            StockIngredient[] ingredients = file.readIngredient();
            for (StockIngredient i : ingredients) {
                if (i.getStockID().equalsIgnoreCase(id)) {
                    int newStock = i.getQuantity() + quantity; // Add received stock to current stock
                    i.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeIngredient(ingredients); // Save updated data back to file
                System.out.println("\033[0;31mStock level updated successfully." + RESET);
            }
        }

        if (!found) {
            System.out.println("\033[0;31mFailed to update stock level. ID not found." + RESET);
        }
    }

    public static boolean updateStock(String stockID, int quantity) {

        StockFile file = new StockFile();
        boolean found = false;

        if (quantity < 0) {
            System.out.println("\n\033[0;31mInvalid quantity cannot less than 0!" + RESET);
            return false;
        }

        if (stockID.startsWith("FD")) { // Update stock level for food
            StockFood[] foods = file.readFood();
            for (StockFood f : foods) {
                if (f.getStockID().equalsIgnoreCase(stockID)) {
                    if (f.getQuantity() - quantity >= 0) {
                        int newStock = f.getQuantity() - quantity; // Add received stock to current stock
                        f.setQuantity(newStock);
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                file.writeFood(foods); // Save updated data back to file
                System.out.println("\033[0;31mStock level updated successfully." + RESET);
            }
        } else if (stockID.startsWith("BV")) { // Update stock level for beverage
            StockBeverage[] beverages = file.readBeverage();
            for (StockBeverage b : beverages) {
                if (b.getStockID().equalsIgnoreCase(stockID)) {
                    if (b.getQuantity() - quantity >= 0) {
                        int newStock = b.getQuantity() - quantity; // Add received stock to current stock
                        b.setQuantity(newStock);
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                file.writeBeverage(beverages); // Save updated data back to file
                System.out.println("\033[0;31mStock level updated successfully." + RESET);
            }
        } else if (stockID.startsWith("IG")) { // Update stock level for ingredient
            StockIngredient[] ingredients = file.readIngredient();
            for (StockIngredient i : ingredients) {
                if (i.getStockID().equalsIgnoreCase(stockID)) {
                    if (i.getQuantity() - quantity >= 0) {
                        int newStock = i.getQuantity() - quantity; // Add received stock to current stock
                        i.setQuantity(newStock);
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                file.writeIngredient(ingredients); // Save updated data back to file
                System.out.println("\033[0;31mStock level updated successfully." + RESET);
            }
        }

        if (!found) {
            System.out.println("\n\033[0;31mFailed to update stock level. ID not found or quantity is too large!" + RESET);
        }
        return found;

    }

    public static void returnStock(String stockID, int quantity) {

        StockFile file = new StockFile();
        boolean found = false;

        if (stockID.startsWith("FD")) { // Update stock level for food
            StockFood[] foods = file.readFood();
            for (StockFood f : foods) {
                if (f.getStockID().equalsIgnoreCase(stockID)) {
                    int newStock = f.getQuantity() + quantity; // Add received stock to current stock
                    f.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeFood(foods); // Save updated data back to file
            }
        } else if (stockID.startsWith("BV")) { // Update stock level for beverage
            StockBeverage[] beverages = file.readBeverage();
            for (StockBeverage b : beverages) {
                if (b.getStockID().equalsIgnoreCase(stockID)) {
                    int newStock = b.getQuantity() + quantity; // Add received stock to current stock
                    b.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeBeverage(beverages); // Save updated data back to file
            }
        } else if (stockID.startsWith("IG")) { // Update stock level for ingredient
            StockIngredient[] ingredients = file.readIngredient();
            for (StockIngredient i : ingredients) {
                if (i.getStockID().equalsIgnoreCase(stockID)) {
                    int newStock = i.getQuantity() + quantity; // Add received stock to current stock
                    i.setQuantity(newStock);
                    found = true;
                    break;
                }
            }
            if (found) {
                file.writeIngredient(ingredients); // Save updated data back to file
            }
        }

        if (!found) {
            System.out.println("\033[0;31mFailed to update stock level. ID not found." + RESET);
        }
    }
}