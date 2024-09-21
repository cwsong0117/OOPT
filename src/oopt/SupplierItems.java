/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class SupplierItems {

    private String stockID;
    private String name;
    private double price;
    private Supplier supplier;

    public SupplierItems(String stockID, String name, double price, Supplier supplier) {
        this.stockID = stockID;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }

    public String getStockID() {
        return stockID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    private static Supplier findSupplierByID(ArrayList<Supplier> suppliersList, String supplierID) {
        for (Supplier supplier : suppliersList) {
            if (supplier.getID().equals(supplierID)) {
                return supplier;
            }
        }
        return null; // Return null if no supplier with the given ID is found
    }

    public static void displayAllItems() {
        ArrayList<Supplier> suppliersList = Supplier.readTextFile(); // Assuming you have a method that reads suppliers
        ArrayList<SupplierItems> itemsList = readItemsTextFile(suppliersList);

        Scanner input = new Scanner(System.in);

        // Group items by supplier
        Map<Supplier, List<SupplierItems>> supplierItemsMap = new HashMap<>();

        for (SupplierItems item : itemsList) {
            Supplier supplier = item.getSupplier();

            // Add the supplier and their item to the map
            supplierItemsMap.computeIfAbsent(supplier, k -> new ArrayList<>()).add(item);
        }

        // Display all items grouped by supplier
        for (Supplier supplier : supplierItemsMap.keySet()) {
            System.out.println("---------------------------------------------------");
            System.out.println("| " + supplier.getID() + " " + supplier.getSupplierName() + " |");
            System.out.println("---------------------------------------------------");
            System.out.println("Items:");

            List<SupplierItems> items = supplierItemsMap.get(supplier);

            for (SupplierItems item : items) {
                System.out.printf("%-10s %-20s RM%.2f\n", item.getStockID(), item.getName(), item.getPrice());
            }

            System.out.println(); // Blank line between suppliers
        }
        System.out.println("\n\t\tPress enter to continue......");
        input.nextLine();
        SupplierItemsMain.SupplierItemsMenu();
    }

    public static void addSupplierItems() {
        ArrayList<Supplier> suppliers = Supplier.readTextFile(); // Load suppliers from file
        ArrayList<SupplierItems> stockItems = readItemsTextFile(suppliers); // Load existing stock items from file
        ArrayList<SupplierItems> newItems = new ArrayList<>(); // To collect added stock items
        Scanner input = new Scanner(System.in);

        boolean validSup = false;
        Supplier selectedSupplier = null;
        String itemId = "";
        String itemName = "";
        double itemPrice = 0.0;

        System.out.println("\n\t\t***Select a Supplier to add new item(s)***\n");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-10s %-20s %s\n", "ID", "Name", "Category");
        System.out.println("--------------------------------------------------------------");
        for (Supplier s : suppliers) {
            if (s.getID().charAt(0) == 'I') {
                System.out.printf("%-10s %-20s %s\n", s.getID(), s.getSupplierName(), s.getCategory());
            }
        }
        for (Supplier s : suppliers) {
            if (s.getID().charAt(0) == 'F') {
                System.out.printf("%-10s %-20s %s\n", s.getID(), s.getSupplierName(), s.getCategory());
            }
        }
        for (Supplier s : suppliers) {
            if (s.getID().charAt(0) == 'B') {
                System.out.printf("%-10s %-20s %s\n", s.getID(), s.getSupplierName(), s.getCategory());
            }
        }

        // Step 1: Select a valid supplier
        do {
            System.out.print("\n\t\tSupplier ID: ");
            String searchId = input.nextLine();

            for (Supplier supplier : suppliers) {
                if (searchId.equalsIgnoreCase(supplier.getID())) {
                    validSup = true;
                    selectedSupplier = supplier;

                    System.out.printf("\n\tSupplier ID: %s", supplier.getID());
                    System.out.printf("\n\tSupplier Name: %s", supplier.getSupplierName());
                    System.out.printf("\n\tCategory: %s", supplier.getCategory());
                    System.out.printf("\n\tCurrent Items (No items added yet if empty):");

                    // Display current items for this supplier
                    for (SupplierItems stockItem : stockItems) {
                        if (stockItem.getSupplier().getID().equalsIgnoreCase(supplier.getID())) {
                            System.out.printf("\n\t%s     RM%.2f", stockItem.getName(), stockItem.getPrice());
                        }
                    }

                    // Step 2: Generate the next item ID based on supplier category
                    switch (supplier.getID().charAt(0)) {
                        case 'F':
                            itemId = generateNextItemId('F');
                            break;
                        case 'B':
                            itemId = generateNextItemId('B');
                            break;
                        default:
                            itemId = generateNextItemId('I');
                            break;
                    }
                    break;
                }
            }

            if (!validSup) {
                System.out.println("INVALID SUPPLIER! Enter to try again or XXX to leave");
                String option = input.nextLine();
                if (option.equalsIgnoreCase("XXX")) {
                    SupplierMain.SupplierMenu();
                    return;
                }
            }
        } while (!validSup);

        boolean moreItems = true;

        while (moreItems) {
            // Step 3: Validate item name
            boolean validName = false;
            do {
                System.out.print("\n\nEnter the name of the new item: ");
                itemName = input.nextLine();
                if (!itemName.isBlank() && !itemName.isEmpty()) {
                    validName = true;
                } else {
                    System.out.println("The name CANNOT be EMPTY or SPACES!");
                    System.out.print("Enter to try again or XXX to leave: ");
                    String option = input.nextLine();
                    if (option.equalsIgnoreCase("XXX")) {
                        SupplierItemsMain.SupplierItemsMenu();
                        return;
                    }
                }
            } while (!validName);

            // Step 4: Validate item price
            boolean validPrice = false;
            do {
                try {
                    System.out.print("Enter the price per unit: ");
                    itemPrice = Double.parseDouble(input.nextLine());
                    if (itemPrice > 0) {
                        validPrice = true;
                    } else {
                        System.out.println("Price must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a numeric value for price.");
                }
            } while (!validPrice);

            // Step 5: Create a SupplierItems object
            SupplierItems newItem = new SupplierItems(itemId, itemName, itemPrice, selectedSupplier);
            newItems.add(newItem);

            // Step 6: Ask if the user wants to add more items
            System.out.print("Do you want to add another item? (Y/N): ");
            String option = input.nextLine();
            if (!option.equalsIgnoreCase("Y")) {
                moreItems = false;
            } else {
                // Increment the item ID manually
                int nextIdNumber = Integer.parseInt(itemId.substring(2)); // Extract the numeric part
                nextIdNumber++; // Increment it
                itemId = itemId.substring(0, 2) + String.format("%03d", nextIdNumber); // Generate next ID
            }
        }

        // Step 7: Display newly added items and ask for confirmation
        System.out.println("\nNewly Added Items:");
        for (SupplierItems item : newItems) {
            System.out.printf("\n\tItem: %s     Unit Price: RM%.2f", item.getName(), item.getPrice());
        }
        System.out.print("\nConfirm adding these items? (Y/N): ");
        String confirm = input.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            // Step 8: Append new stock items to the file if confirmed
            for (SupplierItems item : newItems) {
                appendItemToFile(item);
            }
            System.out.println("\n\t*****Supplier's Item(s) added successfully*****");
        } else {
            System.out.println("\n\tOperation cancelled. No items were added.");
        }

        System.out.print("Press Enter to continue...\n\n");
        input.nextLine(); // Wait for the user to press Enter
        SupplierItemsMain.SupplierItemsMenu();
    }

// Helper method to append the new item to the file
    private static void appendItemToFile(SupplierItems newItem) {
        String fileName = "Items.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // true for append mode
            // Format: stockID | itemName | price | supplierID
            writer.write(newItem.getStockID() + "|" + newItem.getName() + "|" + newItem.getPrice() + "|" + newItem.getSupplier().getID());
            writer.newLine(); // Move to the next line after appending the item
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeSupplierItem() {
        ArrayList<Supplier> suppliers = Supplier.readTextFile(); // Load suppliers from file
        ArrayList<SupplierItems> itemsList = readItemsTextFile(suppliers); // Load stock items from file
        Scanner scanner = new Scanner(System.in);

        boolean validSup = false;
        Supplier chosenSupplier = null;

        System.out.println("\n\t\t***Remove Item from Supplier Stock***");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-10s %-20s %s\n", "ID", "Name", "Category");
        System.out.println("--------------------------------------------------------------");

        boolean supplierHasStock = false;

        for (Supplier j : suppliers) {

            supplierHasStock = false;

            // Check if this supplier has any stocks
            for (SupplierItems i : itemsList) {
                if (j.getID().equalsIgnoreCase(i.getSupplier().getID())) {
                    supplierHasStock = true;
                    break;  // Stop checking further stocks for this supplier
                }
            }

            // Print supplier details only if they have stocks
            if (supplierHasStock) {
                System.out.printf("%-10s %-20s %s\n", j.getID(), j.getSupplierName(), j.getCategory());

            }
        }

        // Step 1: Select a valid supplier by ID that has stock
        do {
            System.out.print("\n\t\tEnter the Supplier ID: ");
            String supplierId = scanner.nextLine().trim();

            for (Supplier supplier : suppliers) {
                boolean hasItem = false;
                for (SupplierItems item : itemsList) {
                    if (item.getSupplier().getID().equals(supplier.getID())) {
                        hasItem = true;
                        break;
                    }
                }

                if (supplierId.equalsIgnoreCase(supplier.getID()) && hasItem) {
                    validSup = true;
                    chosenSupplier = supplier;

                    System.out.printf("\n\tSupplier ID: %s", supplier.getID());
                    System.out.printf("\n\tSupplier Name: %s", supplier.getSupplierName());
                    System.out.printf("\n\tCategory: %s", supplier.getCategory());
                    System.out.printf("\n\tCurrent Items:");

                    // Display current items for this supplier
                    for (SupplierItems item : itemsList) {
                        if (item.getSupplier().getID().equals(chosenSupplier.getID())) {
                            System.out.printf("\n\t%s     %-20s     RM%.2f", item.getStockID(), item.getName(), item.getPrice());
                        }
                    }
                    break;
                }
            }

            if (!validSup) {
                System.out.println("INVALID SUPPLIER ID or Supplier has no item(s) yet! Enter to try again or XXX to exit.");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("XXX")) {
                    SupplierItemsMain.SupplierItemsMenu();// Exit the method if user chooses to cancel
                }
            }
        } while (!validSup);

        // Step 2: Ask user to remove items
        boolean moreItemsToRemove = true;
        while (moreItemsToRemove) {
            ArrayList<SupplierItems> supplierItems = new ArrayList<>();

            // Step 2.1: Collect the items for the chosen supplier
            for (SupplierItems item : itemsList) {
                if (item.getSupplier().getID().equals(chosenSupplier.getID())) {
                    supplierItems.add(item);
                }
            }

            // Step 2.2: Ask the user to select an item to remove by ID
            System.out.println("\nEnter the item ID of the item to remove:");
            String stockIDToRemove = scanner.nextLine().trim();

            SupplierItems itemToRemove = null;
            for (SupplierItems item : supplierItems) {
                if (item.getStockID().equalsIgnoreCase(stockIDToRemove)) {
                    itemToRemove = item;
                    break;
                }
            }

            if (itemToRemove == null) {
                moreItemsToRemove = false;
                System.out.println("INVALID item ID. No item found with the given ID.");

            } else {
                System.out.printf("\nAre you sure you want to remove %s (%s)\n", itemToRemove.getName(), itemToRemove.getStockID());
                System.out.print("YES to confirm, any key to cancel: ");

                String confirmToRemove = scanner.nextLine().trim();
                if (confirmToRemove.equalsIgnoreCase("YES")) {
                    // Step 2.3: Remove the selected item from the list
                    itemsList.remove(itemToRemove);

                    System.out.println("\n***Item " + itemToRemove.getName() + " removed successfully.***");
                } else {
                    System.out.println("\n\tRemove cancelled....");

                }
            }

            // Step 3: Ask if the user wants to remove more items from this supplier
            System.out.print("\nDo you want to remove another item from this supplier? (Y/N): ");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("Y")) {
                moreItemsToRemove = true;
            } else {
                moreItemsToRemove = false;
            }
        }

        // Step 4: Write the updated items list back to the Stock.txt file
        writeItemsToFile(itemsList);
        SupplierItemsMain.SupplierItemsMenu();

    }

// Helper method to write items back to the file
    private static void writeItemsToFile(ArrayList<SupplierItems> itemsList) {
        String fileName = "Items.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (SupplierItems item : itemsList) {
                // Format: stockID | itemName | price | supplierID
                writer.write(item.getStockID() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getSupplier().getID());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SupplierItems> readItemsTextFile(ArrayList<Supplier> suppliersList) {
        ArrayList<SupplierItems> stocks = new ArrayList<>();
        String fileName = "Items.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Trim the line to remove leading and trailing whitespace
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line using the delimiter '|'
                String[] values = line.split("\\|");

                // Check if the line contains exactly 4 fields
                if (values.length == 4) {
                    // Extract values and parse them into the appropriate types
                    String stockID = values[0];
                    String name = values[1];
                    double price = Double.parseDouble(values[2]);  // Price is in the third column
                    String supplierID = values[3];                 // SupplierID is in the fourth column

                    // Find the supplier object based on supplierID
                    Supplier supplier = findSupplierByID(suppliersList, supplierID);

                    if (supplier != null) {
                        // Create SupplierItems object and add to the list
                        SupplierItems s = new SupplierItems(stockID, name, price, supplier);
                        stocks.add(s);
                    } else {
                        System.err.println("Supplier not found for ID: " + supplierID);
                    }
                } else {
                    System.err.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stocks;
    }

    public static String generateNextItemId(char type) {
        ArrayList<SupplierItems> stocks = readItemsTextFile(Supplier.readTextFile()); // Make sure this reads from the correct file
        String lastId = null;

        // Step 1: Determine the prefix based on the type
        String prefix = "";
        switch (type) {
            case 'F':
                prefix = "FD";  // Food supplier items
                break;
            case 'B':
                prefix = "BV";  // Beverage supplier items
                break;
            case 'I':
                prefix = "IG";  // Ingredient supplier items
                break;
            default:
                System.err.println("Invalid item type.");
                return null;
        }

        // Step 2: Find the last used item ID for the specified type
        for (SupplierItems stock : stocks) {
            if (stock.getStockID().startsWith(prefix)) {
                lastId = stock.getStockID();
            }
        }

        // Step 3: If no item was found for this type, start with the first ID for that type
        if (lastId == null) {
            return prefix + "001"; // Start with FD001, IG001, or BV001 if no IDs exist for this type
        }

        // Step 4: Extract the numeric part of the last ID and increment it
        int lastNumber = Integer.parseInt(lastId.substring(2)); // Extract the number after "FD", "IG", or "BV"
        String newId = String.format("%s%03d", prefix, lastNumber + 1); // Increment and format the new ID

        return newId;
    }
}
