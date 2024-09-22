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
public class StockFood extends Stock {

    public static final String RESET = "\u001B[0m";
    private String isOrganic;
    private String allergens;
    private double storageTemperature;

    public StockFood() {
        super();
        this.isOrganic = "";
        this.allergens = "";
        this.storageTemperature = 0.0;
    }

    public StockFood(String stockID, String name, int quantity, double price,
            String supplier, String isOrganic,
            String allergens, double storageTemperature) {
        super(stockID, name, quantity, price, supplier);
        this.isOrganic = isOrganic;
        this.allergens = allergens;
        this.storageTemperature = storageTemperature;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public String getAllergens() {
        return allergens;
    }

    public double getStorageTemperature() {
        return storageTemperature;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public void setStorageTemperature(double storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    @Override
    public String toString() {
        return String.format("%s %-11s %-10s %-15.2f",
                super.toString(), // Calls the toString() from Stock
                isOrganic, allergens, storageTemperature);
    }

    public static void foodIn(int num) {

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;

        while (running) {

            Stock stock = stockIn(num);
            StockFile file = new StockFile();

            System.out.print("Organic?(yes/no) : ");
            String isOrganic = scanner.nextLine().toLowerCase();
            while (!isOrganic.equals("yes") && !isOrganic.equals("no")) {
                System.out.print("\033[0;31mInvalid input. Please enter 'yes' or 'no': " + RESET);
                isOrganic = scanner.nextLine().toLowerCase();
            }

            System.out.print("Allergens : ");
            String allergens = scanner.nextLine();

            double storageTemperature = 0.0;
            boolean validInput = false;

            do {
                try {
                    System.out.print("Storage Temperature: ");
                    storageTemperature = scanner.nextDouble(); // Try to read a double value
                    if (storageTemperature == -1) {
                        System.out.println("\033[0;31mExisting...\n");
                        StockMenu.stockMenu();
                    }
                    if (storageTemperature <= 0) {
                        System.out.println("\033[0;31mThe quantity cannot be zero or negative. Please enter a valid quantity." + RESET);
                    } else {
                        validInput = true; // Valid input was entered, exit the loop
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid temperature (numeric value).");
                    scanner.next(); // Clear the invalid input from the scanner buffer
                }
            } while (!validInput);

            scanner.nextLine();

            StockFood food = new StockFood(stock.getStockID(), stock.getName(), stock.getQuantity(),
                    stock.getPrice(), stock.getSupplier(),
                    isOrganic, allergens, storageTemperature);

            System.out.print("\n");
            System.out.println("You can press any key to stop.");
            System.out.print("Continue?('y' to yes) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                running = false;
                System.out.println("\033[0;31mStock Added Unsuccessfully\n" + RESET);
                StockMenu.stockMenu();
            }
            file.appendFood(food);
        }
        System.out.println("\n");
        Display.displayStock(num);
    }

    public static StockFood stockIn(int num) {

        Scanner scanner = new Scanner(System.in);

        String stockID = StockIDGenerator.generateFoodID();
        System.out.print("Stock ID : " + stockID + "\n");

        String name;
        do {
            System.out.print("Name : ");
            name = scanner.nextLine();

            if (name.equals("-1")) {
                System.out.println("Existing...\n");
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

        return new StockFood(stockID, name, quantity, price, supplier, "no", "", 0.0);

    }
}
