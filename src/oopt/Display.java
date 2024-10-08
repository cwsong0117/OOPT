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
public class Display {

    public static void displayFoodTitle() {
        System.out.println("============================================================================================================");
        System.out.printf("%-9s %-25s %-9s %-9s %-16s %-11s %-10s %-15s\n",
                "ID", "Name", "Quantity", "Price", "Supplier",
                "Organic", "Allergens", "Storage Temp");
        System.out.println("============================================================================================================");
    }

    public static void displayBeverageTitle() {
        System.out.println("========================================================================================================");
        System.out.printf("%-9s %-25s %-9s %-9s %-16s %-11s %-10s %-15s\n",
                "ID", "Name", "Quantity", "Price", "Supplier",
                "Alcohol Content", "Carbonated", "Volume");
        System.out.println("========================================================================================================");
    }

    public static void displayIngredientTitle() {
        System.out.println("========================================================================================================");
        System.out.printf("%-9s %-25s %-9s %-9s %-16s %-11s %-10s %-15s\n",
                "ID", "Name", "Quantity", "Price", "Supplier",
                "Ingredient Type", "Gluten", "Calory");
        System.out.println("========================================================================================================");
    }

    //the purpose of create those method is to ensure the reusability in other method
    public static void displayStock(int n) {

        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        StockMenu stockMenu = new StockMenu();

        if (n == 1) {
            StockFood[] foods = file.readFood();
            displayFoodTitle();

            for (StockFood f : foods) {
                System.out.println(f.toString());
            }
            System.out.print("\n\n");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            stockMenu.stockMenu();
        } else if (n == 2) {
            StockBeverage[] beverages = file.readBeverage();
            displayBeverageTitle();

            for (StockBeverage b : beverages) {
                System.out.println(b.toString());
            }
            System.out.print("\n\n");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            stockMenu.stockMenu();
        } else if (n == 3) {
            StockIngredient[] ingredients = file.readIngredient();
            displayIngredientTitle();

            for (StockIngredient i : ingredients) {
                System.out.println(i.toString());
            }
            System.out.print("\n\n");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            stockMenu.stockMenu();
        }
    }

    public static void listFood() {
        StockFood[] foodList = StockFile.readFood();
        int count = 0;
        System.out.println("Food List:");
        System.out.printf(" ===================================================\n");
        System.out.printf("| %-4s | %-8s | %-20s | %-8s |\n", "No.", "Stock ID", "Food Name", "Quantity");
        System.out.printf(" ===================================================\n");
        for (StockFood food : foodList) {
            count++;
            System.out.printf("| %-4d | %-8s | %-20s | %-8d |\n", count, food.getStockID(), food.getName(), food.getQuantity());
        }
        System.out.printf(" ===================================================\n");
    }

    public static void listBeverage() {
        StockBeverage[] beverageList = StockFile.readBeverage();
        int count = 0;
        System.out.println("Beverage List:");
        System.out.printf(" ===================================================\n");
        System.out.printf("| %-4s | %-8s | %-20s | %-8s |\n", "No.", "Stock ID", "Food Name", "Quantity");
        System.out.printf(" ===================================================\n");
        for (StockBeverage b : beverageList) {
            count++;
            System.out.printf("| %-4d | %-8s | %-20s | %-8d |\n", count, b.getStockID(), b.getName(), b.getQuantity());
        }
        System.out.printf(" ===================================================\n");
    }

    public static void listIngredient() {
        StockIngredient[] ingredientList = StockFile.readIngredient();
        int count = 0;
        System.out.println("Ingredient List:");
        System.out.printf(" ===================================================\n");
        System.out.printf("| %-4s | %-8s | %-20s | %-8s |\n", "No.", "Stock ID", "Food Name", "Quantity");
        System.out.printf(" ===================================================\n");
        for (StockIngredient i : ingredientList) {
            count++;
            System.out.printf("| %-4d | %-8s | %-20s | %-8d |\n", count, i.getStockID(), i.getName(), i.getQuantity());
        }
        System.out.printf(" ===================================================\n");
    }

}
