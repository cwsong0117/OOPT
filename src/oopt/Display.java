/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

/**
 *
 * @author User
 */
public class Display {

    public static void displayFoodTitle() {
        System.out.printf("%-7s %-25s %-9s %-7s %-14s %-11s %-10s %-15s\n",
                "ID", "Name", "Quantity", "Price", "Supplier",
                "Organic", "Allergens", "Storage Temp");
        System.out.println("======================================================================================================");
    }

    public static void displayBeverageTitle() {
        System.out.printf("%-7s %-25s %-9s %-7s %-14s %-11s %-10s %-15s\n",
                "ID", "Name", "Quantity", "Price", "Supplier",
                "Alcohol Content", "Carbonated", "Volume");
        System.out.println("======================================================================================================");
    }

    public static void displayIngredientTitle() {
        System.out.printf("%-7s %-25s %-9s %-7s %-14s %-11s %-10s %-15s\n",
                "ID", "Name", "Quantity", "Price", "Supplier",
                "Ingredient Type", "Gluten", "Calory");
        System.out.println("======================================================================================================");
    }

    //the purpose of create those method is to ensure the reusability in other method
    public static void displayStock(int n) {

        StockFile file = new StockFile();
        StockMenu stockMenu = new StockMenu();

        if (n == 1) {
            StockAddFood[] foods = file.readFood();
            displayFoodTitle();

            for (StockAddFood f : foods) {
                System.out.println(f.toString());
            }
            System.out.print("\n\n");
            stockMenu.stockMenu();
        } else if (n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            displayBeverageTitle();

            for (StockAddBeverage b : beverages) {
                System.out.println(b.toString());
            }
            System.out.print("\n\n");
            stockMenu.stockMenu();
        } else if (n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            displayIngredientTitle();

            for (StockAddIngredient i : ingredients) {
                System.out.println(i.toString());
            }
            System.out.print("\n\n");
            stockMenu.stockMenu();
        }
    }

    public static void listFood() {
        StockAddFood[] foodList = StockFile.readFood();
        int count = 0;
        System.out.println("Food List:");
        System.out.printf(" ===================================================\n");
        System.out.printf("| %-4s | %-8s | %-20s | %-8s |\n", "No.", "Stock ID", "Food Name", "Quantity");
        System.out.printf(" ===================================================\n");
        for (StockAddFood food : foodList) {
            count++;
            System.out.printf("| %-4d | %-8s | %-20s | %-8d |\n", count, food.getStockID(), food.getName(), food.getQuantity());
        }
        System.out.printf(" ===================================================\n");
    }

    public static void listBeverage() {
        StockAddBeverage[] beverageList = StockFile.readBeverage();
        int count = 0;
        System.out.println("Beverage List:");
        System.out.printf(" ===================================================\n");
        System.out.printf("| %-4s | %-8s | %-20s | %-8s |\n", "No.", "Stock ID", "Food Name", "Quantity");
        System.out.printf(" ===================================================\n");
        for (StockAddBeverage b : beverageList) {
            count++;
            System.out.printf("| %-4d | %-8s | %-20s | %-8d |\n", count, b.getStockID(), b.getName(), b.getQuantity());
        }
        System.out.printf(" ===================================================\n");
    }

    public static void listIngredient() {
        StockAddIngredient[] ingredientList = StockFile.readIngredient();
        int count = 0;
        System.out.println("Ingredient List:");
        System.out.printf(" ===================================================\n");
        System.out.printf("| %-4s | %-8s | %-20s | %-8s |\n", "No.", "Stock ID", "Food Name", "Quantity");
        System.out.printf(" ===================================================\n");
        for (StockAddIngredient i : ingredientList) {
            count++;
            System.out.printf("| %-4d | %-8s | %-20s | %-8d |\n", count, i.getStockID(), i.getName(), i.getQuantity());
        }
        System.out.printf(" ===================================================\n");
    }

}
