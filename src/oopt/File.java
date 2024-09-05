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
import java.util.List;

/**
 *
 * @author User
 */
public class File {
    public static void appendFood(AddFood food) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Food.txt", true))){
            writer.append(
                    food.getStockID() + "|" +
                    food.getName() + "|" +
                    food.getQuantity() + "|" + 
                    food.getPrice() + "|" + 
                    food.getSupplier() + "|" +
                    food.getExpiryDate() + "|" +
                    food.getArrivalDate() + "|" + 
                    food.getLocation() + "|" +
                    food.getIsOrganic() + "|" +
                    food.getAllergens() + "|" +
                    food.getStorageTemperature());
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static AddFood[] readFood() {
        //read only then create a array to store the arrayList from the text file
        List<AddFood> foods = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Food.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 11) {
                    try{
                        String stockID = parts[0];
                        String name = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        String supplier = parts[4];
                        String expiryDate = parts[5];
                        String arrivalDate = parts[6];
                        String location = parts[7];
                        String isOrganic = parts[8];
                        String allergens = parts[9];
                        double storageTemperature = Double.parseDouble(parts[10]);
                        
                        AddFood food = new AddFood(stockID, name, quantity, price, 
                                supplier, expiryDate, arrivalDate,
                                location, isOrganic, allergens, storageTemperature);
                        foods.add(food);
                    }catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return foods.toArray(new AddFood[0]);
    }
    
    public static void writeFood(AddFood[] foods) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Food.txt", false))){
            for(AddFood food : foods) {
                writer.write(
                    food.getStockID() + "|" +
                    food.getName() + "|" +
                    food.getQuantity() + "|" + 
                    food.getPrice() + "|" + 
                    food.getSupplier() + "|" +
                    food.getExpiryDate() + "|" +
                    food.getArrivalDate() + "|" + 
                    food.getLocation() + "|" +
                    food.getIsOrganic() + "|" +
                    food.getAllergens() + "|" +
                    food.getStorageTemperature());
            writer.newLine();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void appendBeverage(AddBeverage beverage) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Beverage.txt", true))){
            writer.append(
                    beverage.getStockID() + "|" +
                    beverage.getName() + "|" +
                    beverage.getQuantity() + "|" + 
                    beverage.getPrice() + "|" + 
                    beverage.getSupplier() + "|" +
                    beverage.getExpiryDate() + "|" +
                    beverage.getArrivalDate() + "|" + 
                    beverage.getLocation() + "|" +
                    beverage.getAlcoholContent() + "|" +
                    beverage.getIsCarbonated() + "|" +
                    beverage.getVolume());
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static AddBeverage[] readBeverage() {
        //read only then create a array to store the arrayList from the text file
        List<AddBeverage> beverages = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Beverage.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 11) {
                    try{
                        String stockID = parts[0];
                        String name = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        String supplier = parts[4];
                        String expiryDate = parts[5];
                        String arrivalDate = parts[6];
                        String location = parts[7];
                        double alcoholContent = Double.parseDouble(parts[8]);
                        String isCarbonated = parts[9];
                        int volume = Integer.parseInt(parts[10]);
                        
                        AddBeverage beverage = new AddBeverage(stockID, name, quantity, price, 
                                supplier, expiryDate, arrivalDate,
                                location, alcoholContent, isCarbonated, volume);
                        beverages.add(beverage);
                    }catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return beverages.toArray(new AddBeverage[0]);
    }
    
    public static void writeBeverage(AddBeverage[] beverages) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Beverage.txt", false))){
            for(AddBeverage beverage : beverages) {
                writer.write(
                    beverage.getStockID() + "|" +
                    beverage.getName() + "|" +
                    beverage.getQuantity() + "|" + 
                    beverage.getPrice() + "|" + 
                    beverage.getSupplier() + "|" +
                    beverage.getExpiryDate() + "|" +
                    beverage.getArrivalDate() + "|" + 
                    beverage.getLocation() + "|" +
                    beverage.getAlcoholContent() + "|" +
                    beverage.getIsCarbonated() + "|" +
                    beverage.getVolume());
            writer.newLine();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void appendIngredient(AddIngredient ingredient) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ingredient.txt", true))){
            writer.append(
                    ingredient.getStockID() + "|" +
                    ingredient.getName() + "|" +
                    ingredient.getQuantity() + "|" + 
                    ingredient.getPrice() + "|" + 
                    ingredient.getSupplier() + "|" +
                    ingredient.getExpiryDate() + "|" +
                    ingredient.getArrivalDate() + "|" + 
                    ingredient.getLocation() + "|" +
                    ingredient.getIngredientType() + "|" +
                    ingredient.getGluten() + "|" +
                    ingredient.getCalory());
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static AddIngredient[] readIngredient() {
        //read only then create a array to store the arrayList from the text file
        List<AddIngredient> ingredients = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Ingredient.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 11) {
                    try{
                        String stockID = parts[0];
                        String name = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        String supplier = parts[4];
                        String expiryDate = parts[5];
                        String arrivalDate = parts[6];
                        String location = parts[7];
                        String ingredientType = parts[8];
                        String gluten = parts[9];
                        int calory = Integer.parseInt(parts[10]);
                        
                        AddIngredient ingredient = new AddIngredient(stockID, name, quantity, price, 
                                supplier, expiryDate, arrivalDate,
                                location, ingredientType, gluten, calory);
                        ingredients.add(ingredient);
                    }catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return ingredients.toArray(new AddIngredient[0]);
    }
    
    public static void writeIngredient(AddIngredient[] ingredients) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ingredient.txt", false))){
            for(AddIngredient ingredient : ingredients) {
                writer.write(
                    ingredient.getStockID() + "|" +
                    ingredient.getName() + "|" +
                    ingredient.getQuantity() + "|" + 
                    ingredient.getPrice() + "|" + 
                    ingredient.getSupplier() + "|" +
                    ingredient.getExpiryDate() + "|" +
                    ingredient.getArrivalDate() + "|" + 
                    ingredient.getLocation() + "|" +
                    ingredient.getIngredientType() + "|" +
                    ingredient.getGluten() + "|" +
                    ingredient.getCalory());
            writer.newLine();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
