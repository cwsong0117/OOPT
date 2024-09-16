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
public class StockFile {
    public static void appendFood(StockAddFood food) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Food.txt", true))){
            writer.append(
                    food.getStockID() + "|" +
                    food.getName() + "|" +
                    food.getQuantity() + "|" + 
                    food.getPrice() + "|" + 
                    food.getSupplier() + "|" +
                    food.getIsOrganic() + "|" +
                    food.getAllergens() + "|" +
                    food.getStorageTemperature());
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static StockAddFood[] readFood() {
        //read only then create a array to store the arrayList from the text file
        List<StockAddFood> foods = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Food.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 8) {
                    try{
                        String stockID = parts[0];
                        String name = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        String supplier = parts[4];
                        String isOrganic = parts[5];
                        String allergens = parts[6];
                        double storageTemperature = Double.parseDouble(parts[7]);
                        
                        StockAddFood food = new StockAddFood(stockID, name, quantity, price, 
                                supplier, isOrganic, allergens, storageTemperature);
                        foods.add(food);
                    }catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return foods.toArray(new StockAddFood[0]);
    }
    
    public static void writeFood(StockAddFood[] foods) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Food.txt", false))){
            for(StockAddFood food : foods) {
                writer.write(
                    food.getStockID() + "|" +
                    food.getName() + "|" +
                    food.getQuantity() + "|" + 
                    food.getPrice() + "|" + 
                    food.getSupplier() + "|" +
                    food.getIsOrganic() + "|" +
                    food.getAllergens() + "|" +
                    food.getStorageTemperature());
            writer.newLine();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void appendBeverage(StockAddBeverage beverage) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Beverage.txt", true))){
            writer.append(
                    beverage.getStockID() + "|" +
                    beverage.getName() + "|" +
                    beverage.getQuantity() + "|" + 
                    beverage.getPrice() + "|" + 
                    beverage.getSupplier() + "|" +
                    beverage.getAlcoholContent() + "|" +
                    beverage.getIsCarbonated() + "|" +
                    beverage.getVolume());
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static StockAddBeverage[] readBeverage() {
        //read only then create a array to store the arrayList from the text file
        List<StockAddBeverage> beverages = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Beverage.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 8) {
                    try{
                        String stockID = parts[0];
                        String name = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        String supplier = parts[4];
                        double alcoholContent = Double.parseDouble(parts[5]);
                        String isCarbonated = parts[6];
                        int volume = Integer.parseInt(parts[7]);
                        
                        StockAddBeverage beverage = new StockAddBeverage(stockID, name, quantity, price, 
                                supplier, alcoholContent, isCarbonated, volume);
                        beverages.add(beverage);
                    }catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return beverages.toArray(new StockAddBeverage[0]);
    }
    
    public static void writeBeverage(StockAddBeverage[] beverages) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Beverage.txt", false))){
            for(StockAddBeverage beverage : beverages) {
                writer.write(
                    beverage.getStockID() + "|" +
                    beverage.getName() + "|" +
                    beverage.getQuantity() + "|" + 
                    beverage.getPrice() + "|" + 
                    beverage.getSupplier() + "|" +
                    beverage.getAlcoholContent() + "|" +
                    beverage.getIsCarbonated() + "|" +
                    beverage.getVolume());
            writer.newLine();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void appendIngredient(StockAddIngredient ingredient) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ingredient.txt", true))){
            writer.append(
                    ingredient.getStockID() + "|" +
                    ingredient.getName() + "|" +
                    ingredient.getQuantity() + "|" + 
                    ingredient.getPrice() + "|" + 
                    ingredient.getSupplier() + "|" +
                    ingredient.getIngredientType() + "|" +
                    ingredient.getGluten() + "|" +
                    ingredient.getCalory());
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static StockAddIngredient[] readIngredient() {
        //read only then create a array to store the arrayList from the text file
        List<StockAddIngredient> ingredients = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Ingredient.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 8) {
                    try{
                        String stockID = parts[0];
                        String name = parts[1];
                        int quantity = Integer.parseInt(parts[2]);
                        double price = Double.parseDouble(parts[3]);
                        String supplier = parts[4];
                        String ingredientType = parts[5];
                        String gluten = parts[6];
                        int calory = Integer.parseInt(parts[7]);
                        
                        StockAddIngredient ingredient = new StockAddIngredient(stockID, name, quantity, price, 
                                supplier, ingredientType, gluten, calory);
                        ingredients.add(ingredient);
                    }catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return ingredients.toArray(new StockAddIngredient[0]);
    }
    
    public static void writeIngredient(StockAddIngredient[] ingredients) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ingredient.txt", false))){
            for(StockAddIngredient ingredient : ingredients) {
                writer.write(
                    ingredient.getStockID() + "|" +
                    ingredient.getName() + "|" +
                    ingredient.getQuantity() + "|" + 
                    ingredient.getPrice() + "|" + 
                    ingredient.getSupplier() + "|" +
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
