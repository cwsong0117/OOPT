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
public class Ingredient extends Stock{
    
    private String ingredientType;
    private String gluten;
    private int calory;
    
    public Ingredient() {
        super();
        this.ingredientType = ingredientType;
        this.gluten = gluten;
        this.calory = calory;
    }
    
    public Ingredient(String stockID, String name, int quantity, double price, 
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
        return String.format("%s %-11s %-10s %-15d",
                super.toString(), // Calls the toString() from Stock
                ingredientType, gluten, calory);
    }
    
    public static void ingredientIn() {
    
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
                        
            Stock stock = stockIn();
            File file = new File();
                
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
            
            Ingredient ingredient = new Ingredient(stock.getStockID(), stock.getName(), stock.getQuantity(),
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
        Display.displayIngredient();
    }
    
    public static Stock stockIn() {
    
        Scanner scanner = new Scanner(System.in);

        System.out.print("Stock ID : ");
        String stockID = scanner.nextLine();
        
        System.out.print("Name : ");
        String name = scanner.nextLine();

        System.out.print("Quantity : ");
        int quantity = scanner.nextInt();
        
        scanner.nextLine();

        System.out.print("Price : ");
        double price = scanner.nextDouble();
        
        scanner.nextLine();

        System.out.print("Supplier : ");
        String supplier = scanner.nextLine();

        System.out.print("Expiry Date : ");
        String expiryDate = scanner.nextLine();
        
        System.out.print("Arrival Date : ");
        String arrivalDate = scanner.nextLine();
        
        System.out.print("Location : ");
        String location = scanner.nextLine();
        
        return new Stock(stockID, name, quantity, price, supplier, expiryDate, arrivalDate, location);
        
    }
}
