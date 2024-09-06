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
public class StockAddIngredient extends Stock{
    
    private String ingredientType;
    private String gluten;
    private int calory;
    
    public StockAddIngredient() {
        super();
        this.ingredientType = ingredientType;
        this.gluten = gluten;
        this.calory = calory;
    }
    
    public StockAddIngredient(String stockID, String name, int quantity, double price, 
            String supplier, String expiryDate, 
            String arrivalDate, String location, String ingredientType,
            String gluten, int calory) {
        super(stockID, name, quantity, price, supplier, expiryDate,
                arrivalDate, location);
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
        
        while(running) {
                        
            Stock stock = stockIn(num);
            StockFile file = new StockFile();
                
            System.out.print("Ingredient Type : ");
            String ingredientType = scanner.nextLine();
            
            System.out.print("Gluten?(yes/no) : ");
            String gluten = scanner.nextLine().toLowerCase();
            while (!gluten.equals("yes") && !gluten.equals("no")) {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
                gluten = scanner.nextLine().toLowerCase();
            }
            
            System.out.print("Calory : ");
            int calory = scanner.nextInt();
            
            scanner.nextLine();
            
            StockAddIngredient ingredient = new StockAddIngredient(stock.getStockID(), stock.getName(), stock.getQuantity(),
                                stock.getPrice(), stock.getSupplier(),
                                stock.getExpiryDate(), stock.getArrivalDate() ,stock.getLocation(),
                                ingredientType, gluten, calory);
            
            file.appendIngredient(ingredient);
            
            System.out.print("\n");
            System.out.println("You can press any key to stop.");
            System.out.print("Continue?('y' to yes) : ");
            String response = scanner.nextLine();
            
            if(!response.equalsIgnoreCase("y")) {
                running = false;
            }
        }
        System.out.println("\n");
        Display.displayStock(num);
    }
    
    public static StockAddIngredient stockIn(int num) {
    
        Scanner scanner = new Scanner(System.in);

        String stockID = StockIDGenerator.generateIngredientID();
        System.out.print("Stock ID : " + stockID + "\n");
        
        String name;
        do{
            System.out.print("Name : ");
            name = scanner.nextLine();
            if(name.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isValidName(name)) {
                System.out.println("Invalid Name format. Please try again.");
            }
            else if(!Validation.isValidName(name, num)) {
                System.out.println("Name already exists. Please select UPDATE or enter other name.");
            }
            else if(!Validation.isNotNullOrEmpty(name)) {
                System.out.println("Name cannot be empty. Please enter a name.");
            }
        }while(!Validation.isValidName(name, num) || !Validation.isValidName(name));

        int quantity = 0;
        do{
            System.out.print("Quantity : ");
            quantity = scanner.nextInt();
            if (quantity == -1) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
            }
            if(quantity <= 0) {
                System.out.println("The price cannot be zero or negative. Please enter a price");
            }
        }while(quantity <= 0);
        
        scanner.nextLine();

        String priceInput;
        double price = 0.0;
        do{
            System.out.print("Price : ");
            priceInput = scanner.nextLine();
            if(priceInput.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isNotNullOrEmpty(priceInput)) {
                System.out.println("Price cannot be empty.");
            }else{
                try{
                    price = Double.parseDouble(priceInput);
                    if(price <= 0.0) {
                        System.out.println("Price cannot be zero or negative.");
                    }
                }catch(NumberFormatException e) {
                    System.out.println("Invald price format. Please enter a valid price.");
                }
            }
        }while(price <= 0);
        
        String supplier;
        do{
            System.out.println("Supplier : ");
            supplier = scanner.nextLine();
            if(supplier.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isNotNullOrEmpty(supplier)) {
                System.out.println("Supplier cannot be empty. Please enter a supplier.");
            }
        }while(!Validation.isNotNullOrEmpty(supplier));
        
        String arrivalDate = Validation.getLocalDate();
        System.out.print("Arrival Date : " + arrivalDate + "\n");

        String expiryDate;
        do {
            System.out.print("Expiry Date(YYYY-MM-DD) : ");
            expiryDate = scanner.nextLine();
            if(expiryDate.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isValidDate(expiryDate)) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }while(!Validation.isValidDate(expiryDate));
        
        String location;
        do{
            System.out.print("Location : ");
            location = scanner.nextLine();
            if(location.equals("-1")) {
                System.out.println("Existing...\n");
                StockMenu.stockMenu();
                return null;
            }
            if(!Validation.isValidLocation(location)) {
                System.out.println("Invalid location format. Please enter again.");
                System.out.println("Example: A001");
            }
        }while(!Validation.isValidLocation(location));
        
        return new StockAddIngredient(stockID, name, quantity, price, supplier, expiryDate, arrivalDate, location, "", "no", 0);
        
    }
}
