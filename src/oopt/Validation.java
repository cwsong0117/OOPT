/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Validation {
    
    public static boolean validate(String name, int n) {
        if(n == 1) {
            StockFood[] foods = StockFile.readFood();
            for(StockFood f : foods) {
                if(f.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        else if(n == 2) {
            StockBeverage[] beverages = StockFile.readBeverage();
            for(StockBeverage b : beverages) {
                if(b.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        else if(n == 3) {
            StockIngredient[] ingredients = StockFile.readIngredient();
            for(StockIngredient i : ingredients) {
                if(i.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isNotNullOrEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    public static boolean validate(String supplier) {
        Supplier readSupplier = new Supplier();
        ArrayList<Supplier> suppliers = readSupplier.readTextFile();
        
        for(Supplier s : suppliers) {
            if(s.getSupplierName().equalsIgnoreCase(supplier)) {
                return true;
            }
        }
        return false;
    }
}
