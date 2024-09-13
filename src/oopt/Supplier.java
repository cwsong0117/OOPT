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
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class Supplier {

    private String supplierName;
    private String supplierID;
    private String contact;
    private String email;
    private String category;

    
    public Supplier(){};

    public Supplier(String supplierName, String supplierID, String contact, String email, String category) {
        this.supplierName = supplierName;
        this.supplierID = supplierID;
        this.contact = contact;
        this.email = email;
        this.category = category;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getID() {
        return supplierID;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getCategory() {
        return category;
    }

    //setter
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

   public static String generateNextSupplierId(char type) {
    ArrayList<Supplier> suppliers = readTextFile(); // Read existing suppliers from the file
    String lastId = null;
    
    // Find the last used supplier ID for the specified type
    for (Supplier s : suppliers) {
        if (s.getID().charAt(0) == type) {
            lastId = s.getID();
        }
    }
    
    // If no supplier was found, start with the first ID for that type
    if (lastId == null) {
        return type + "001"; // Start with F001, I001, or B001
    }
    
    // Extract the numeric part of the last ID and increment it
    int lastNumber = Integer.parseInt(lastId.substring(1));
    String newId = String.format("%s%03d", type, lastNumber + 1);
    
    return newId;
}


    public static ArrayList<Supplier> readTextFile() {
        ArrayList<Supplier> supply = new ArrayList<>();
        String fileName = "Suppliers.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Split the line using the delimiter '/'
                String[] values = line.split("/");

                // Check if the line contains exactly 5 fields
                if (values.length == 5) {
                    // Extract values
                    String supplierName = values[0];
                    String supplierID = values[1];
                    String contactNumber = values[2];
                    String email = values[3];
                    String category = values[4];

                    // Create Supplier object and add to the list
                    Supplier s = new Supplier(supplierName, supplierID, contactNumber, email, category);
                    supply.add(s);
                } else {
                    System.err.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return supply;
    }

    public static void displayAllsupplier() {
        ArrayList<Supplier> suppliers = readTextFile();
        Scanner scan = new Scanner(System.in);

        System.out.printf("%-10s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Contact NO.", "Email", "Category");
        System.out.println("-------------------------------------------------------------------------------------");

        // Display the data
        for (Supplier s : suppliers) {
            if (s.getID().charAt(0) == 'I') {
                System.out.printf("%-10s %-20s %-15s %-25s %-10s\n", s.getID(), s.getSupplierName(), s.getContact(),
                        s.getEmail(), s.getCategory());
            }
        }
        for (Supplier s : suppliers) {
            if (s.getID().charAt(0) == 'F') {
                System.out.printf("%-10s %-20s %-15s %-25s %-10s\n", s.getID(), s.getSupplierName(), s.getContact(),
                        s.getEmail(), s.getCategory());
            }
        }
        for (Supplier s : suppliers) {
            if (s.getID().charAt(0) == 'B') {
                System.out.printf("%-10s %-20s %-15s %-25s %-10s\n", s.getID(), s.getSupplierName(), s.getContact(),
                        s.getEmail(), s.getCategory());
            }
        }
        System.out.print("\n\t\tPress Enter to continue...\n\n");
        scan.nextLine();
        SupplierMain.SupplierMenu();
    }

    public static void AddSupplier() {
        Scanner scan = new Scanner(System.in);
        char type = ' ';
        boolean validChoice = false;

        // Ask the user to choose the type of products the supplier supplies
        do {
            System.out.println("\nWhat kind of products does the supplier supply?");
            System.out.println("1. Ingredient");
            System.out.println("2. Food");
            System.out.println("3. Beverages");

            try {
                System.out.print("Kinds: ");
                int kinds = scan.nextInt();
                scan.nextLine();  // Consume newline
                validChoice = true;

                switch (kinds) {
                    case 1:
                        type = 'I';  // Ingredient
                        break;
                    case 2:
                        type = 'F';  // Food
                        break;
                    case 3:
                        type = 'B';  // Beverages
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        validChoice = false;
                }
            } catch (InputMismatchException e) {
                validChoice = false;
                System.out.println("\nInvalid input. Please enter a numeric value.");
                scan.nextLine();  // Consume invalid input
                System.out.print("(Any key to try again/XXX to leave): ");
                String option = scan.nextLine();
                if (option.equalsIgnoreCase("XXX")) {
                    SupplierMain.SupplierMenu();
                    return;
                }
            }
        } while (!validChoice);

        // Automatically generate supplier ID based on the chosen type
        String supplierID = generateNextSupplierId(type);

        // Gather remaining supplier information from the user
        String name = "";
        boolean validName = false;
        do {
            System.out.print("Enter Supplier Name: ");
            name = scan.nextLine();
            
            if(name.equalsIgnoreCase("XXX")){
                SupplierMain.SupplierMenu();  // Exit and return to the main menu
                return;
            }

            if (!name.isBlank() && !name.isEmpty()) {
                validName = true;
            } else {
                System.out.println("\nPls enter the company name of the supplier, cannot be empty");
                System.out.println("\t\t(XXX) during input to stop and cancel the process");
                System.out.println("Enter to continue.....");
                scan.nextLine();
            }

        } while (!validName);
        
//contact number
        String contact = "";
        boolean validContact = false;
        do {
            System.out.print("Enter Contact Number (format: 012-3456789 or 03-12345678): ");
            contact = scan.nextLine();

            if (contact.equalsIgnoreCase("XXX")) {
                SupplierMain.SupplierMenu();  // Exit and return to the main menu
                return;
            }

            // Regex to validate the contact number format
            String contactRegex = "^\\d{2,3}-\\d{7,8}$";
            Pattern pattern = Pattern.compile(contactRegex);
            Matcher matcher = pattern.matcher(contact);

            if (matcher.matches()) {
                validContact = true;
            } else {
                System.out.println("\nInvalid contact number format. Please enter a valid number (e.g., 012-3456789 or 03-12345678).");
                System.out.println("\t\t(XXX) during input to stop and cancel the process");
                System.out.println("Enter to continue.....");
                scan.nextLine();
            }
        } while (!validContact);

         // Validate email
        String email = "";
        boolean validEmail = false;
        do {
            System.out.print("Enter Email (format: xxxx@xxxxx.com.my or xxxx@xxxxx.com): ");
            email = scan.nextLine();

            if (email.equalsIgnoreCase("XXX")) {
                SupplierMain.SupplierMenu();  // Exit and return to the main menu
                return;
            }

            // Regex to validate email format for both .com and .com.my
            String emailRegex = "^[\\w\\d]+@[\\w\\d]+\\.com(\\.my)?$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()) {
                validEmail = true;
            } else {
                System.out.println("\nInvalid email format. Please enter a valid email (e.g., john@company.com or john@company.com.my).");
                System.out.println("\t\t(XXX) during input to stop and cancel the process");
                System.out.println("Enter to continue.....");
                scan.nextLine();
            }
        } while (!validEmail);

        //Category/description
        String category = "";
        boolean validCat = false;
        do{
        System.out.print("Enter Category/Description: ");
        category = scan.nextLine();
        
        if (category.equalsIgnoreCase("XXX")) {
                SupplierMain.SupplierMenu();  // Exit and return to the main menu
                return;
            }
        
        if (!category.isBlank() && !category.isEmpty()) {
                validCat = true;
            } else {
                System.out.println("\nPls enter the company name of the supplier, cannot be empty");
                System.out.println("\t\t(XXX) during input to stop and cancel the process");
                System.out.println("Enter to continue.....");
                scan.nextLine();
            }
        
        }while(!validCat);
        
        Supplier newSupp = new Supplier(name, supplierID, contact, email, category);
        System.out.println("Are you sure you want to add this new supplier?");
        System.out.println(newSupp.toString());

        System.out.println("Yes to confirm, any key to cancel: ");
        String confirm = scan.nextLine();

        if (confirm.equalsIgnoreCase("YES")) {
            // Format the supplier data as "Name/ID/Contact/Email/Category"
            String supplierData = name + "/" + supplierID + "/" + contact + "/" + email + "/" + category;

            // Write the supplier data to the file
            try (FileWriter writer = new FileWriter("Suppliers.txt", true)) { // 'true' indicates appending to the file
                writer.write(supplierData + "\n"); // Write the formatted data followed by a new line
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

            System.out.println("\n\t*****Supplier added succesfully*****\n");
            System.out.print("Press Enter to continue...\n\n");
            scan.nextLine();  // Wait for the user to press Enter
            SupplierMain.SupplierMenu();  // Return to the Supplier menu
        } else {
            System.out.println("\n\t**Cancel new supplier**");
            System.out.print("Press Enter to continue...\n\n");
            scan.nextLine();  // Wait for the user to press Enter
            SupplierMain.SupplierMenu();  // Return to the Supplier menu
        }

    }

    public static void ModifySupplier() {
    ArrayList<Supplier> suppliers = readTextFile(); // Read existing suppliers from the file
    Scanner scan = new Scanner(System.in);

    boolean supplierExist = false; // Variable to track if a supplier was found
    
    do {
        System.out.print("\nEnter Supplier ID to modify (or type XXX to cancel): ");
        String searchID = scan.nextLine();

        if (searchID.equalsIgnoreCase("XXX")) {
            SupplierMain.SupplierMenu(); // Exit to main menu if "XXX" is entered
            return;
        }

        boolean found = false; // Variable to track if the current searchID matches a supplier

        // Search for the supplier with the given ID
        for (Supplier j : suppliers) {
            if (j.getID().equalsIgnoreCase(searchID)) {
                found = true;
                supplierExist = true;
                System.out.println("\nSupplier found! Current details:");
                System.out.println("*************************************");
                System.out.println(j.toString());

                // Modify Name
                String newName = "";
                boolean validName = false;
                do {
                    System.out.print("Enter new Name (leave blank to keep current): ");
                    newName = scan.nextLine();

                    if (newName.equalsIgnoreCase("XXX")) {
                        SupplierMain.SupplierMenu();  // Exit to main menu if "XXX" is entered
                        return;
                    }

                    if (newName.isEmpty()) {
                        validName = true; // Keep current value
                    } else if (!newName.isBlank()) {
                        validName = true;
                        j.setSupplierName(newName); // Update name
                    } else {
                        System.out.println("\nInvalid name. It cannot be just spaces.");
                        System.out.println("\t(XXX) during input to stop and cancel the process");
                        System.out.println("Enter to continue.....");
                        scan.nextLine();
                    }
                } while (!validName);

                // Modify Contact Number with validation
                String newContact = "";
                boolean validContact = false;
                do {
                    System.out.print("Enter new Contact Number (leave blank to keep current): ");
                    newContact = scan.nextLine();

                    if (newContact.equalsIgnoreCase("XXX")) {
                        SupplierMain.SupplierMenu();  // Exit to main menu if "XXX" is entered
                        return;
                    }

                    if (newContact.isEmpty()) {
                        validContact = true; // Keep current value
                    } else {
                        String contactRegex = "^\\d{2,3}-\\d{7,8}$";
                        if (newContact.matches(contactRegex)) {
                            validContact = true;
                            j.setContact(newContact); // Update contact
                        } else {
                            System.out.println("\nInvalid contact number format. Please use 012-3456789 or 03-12345678.");
                            System.out.println("\t(XXX) during input to stop and cancel the process");
                            System.out.println("Enter to continue.....");
                            scan.nextLine();
                        }
                    }
                } while (!validContact);

                // Modify Email with validation
                String newEmail = "";
                boolean validEmail = false;
                do {
                    System.out.print("Enter new Email (leave blank to keep current): ");
                    newEmail = scan.nextLine();

                    if (newEmail.equalsIgnoreCase("XXX")) {
                        SupplierMain.SupplierMenu();  // Exit to main menu if "XXX" is entered
                        return;
                    }

                    if (newEmail.isEmpty()) {
                        validEmail = true; // Keep current value
                    } else {
                        String emailRegex = "^[\\w\\d]+@[\\w\\d]+\\.com(\\.my)?$";
                        if (newEmail.matches(emailRegex)) {
                            validEmail = true;
                            j.setEmail(newEmail); // Update email
                        } else {
                            System.out.println("\nInvalid email format. Please enter a valid email (e.g., john@company.com or john@company.com.my).");
                            System.out.println("\t(XXX) during input to stop and cancel the process");
                            System.out.println("Enter to continue.....");
                            scan.nextLine();
                        }
                    }
                } while (!validEmail);

                // Modify Category
                String newCategory = "";
                boolean validCategory = false;
                do {
                    System.out.print("Enter new Category (leave blank to keep current): ");
                    newCategory = scan.nextLine();

                    if (newCategory.equalsIgnoreCase("XXX")) {
                        SupplierMain.SupplierMenu();  // Exit to main menu if "XXX" is entered
                        return;
                    }

                    if (newCategory.isEmpty()) {
                        validCategory = true; // Keep current value
                    } else if (!newCategory.isBlank()) {
                        validCategory = true;
                        j.setCategory(newCategory); // Update category
                    } else {
                        System.out.println("\nInvalid category. It cannot be just spaces.");
                        System.out.println("\t(XXX) during input to stop and cancel the process");
                        System.out.println("Enter to continue.....");
                        scan.nextLine();
                    }
                } while (!validCategory);

                // Display updated supplier details
                Supplier updatedSupp = new Supplier(j.getSupplierName(), j.getID(), j.getContact(), j.getEmail(), j.getCategory());
                System.out.println("\nAre you sure you want to update this supplier?");
                System.out.println(updatedSupp.toString() + "\n");

                // Confirmation prompt before saving changes
                System.out.print("Type 'yes' to confirm, any key to cancel: ");
                String confirm = scan.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    // Write the updated list back to the file
                    writeToFile(suppliers);
                    System.out.println("\n\t**Supplier details updated successfully!**");
                    System.out.println("Enter to continue.....");
                        scan.nextLine();
                    SupplierMain.SupplierMenu();
                } else {
                    System.out.println("\n\t**Update canceled.**");
                    System.out.println("Enter to continue.....");
                        scan.nextLine();
                    SupplierMain.SupplierMenu();
                }
                break;
            }
        }
        // If no supplier was found with the given ID
        if (!found) {
            System.out.println("\n\tSupplier ID not found! Please try again.");
            System.out.println("\t(XXX) during input to stop and cancel the process");
        }

    } while (!supplierExist); // Loop until a valid supplier is found or the user exits
}
 
    // Helper method to write the list of suppliers to the file
    private static void writeToFile(ArrayList<Supplier> suppliers) {
        String fileName = "Suppliers.txt";

        try (FileWriter writer = new FileWriter(fileName)) { // Overwrite the file
            for (Supplier supplier : suppliers) {
                // Format the supplier data and write to the file
                String supplierData = supplier.getSupplierName()+ "/" + supplier.getID() + "/" +
                                      supplier.getContact() + "/" + supplier.getEmail() + "/" +
                                      supplier.getCategory();
                writer.write(supplierData + "\n");
            }
            System.out.println("All changes saved to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static void RemoveSupplier() {
    ArrayList<Supplier> suppliers = readTextFile(); // Read existing suppliers from the file
    Scanner scan = new Scanner(System.in);
    
    boolean exist = false;
    
    do{
    System.out.println("Enter supplier ID that you want to remove");
    System.out.print("(F000, I000, B000): ");
    String deleteID = scan.nextLine();
    
    if (deleteID.equalsIgnoreCase("XXX")) {
        SupplierMain.SupplierMenu();
        return;
    }
    
    Supplier supplierToRemove = null;
    for (Supplier supplier : suppliers) {
        if (deleteID.equalsIgnoreCase(supplier.getID())) {
            exist = true;
            supplierToRemove = supplier;
            break;
        }
    }
    
    if (supplierToRemove != null) {
        System.out.println("Supplier found! Current details:");
        System.out.println("*************************************");
        System.out.println(supplierToRemove.toString());
        
        System.out.println("Are you sure you want to delete? (Y to delete, any key to cancel)");
        String confirm = scan.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) {
            suppliers.remove(supplierToRemove);
            System.out.println("Supplier deleted successfully.");
            writeToFile(suppliers);
        } else {
            System.out.println("Deletion canceled.");
        }
    } else {
        System.out.println("\n\t**Supplier ID not found....");
        System.out.println("\t(XXX) during insput to stop and cancel the process");
    }
    }while(!exist);
    
    System.out.println("Enter to continue.....");
    scan.nextLine();
    SupplierMain.SupplierMenu();
}


    @Override
    public String toString(){
        return String.format("Supplier ID: %s" + "\nSupplier Name: %s" + "\nContact NO. : %s" + "\nEmail: %s" + "\nCategory: %s\n" ,supplierID
        ,supplierName, contact, email, category);
    }
}
