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
public class StockBeverage extends Stock {

    public static final String RESET = "\u001B[0m";
    private double alcoholContent;
    private String isCarbonated;
    private int volume;

    public StockBeverage() {
        super();
        this.alcoholContent = alcoholContent;
        this.isCarbonated = isCarbonated;
        this.volume = volume;
    }

    public StockBeverage(String stockID, String name, int quantity, double price,
            String supplier, double alcoholContent,
            String isCarbonated, int volume) {
        super(stockID, name, quantity, price, supplier);
        this.alcoholContent = alcoholContent;
        this.isCarbonated = isCarbonated;
        this.volume = volume;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public String getIsCarbonated() {
        return isCarbonated;
    }

    public int getVolume() {
        return volume;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public void setIsCarbonated(String isCarbonated) {
        this.isCarbonated = isCarbonated;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("%s %-15s %-10s %-15d",
                super.toString(), // Calls the toString() from Stock
                alcoholContent, isCarbonated, volume);
    }

    public static void beverageIn(int num) {

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;

        while (running) {

            Stock stock = stockIn(num);
            StockFile file = new StockFile();

            double alcoholContent = 0.0;
            boolean validAlcoholInput = false;

            do {
                try {
                    System.out.print("Alcohol Content(%) : ");
                    alcoholContent = scanner.nextDouble(); // Try to read a double value
                    if (alcoholContent < 0) {
                        System.out.println("\033[0;31mAlcohol content cannot be negative. Please enter a valid percentage." + RESET);
                    } else {
                        validAlcoholInput = true; // Valid input, exit loop
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\033[0;31mInvalid input. Please enter a valid number for alcohol content." + RESET);
                    scanner.next(); // Clear invalid input
                }
            } while (!validAlcoholInput);

            scanner.nextLine(); // Clear the buffer after reading double

            // Validate carbonated input (yes/no)
            System.out.print("Carbonated? (yes/no) : ");
            String isCarbonated = scanner.nextLine().toLowerCase();
            while (!isCarbonated.equals("yes") && !isCarbonated.equals("no")) {
                System.out.print("\033[0;31mInvalid input. Please enter 'yes' or 'no': " + RESET);
                isCarbonated = scanner.nextLine().toLowerCase();
            }

            int volume = 0;
            boolean validVolumeInput = false;

            do {
                try {
                    System.out.print("Volume : ");
                    volume = scanner.nextInt(); // Try to read an integer
                    if (volume <= 0) {
                        System.out.println("\033[0;31mVolume must be a positive number. Please enter a valid volume." + RESET);
                    } else {
                        validVolumeInput = true; // Valid input, exit loop
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\033[0;31mInvalid input. Please enter a valid integer for volume." + RESET);
                    scanner.next(); // Clear invalid input
                }
            } while (!validVolumeInput);

            scanner.nextLine();

            StockBeverage beverage = new StockBeverage(stock.getStockID(), stock.getName(), stock.getQuantity(),
                    stock.getPrice(), stock.getSupplier(),
                    alcoholContent, isCarbonated, volume);

            System.out.print("\n");
            System.out.println("You can press any key to stop.");
            System.out.print("Continue?('y' to yes) : ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                running = false;
                System.out.println("\033[0;31mStock Added Unsuccessfully\n" + RESET);
                StockMenu.stockMenu();
            }
            file.appendBeverage(beverage);
        }
        System.out.println("\n");
        Display.displayStock(num);
    }

    public static StockBeverage stockIn(int num) {

        Scanner scanner = new Scanner(System.in);

        String stockID = StockIDGenerator.generateBeverageID();
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

        return new StockBeverage(stockID, name, quantity, price, supplier, 0.0, "no", 0);

    }
}
