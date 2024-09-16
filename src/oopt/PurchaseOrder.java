/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

/**
 *
 * @author User
 */
public class PurchaseOrder {

    private String POid;
    private String supplier;
    private ArrayList<String> productOrder;
    private ArrayList<Double> quantities;
    private ArrayList<Double> pricePerUnit;
    private double totalPrice;
    private Date orderDate;
    private String paymentMethod;

//constructor
    public PurchaseOrder(String supplier, ArrayList<String> productOrder, ArrayList<Double> quantities,
            ArrayList<Double> pricePerUnit, String paymentMethod) {
        this.POid = generateNextPOId();
        this.supplier = supplier;
        this.productOrder = productOrder;
        this.quantities = quantities;
        this.pricePerUnit = pricePerUnit;
        this.paymentMethod = paymentMethod;
        this.totalPrice = countTotal(quantities, pricePerUnit);  // Calculate total price
        this.orderDate = new Date();
    }

    public PurchaseOrder(String POid, String supplier, ArrayList<String> productOrder, ArrayList<Double> quantities,
            ArrayList<Double> pricePerUnit, double totalPrice, Date orderDate, String paymentMethod) {
        this.POid = POid;
        this.supplier = supplier;
        this.productOrder = productOrder;
        this.quantities = quantities;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod; // Set the payment method
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PurchaseOrder() {
        orderDate = new Date();
        this.POid = generateNextPOId();
    }

    private static int lastPOnum = 0;
    private static final String PO_PREFIX = "PO-";

    private static String generateNextPOId() {
        String highestPOId = null;

        try (BufferedReader br = new BufferedReader(new FileReader("PurchaseOrder.txt"))) {
            String line;

            // Read through the file to find the highest PO ID
            while ((line = br.readLine()) != null) {
                String currentPOId = line.split("/")[0]; // Extract PO ID from each line
                if (highestPOId == null || currentPOId.compareTo(highestPOId) > 0) {
                    highestPOId = currentPOId;
                }
            }

            // If there's an existing PO ID, parse and increment it
            if (highestPOId != null) {
                lastPOnum = Integer.parseInt(highestPOId.substring(3)); // Extract the numeric part
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lastPOnum++;  // Increment the last PO number
        return PO_PREFIX + String.format("%05d", lastPOnum);  // Format as PO-00001, PO-00002, etc.
    }

    public double countTotal(ArrayList<Double> quantities, ArrayList<Double> pricePerUnit) {
        double total = 0.0;
        for (int i = 0; i < quantities.size(); i++) {
            total += quantities.get(i) * pricePerUnit.get(i);
        }
        return total;
    }

    public void addProduct(String productOrd, double quantity, double price) {
        // Java will automatically convert (autobox) the primitive double to Double
        productOrder.add(productOrd);
        quantities.add(quantity);  // Autoboxing: double to Double
        pricePerUnit.add(price);   // Autoboxing: double to Double

        // Update the total price
        totalPrice += quantity * price;
    }

    //getter
    public String getPOid() {
        return POid;
    }

    public String getSupplier() {
        return supplier;
    }

    public ArrayList<String> getProducts() {
        return productOrder;
    }

    public ArrayList<Double> getQuantities() {
        return quantities;
    }

    public ArrayList<Double> getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

//setter
    public void setPOid(String POid) {
        this.POid = POid;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setProducts(ArrayList<String> productOrder) {
        this.productOrder = productOrder;
    }

    public void setQuantities(ArrayList<Double> quantities) {
        this.quantities = quantities;
    }

    public void setPricePerUnit(ArrayList<Double> pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public static ArrayList<PurchaseOrder> readPurchaseOrdersFromFile() {
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<>();
        String filePath = "PurchaseOrder.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");

                // Check if the data array has enough elements
                if (data.length >= 8) {  // Minimum expected fields (POid, SupplierID, at least 1 product, quantity, price, total price, order date, payment method)
                    String POid = data[0];
                    String supplierID = data[1];

                    ArrayList<String> productOrder = new ArrayList<>();
                    ArrayList<Double> quantities = new ArrayList<>();
                    ArrayList<Double> pricePerUnit = new ArrayList<>();

                    // Parse product, quantity, and price data
                    int i = 2; // Start from index 2 (products start here)
                    while (i < data.length - 3) {  // Stop before the last 3 fields (TotalPrice, OrderDate, and PaymentMethod)
                        String product = data[i];
                        double quantity = Double.parseDouble(data[++i]);
                        double price = Double.parseDouble(data[++i]);

                        productOrder.add(product);
                        quantities.add(quantity);
                        pricePerUnit.add(price);

                        i++;  // Move to the next product
                    }

                    // Extract total price, order date, and payment method
                    double totalPrice = Double.parseDouble(data[data.length - 3]);
                    SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
                    Date orderDate = sdf.parse(data[data.length - 2]);
                    String paymentMethod = data[data.length - 1];

                    // Create and add the PurchaseOrder object to the list
                    PurchaseOrder purchaseOrder = new PurchaseOrder(POid, supplierID, productOrder, quantities, pricePerUnit, totalPrice, orderDate, paymentMethod);
                    purchaseOrders.add(purchaseOrder);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return purchaseOrders;
    }

    public static void displayAllpo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("***********All Purchases***********\n");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<PurchaseOrder> purchaseOrders = readPurchaseOrdersFromFile();
        for (PurchaseOrder po : purchaseOrders) {
            System.out.println("Purchase Order ID: " + po.getPOid());
            System.out.println("Supplier ID: " + po.getSupplier());
            System.out.printf("%-20s %-10s %-10s %-10s\n", "Items", "Quantity", "Unit Cost", "Total Paid");

            // Print products, quantities, and prices
            ArrayList<String> products = po.getProducts();
            ArrayList<Double> quantities = po.getQuantities();
            ArrayList<Double> pricePerUnit = po.getPricePerUnit();

            for (int i = 0; i < products.size(); i++) {
                System.out.printf("%-20s %-10.2f x %-10.2f %-10.2f%n\n", products.get(i),
                        quantities.get(i), pricePerUnit.get(i), quantities.get(i) * pricePerUnit.get(i));
            }

            System.out.printf("%40s   %-15.2f\n", "Net Total: ", po.getTotalPrice());
            String formattedDate = sdf.format(po.getOrderDate());
            System.out.printf("%30s\n", formattedDate);
            System.out.printf("%20s : %s\n", "Payment Method", po.getPaymentMethod());
            System.out.println("------------------------------------------------------");  // Print an empty line for better readability between orders
        }

        System.out.print("\n\t\tPress Enter to continue...\n\n");
        scan.nextLine();  // Wait for the user to press Enter
        PurchaseOrderMain.PurchaseOrderMenu();
    }

    public static void addPurchaseOrderMenu() {
        System.out.println("Category:");
        System.out.println("1. Ingredients");
        System.out.println("2. Food");
        System.out.println("3. Beverages");

        boolean validInput = false;
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.print("\nEnter choice (or type 'XXX' to exit): ");
            String userInput = input.nextLine().trim();  // Read the entire line and trim any whitespace

            if (userInput.equalsIgnoreCase("XXX")) {
                // Exit the method and return to the main menu
                System.out.println("Exiting to the main menu...");
                PurchaseOrderMain.PurchaseOrderMenu();  // Redirect to the main menu
                return;  // Exit the current method
            }

            try {
                choice = Integer.parseInt(userInput);  // Try to parse the input as an integer

                // Validate the choice range (1-3)
                if (choice >= 1 && choice <= 3) {
                    validInput = true;  // Valid input, break the loop
                } else {
                    System.out.println("INVALID CHOICE! Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                System.out.println("INVALID INPUT! Please enter a numeric value.");
            }
        } while (!validInput);

        String chooseSupplier;
        boolean supplierHasStock = false;

        ArrayList<Supplier> suppliers = Supplier.readTextFile();
        ArrayList<SupplierStock> stocks = SupplierStock.readStockTextFile();

        switch (choice) {
            case 1:
                // Display Ingredients suppliers
                for (Supplier j : suppliers) {
                    if (j.getID().charAt(0) == 'I') {
                        supplierHasStock = false;

                        // Check if this supplier has any stocks
                        for (SupplierStock i : stocks) {
                            if (j.getID().equalsIgnoreCase(i.getSupplier())) {
                                supplierHasStock = true;
                                break;  // Stop checking further stocks for this supplier
                            }
                        }

                        // Print supplier details only if they have stocks
                        if (supplierHasStock) {
                            System.out.println("\n\t\tSupplier ID: " + j.getID());
                            System.out.println("\t\tSupplier Name: " + j.getSupplierName());
                            System.out.println("\t\tCategory: " + j.getCategory());
                            System.out.println("\t\t----------------------------");
                        }
                    }
                }
                break;

            case 2:
                // Display Food suppliers
                for (Supplier j : suppliers) {
                    if (j.getID().charAt(0) == 'F') {
                        supplierHasStock = false;

                        // Check if this supplier has any stocks
                        for (SupplierStock i : stocks) {
                            if (j.getID().equalsIgnoreCase(i.getSupplier())) {
                                supplierHasStock = true;
                                break;  // Stop checking further stocks for this supplier
                            }
                        }

                        // Print supplier details only if they have stocks
                        if (supplierHasStock) {
                            System.out.println("\n\t\tSupplier ID: " + j.getID());
                            System.out.println("\t\tSupplier Name: " + j.getSupplierName());
                            System.out.println("\t\tCategory: " + j.getCategory());
                            System.out.println("\t\t----------------------------");
                        }
                    }
                }
                break;

            case 3:
                // Display Beverages suppliers
                for (Supplier j : suppliers) {
                    if (j.getID().charAt(0) == 'B') {
                        supplierHasStock = false;

                        // Check if this supplier has any stocks
                        for (SupplierStock i : stocks) {
                            if (j.getID().equalsIgnoreCase(i.getSupplier())) {
                                supplierHasStock = true;
                                break;  // Stop checking further stocks for this supplier
                            }
                        }

                        // Print supplier details only if they have stocks
                        if (supplierHasStock) {
                            System.out.println("\n\t\tSupplier ID: " + j.getID());
                            System.out.println("\t\tSupplier Name: " + j.getSupplierName());
                            System.out.println("\t\tCategory: " + j.getCategory());
                            System.out.println("\t\t----------------------------");
                        }
                    }
                }
                break;
        }

        boolean validSupplier = false;
        do {
            // Prompt for supplier selection
            System.out.print("\nSelect one supplier to make a Purchase Order (enter supplier ID): ");
            chooseSupplier = input.nextLine();
            String relevantSupp = "A000";
            boolean valid = false;
            for (Supplier j : suppliers) {
                for (SupplierStock i : stocks) {
                    if (i.getSupplier().equalsIgnoreCase(chooseSupplier)) {
                        if (i.getSupplier().equalsIgnoreCase(j.getID())) {
                            validSupplier = true;
                            valid = true;
                            relevantSupp = i.getSupplier();
                        }
                    }
                }
            }

            if (valid) {
                System.out.println("************");
                System.out.printf("%-10s %-16s %-30s\n", "ID", "Items", "Price");
                for (SupplierStock i : stocks) {
                    if (i.getSupplier().equalsIgnoreCase(chooseSupplier)) {
                        System.out.printf("%-10s %-15s  %-10.2f\n", i.getStockID(), i.getName(), i.getPrice());
                    }
                }
                // Call addPurchaseOrder method and pass the relevant category
                addPurchaseOrder(relevantSupp);
            } else {
                System.out.println("INVALID SUPPLIER ID!");
                System.out.print("Any key to try again or 'XXX' to cancel current order and quit: ");
                String choose = input.nextLine();
                if (choose.equalsIgnoreCase("XXX")) {
                    System.out.println("\t\t**Left make Purchase Order's tab**");
                    PurchaseOrderMain.PurchaseOrderMenu();
                    return;
                }
            }
        } while (!validSupplier);

    }

    public static void addPurchaseOrder(String relevant) {

        ArrayList<SupplierStock> stocks = SupplierStock.readStockTextFile();
        Scanner scan = new Scanner(System.in);

        boolean found = false;  // Start with false, indicating item not found
        String continueAdd = "no";
        String supId = null;

        ArrayList<String> productOrder = new ArrayList<>();
        ArrayList<Double> quantities = new ArrayList<>();
        ArrayList<Double> pricePerUnit = new ArrayList<>();

        do {
            System.out.println("\t\t**Select your items to purchase**");
            System.out.print("Item's ID: ");
            String itemId = scan.nextLine().toUpperCase();

            boolean itemFound = false;

            for (SupplierStock i : stocks) {
                if (i.getStockID().equals(itemId) && i.getSupplier().equals(relevant)) {
                    itemFound = true;
                    found = true;
                    supId = i.getSupplier();  // Update the supplier ID
                    double itemQuantity = 0.0;
                    boolean correctQuantity = false;

                    do {
                        System.out.printf("You selected %s\n", i.getName());
                        System.out.print("Quantity: ");

                        try {
                            itemQuantity = scan.nextDouble();
                            scan.nextLine();  // Consume newline

                            if (itemQuantity <= 0) {
                                System.out.println("Quantity must be at least 1");
                                System.out.print("(Yes to try again/XXX to leave, any key to exit): ");
                                continueAdd = scan.nextLine();
                                if (continueAdd.equalsIgnoreCase("XXX")) {
                                    System.out.println("\t\t**Left make Purchase Order's tab**");
                                    PurchaseOrderMain.PurchaseOrderMenu();
                                    return;
                                }
                            } else {
                                correctQuantity = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("INVALID INPUT! Please enter a numeric value.");
                            scan.nextLine();  // Consume the invalid input
                            System.out.print("(Yes to try again/XXX to leave): ");
                            continueAdd = scan.nextLine();
                            if (continueAdd.equalsIgnoreCase("XXX")) {
                                System.out.println("\t\t**Left make Purchase Order's tab**");
                                PurchaseOrderMain.PurchaseOrderMenu();
                                return;
                            }
                        }
                    } while (!correctQuantity);

                    // Add data to the lists
                    productOrder.add(i.getName());
                    quantities.add(itemQuantity);
                    pricePerUnit.add(i.getPrice());

                    System.out.println("Add more items?");
                    System.out.print("Yes/Any key: ");
                    continueAdd = scan.nextLine();
                    break;  // Exit the loop after processing the current item
                }
            }

            if (!itemFound) {
                System.out.println("No such item found...");
                boolean validInput = false;
                do {
                    System.out.print("(Yes to try again/XXX to leave): ");
                    continueAdd = scan.nextLine().trim();  // Trim any extra spaces

                    if (continueAdd.equalsIgnoreCase("yes")) {
                        validInput = true;  // Break the loop and continue adding items
                    } else if (continueAdd.equalsIgnoreCase("XXX")) {
                        System.out.println("\t\t**Left make Purchase Order's tab**");
                        PurchaseOrderMain.PurchaseOrderMenu();
                        return;  // Exit the method and return to the main menu
                    } else {
                        System.out.println("INVALID INPUT! Please type 'Yes' to try again or 'XXX' to leave.");
                    }
                } while (!validInput);  // Keep looping until a valid input is provided
            }

        } while (continueAdd.equalsIgnoreCase("yes"));

        // Proceed to payment method selection if items were added
        if (found) {
            // Prompt for payment method
            String payMethod = "none";
            boolean validChoice = false;

            final String STAFF_PASSWORD = "staff123";

            do {
                System.out.println("\n\t\tSelect a payment method:");
                System.out.println("\t\t1. Online Banking");
                System.out.println("\t\t2. E-wallet\n");
                System.out.print("\t\tSelect number: ");

                try {
                    int pMethodChoice = scan.nextInt();
                    scan.nextLine();  // Consume newline

                    switch (pMethodChoice) {
                        case 1:
                            payMethod = "Online Banking";
                            validChoice = true;

                            break;
                        case 2:
                            payMethod = "E-wallet";
                            validChoice = true;

                            break;
                        default:
                            System.out.println("INVALID CHOICE! Please select either 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("INVALID INPUT! Please enter a numeric value.");
                    scan.nextLine();  // Consume the invalid input
                }

                if (!validChoice) {
                    System.out.print("Any key to try again or type 'XXX' to quit and cancel the purchase order: ");
                    String choose = scan.nextLine();
                    if (choose.equalsIgnoreCase("XXX")) {
                        System.out.println("\t\t**Left make Purchase Order's tab**");
                        PurchaseOrderMain.PurchaseOrderMenu();
                        return;
                    }
                }
            } while (!validChoice);

            boolean passwordValid = false;
            int attempts = 3;

            do {
                System.out.print("\nEnter staff password (or type 'XXX' to quit): ");
                String enteredPassword = scan.nextLine();

                if (enteredPassword.equalsIgnoreCase("XXX")) {
                    System.out.println("\t\t**Left make Purchase Order's tab**");
                    PurchaseOrderMain.PurchaseOrderMenu();
                    return;
                } else if (enteredPassword.equals(STAFF_PASSWORD)) {
                    passwordValid = true;
                    System.out.println("\t*****Purchase order added successfully.*****\n\n");
                    System.out.print("Press Enter to continue...\n\n");
                    scan.nextLine();  // Wait for the user to press Enter
                } else {
                    attempts--;
                    if (attempts > 0) {
                        System.out.println("INCORRECT PASSWORD! You have " + attempts + " attempt(s) left.");
                    } else {
                        System.out.println("Too many failed attempts. Returning to menu...");
                        PurchaseOrderMain.PurchaseOrderMenu();
                        return;
                    }
                }
            } while (!passwordValid && attempts > 0);

            // Create the PurchaseOrder object and write to file if password is valid
            if (passwordValid) {
                PurchaseOrder p = new PurchaseOrder(supId, productOrder, quantities, pricePerUnit, payMethod);
                appendPurchaseOrderToFile(p, "PurchaseOrder.txt");
                PurchaseOrderMain.PurchaseOrderMenu();
            }
        }

    }

    public static void appendPurchaseOrderToFile(PurchaseOrder p, String filename) {
        try (FileWriter bw = new FileWriter(filename, true)) { // 'true' for append mode
            bw.write(p.toFormattedString() + "\n");
            // Add a newline after the formatted string
        } catch (IOException e) {
            e.printStackTrace();
        }

        PurchaseOrderMain.PurchaseOrderMenu();
    }

    public String toFormattedString() {
        // Format date and time
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        String formattedDate = sdf.format(orderDate);

        // Build the string
        StringBuilder sb = new StringBuilder();
        sb.append(POid).append("/")
                .append(supplier);

        for (int i = 0; i < productOrder.size(); i++) {
            sb.append("/").append(productOrder.get(i))
                    .append("/").append(quantities.get(i))
                    .append("/").append(pricePerUnit.get(i));
        }

        sb.append("/").append(totalPrice)
                .append("/").append(formattedDate)
                .append("/").append(paymentMethod);

        return sb.toString();
    }

    public static void cancelPurchaseOrder() {
        ArrayList<PurchaseOrder> purchaseOrders = readPurchaseOrdersFromFile(); // Read existing purchase orders from the file
        Scanner scan = new Scanner(System.in);

        boolean POexist = false;

        do {
            System.out.print("Enter Purchase Order ID that you want to cancel: ");
            String cancelID = scan.nextLine();

            boolean found = false;

            for (PurchaseOrder po : purchaseOrders) {
                if (cancelID.equalsIgnoreCase(po.getPOid())) {
                    found = true;
                    POexist = true;
                    System.out.println("Purchase Order found! Current details:\n\n");
                    System.out.println(po.toString());

                    System.out.println("\nAre you sure you want to cancel this purchase order? (Y to remove, any key to cancel remove)");
                    String confirm = scan.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        purchaseOrders.remove(po);
                        System.out.println("\nPurchase Order: " + po.getPOid() + " cancelled successfully.");
                        System.out.println("PO Cancellation message sent to supplier. Wait for refund");
                        break;
                    } else {
                        System.out.println("Cancelation aborted.");
                        System.out.print("\n\t\tPress Enter to continue...\n\n");
                        scan.nextLine();  // Wait for the user to press Enter
                        PurchaseOrderMain.PurchaseOrderMenu();
                    }
                }
            }

            if (!found) {
                System.out.println("\n**Purchase Order ID not found.....");
                System.out.println("Any key to try again or Type 'XXX' to quit and the purchase order will be cancel");
                System.out.print("X or Any key:");
                String choose = scan.nextLine();
                if (choose.equalsIgnoreCase("XXX")) {
                    System.out.println("\t\t**Left Cancel Purchase Order's tab**");
                    PurchaseOrderMain.PurchaseOrderMenu();
                    return;
                }
            } else {
                // Write the updated list back to the file
                writePurchaseOrdersToFile(purchaseOrders, "PurchaseOrder.txt");
                System.out.print("\n\t\tPress Enter to continue...\n\n");
                scan.nextLine();  // Wait for the user to press Enter
                PurchaseOrderMain.PurchaseOrderMenu();
            }
        } while (!POexist);
    }

    public static void writePurchaseOrdersToFile(ArrayList<PurchaseOrder> purchaseOrders, String filename) {
        try (FileWriter bw = new FileWriter(filename)) {
            for (PurchaseOrder p : purchaseOrders) {
                bw.write(p.toFormattedString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(orderDate);

        // Build the product details string (iterating through ArrayLists)
        StringBuilder productsInfo = new StringBuilder();
        for (int i = 0; i < productOrder.size(); i++) {
            productsInfo.append(String.format("%-10s %.2f x %.2f\n",
                    productOrder.get(i), quantities.get(i), pricePerUnit.get(i)));
        }

        // Return the formatted string
        return String.format("Purchase Order ID: %s" + "\nSupplier: %s" + "\nItems:\n" + "%s" + "\nNet Total: %.2f\n"
                + "\nOrder Date: %s" + "\n\t\tPaid by %s", POid, supplier, productsInfo.toString(), totalPrice, formattedDate, paymentMethod);
    }
}
