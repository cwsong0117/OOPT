/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class StockIngredient extends Stock {

    public static final String RESET = "\u001B[0m";
    private String ingredientType;
    private String gluten;
    private int calory;

    public StockIngredient() {
        super();
        this.ingredientType = ingredientType;
        this.gluten = gluten;
        this.calory = calory;
    }

    public StockIngredient(String stockID, String name, int quantity, double price,
            String supplier, String ingredientType,
            String gluten, int calory) {
        super(stockID, name, quantity, price, supplier);
        this.ingredientType = ingredientType;
        this.gluten = gluten;
        this.calory = calory;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public String getGluten() {
        return gluten;
    }

    public int getCalory() {
        return calory;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public void setGluten(String gluten) {
        this.gluten = gluten;
    }

    public void setCalory(int calory) {
        this.calory = calory;
    }

    @Override
    public String toString() {
        return String.format("%s %-15s %-10s %-15d",
                super.toString(), // Calls the toString() from Stock
                ingredientType, gluten, calory);
    }

    public static void ingredientIn(int num) {

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;

        while (running) {

            Stock stock = stockIn(num);
            StockFile file = new StockFile();

            String ingredientType;

            do {
                System.out.print("Ingredient Type: ");
                ingredientType = scanner.nextLine();
                if(ingredientType.equals("-1")) {
                    System.out.println("\033[0;31mExisting...\n" + RESET);
                    StockMenu.stockMenu();
                }
                // Check if ingredientType contains any digits
                if (ingredientType.matches(".*\\d.*")) {
                    System.out.println("\033[0;31mInvalid input. Ingredient Type cannot contain numbers. Please enter a valid type." + RESET);
                }
            } while (ingredientType.matches(".*\\d.*")); // Loop until there are no digits in the input

            // Validate gluten input (yes/no)
            System.out.print("Gluten? (yes/no): ");
            String gluten = scanner.nextLine().toLowerCase();
            if(gluten.equals("-1")) {
                System.out.println("\033[0;31mExisting...\n" + RESET);
                StockMenu.stockMenu();
            }
            while (!gluten.equals("yes") && !gluten.equals("no")) {
                System.out.print("\033[0;31mInvalid input. Please enter 'yes' or 'no': " + RESET);
                gluten = scanner.nextLine().toLowerCase();
            }

            // Validate calory input
            int calory = 0;
            boolean validCaloryInput = false;

            do {
                try {
                    System.out.print("Calory: ");
                    calory = scanner.nextInt(); // Try to read an integer value
                    if(calory == -1) {
                        System.out.println("\033[0;31mExisting...\n" + RESET);
                        StockMenu.stockMenu();
                    }
                    if (calory < 0) {
                        System.out.println("\033[0;31mCalory cannot be negative. Please enter a valid number." + RESET);
                    } else {
                        validCaloryInput = true; // Valid input, exit loop
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\033[0;31mInvalid input. Please enter a valid integer for calory." + RESET);
                    scanner.next(); // Clear the invalid input
                }
            } while (!validCaloryInput);

            scanner.nextLine();

            StockIngredient ingredient = new StockIngredient(stock.getStockID(), stock.getName(), stock.getQuantity(),
                    stock.getPrice(), stock.getSupplier(),
                    ingredientType, gluten, calory);

            System.out.print("\n");
            System.out.println("You can press any key to stop.");
            System.out.print("Continue?('y' to yes) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                System.out.println("\033[0;31mStock Added Unsuccessfully\n" + RESET);
                StockMenu.stockMenu();
                running = false;
            }
            file.appendIngredient(ingredient);
        }
        System.out.println("\n");
        Display.displayStock(num);
    }

    public static StockIngredient stockIn(int num) {

        Scanner scanner = new Scanner(System.in);

        String stockID = StockIDGenerator.generateIngredientID();
        System.out.print("Stock ID : " + stockID + "\n");

        String name;
        do {
            System.out.print("Name : ");
            name = scanner.nextLine();

            if (name.equals("-1")) {
                System.out.println("\033[0;31mExisting...\n" + RESET);
                StockMenu.stockMenu();
                return null;
            } else if (!Validation.validate(name, num)) {
                System.out.println("\033[0;31mName already exists. Please select UPDATE or enter other name." + RESET);
            } else if (!Validation.isNotNullOrEmpty(name)) {
                System.out.println("\033[0;31mName cannot be empty. Please enter a name." + RESET);
            }
        } while (!Validation.validate(name, num));

        int quantity = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print("Quantity : ");
                quantity = scanner.nextInt(); // Try to read an integer value
                if (quantity == -1) {
                    System.out.println("\033[0;31mExiting...\n" + RESET);
                    StockMenu.stockMenu(); // Assuming StockMenu has a static stockMenu method
                }
                if (quantity <= 0) {
                    System.out.println("\033[0;31mThe quantity cannot be zero or negative. Please enter a valid quantity." + RESET);
                } else {
                    validInput = true; // Valid input was entered, exit the loop
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[0;31mInvalid input. Please enter an integer." + RESET);
                scanner.next(); // Clear the invalid input
            }
        } while (!validInput);

        scanner.nextLine();

        String priceInput;
        double price = 0.0;
        do {
            System.out.print("Price : ");
            priceInput = scanner.nextLine();
            if (priceInput.equals("-1")) {
                System.out.println("\033[0;31mExisting...\n");
                StockMenu.stockMenu();
                return null;
            } else if (!Validation.isNotNullOrEmpty(priceInput)) {
                System.out.println("\033[0;31mPrice cannot be empty." + RESET);
            } else {
                try {
                    price = Double.parseDouble(priceInput);
                    if (price <= 0.0) {
                        System.out.println("\033[0;31mPrice cannot be zero or negative." + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\033[0;31mInvald price format. Please enter a valid price." + RESET);
                }
            }
        } while (price <= 0);

        String supplier;
        do {
            System.out.print("Supplier : ");
            supplier = scanner.nextLine();

            if (supplier.equals("-1")) {
                System.out.println("\033[0;31mExisting...\n" + RESET);
                StockMenu.stockMenu();
                return null;
            } else if (!Validation.isNotNullOrEmpty(supplier)) {
                System.out.println("\033[0;31mSupplier cannot be empty. Please enter a supplier." + RESET);
            } else if (!Validation.validate(supplier)) {
                System.out.println("\033[0;31mNo such supplier in system...Please enter a valid supplier." + RESET);
            }
        } while (!Validation.isNotNullOrEmpty(supplier) || !Validation.validate(supplier));

        return new StockIngredient(stockID, name, quantity, price, supplier, "", "no", 0);

    }
}
