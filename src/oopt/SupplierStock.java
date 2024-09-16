/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class SupplierStock {
    private String stockID;
    private String name;
    private double price;
    private String supplier;


    public SupplierStock(String stockID, String name,double price, String supplier) {
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

    public String getSupplier() {
        return supplier;
    }
   
//setter
    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    
 public static ArrayList<SupplierStock> readStockTextFile() {
    ArrayList<SupplierStock> stocks = new ArrayList<>();
    String fileName = "Stock.txt";

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
                String supplier = values[3];                   // Supplier is in the fourth column

                // Create Stock object and add to the list
                SupplierStock s = new SupplierStock(stockID, name, price, supplier);
                stocks.add(s);
            } else {
                System.err.println("Invalid line: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return stocks;
}

 public static void addSupplierItems() {
    ArrayList<Supplier> suppliers = Supplier.readTextFile(); // Load suppliers from file
    ArrayList<SupplierStock> stockItems = readStockTextFile(); // Load existing stock items from file
    ArrayList<SupplierStock> newItems = new ArrayList<>(); // To collect added stock items
    Scanner input = new Scanner(System.in);

    boolean validSup = false;
    String suppId = "";
    String itemId = "";
    String itemName = "";
    double itemPrice = 0.0;
    
    System.out.println("\n\t\t***Select a Supplier to add new item(s)***");

    // Select a valid supplier
    do {
        System.out.print("\n\t\t\tSupplier ID: ");
        String searchId = input.nextLine();

        for (Supplier i : suppliers) {
            if (searchId.equalsIgnoreCase(i.getID())) {
                validSup = true;
                suppId = i.getID();

                System.out.printf("\n\tSupplier ID: %s", i.getID());
                System.out.printf("\n\tSupplier Name: %s", i.getSupplierName());
                System.out.printf("\n\tCategory: %s", i.getCategory());
                System.out.printf("\n\tCurrent Items(No items added yet if empty): ");

                // Display current items for this supplier
                for (SupplierStock j : stockItems) {
                    if (j.getSupplier().equalsIgnoreCase(suppId)) {
                        System.out.printf("\n\t%s     RM%.2f", j.getName(), j.getPrice());
                    }
                }

                // Generate the next item ID based on supplier category
                switch (i.getID().charAt(0)) {
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
        // Validate item name
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
                    SupplierMain.SupplierMenu();
                    return;
                }
            }
        } while (!validName);

        // Validate item price
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

        // Create a SupplierStock object
        SupplierStock newItem = new SupplierStock(itemId, itemName, itemPrice, suppId);
        newItems.add(newItem);

        // Ask if the user wants to add more items
        System.out.print("Do you want to add another item? (Y/N): ");
        String option = input.nextLine();
        if (!option.equalsIgnoreCase("Y")) {
            moreItems = false;
        } else {
            // Manually increment the item ID by extracting the number and adding 1
            int nextIdNumber = Integer.parseInt(itemId.substring(2)); // Extract the numeric part
            nextIdNumber++; // Increment it
            itemId = itemId.substring(0, 2) + String.format("%03d", nextIdNumber); // Generate next ID
        }
    }

    // Display newly added items and ask for confirmation
    System.out.println("\nNewly Added Items:");
    for (SupplierStock item : newItems) {
        System.out.printf("\n\tItem: %s     Unit Price: RM%.2f", item.getName(), item.getPrice());
    }
    System.out.print("\nConfirm adding these items? (Y/N): ");
    String confirm = input.nextLine();

    if (confirm.equalsIgnoreCase("Y")) {
        // Write new stock items to file if confirmed
        try (FileWriter writer = new FileWriter("Stock.txt", true)) {
            for (SupplierStock item : newItems) {
                writer.write(item.getStockID() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getSupplier() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("\n\t*****Supplier's Item(s) added successfully*****");
    } else {
        System.out.println("\n\tOperation cancelled. No items were added.");
    }

    System.out.print("Press Enter to continue...\n\n");
    input.nextLine(); // Wait for the user to press Enter
    SupplierMain.SupplierMenu(); // Return to the Supplier menu
}

 
  public static String generateNextItemId(char type) {
    ArrayList<SupplierStock> stocks = readStockTextFile();
    String lastId = null;

    // Determine the prefix based on the type
    String prefix = "";
        switch (type) {
            case 'F':
                prefix = "FD";
                break;
            case 'B':
                prefix = "BV";
                break;
            case 'I':
                prefix = "IG";
                break;
            default:
                break;
        }

    // Find the last used item ID for the specified type
    for (SupplierStock stock : stocks) {
        if (stock.getStockID().startsWith(prefix)) {
            lastId = stock.getStockID();
        }
    }

    // If no item was found, start with the first ID for that type
    if (lastId == null) {
        return prefix + "001"; // Start with FD001, IG001, or BV001
    }

    // Extract the numeric part of the last ID and increment it
    int lastNumber = Integer.parseInt(lastId.substring(2)); // Extract the number after "FD", "IG", or "BV"
    String newId = String.format("%s%03d", prefix, lastNumber + 1);

    return newId;
    }
}
