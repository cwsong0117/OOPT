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
    public static void appendFood(Food food) {
    
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
    
    public static Food[] readFood() {
        
        //read only then create a array to store the arrayList from the text file
        List<Food> foods = new ArrayList<>();
        
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
                        
                        Food food = new Food(stockID, name, quantity, price, 
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
        return foods.toArray(new Food[0]);
    }
    
    public static void writeFood(Food[] foods) {
    
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Food.txt", false))){
            for(Food food : foods) {
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
}
