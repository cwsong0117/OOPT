/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author User
 */
public class Validation {
    
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static boolean isValidDate(String dateStr) {
        //unified the date format
        try{
            LocalDate.parse(dateStr, DATE_FORMAT);
            return true;
        }catch(DateTimeParseException e) {
            return false;
        }
    }
    
    public static String getLocalDate() {
        return LocalDate.now().toString();
    }
    
    public static boolean isValidLocation(String location) {
        //the place of placing the inventory like aisle A followed by the shelf number 001
        return location.matches("^[A-Z]\\d{3}$"); //Regex to ensure 1 character and 3 digit
    }
    
    public static boolean isValidName(String name, int n) {
        if(n == 1) {
            StockAddFood[] foods = StockFile.readFood();
            for(StockAddFood f : foods) {
                if(f.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        else if(n == 2) {
            StockAddBeverage[] beverages = StockFile.readBeverage();
            for(StockAddBeverage b : beverages) {
                if(b.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        else if(n == 3) {
            StockAddIngredient[] ingredients = StockFile.readIngredient();
            for(StockAddIngredient i : ingredients) {
                if(i.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isValidName(String name) {
        //only character
        //no digit and special character
        return name.matches("^[A-Za-z]+$");
    }
    
    public static boolean isNotNullOrEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
