/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class StockIDGenerator {
    private static final String FOOD_FILE = "Food.txt";
    private static final String BEVERAGE_FILE = "Beverage.txt";
    private static final String INGREDIENT_FILE = "Ingredient.txt";

    public static String generateFoodID() {
        return generateID("FD", FOOD_FILE);
    }

    public static String generateBeverageID() {
        return generateID("BV", BEVERAGE_FILE);
    }

    public static String generateIngredientID() {
        return generateID("IG", INGREDIENT_FILE);
    }

    private static String generateID(String prefix, String fileName) {
        int uniqueNumber = getLastIDNumber(fileName) + 1;
        return prefix + String.format("%03d", uniqueNumber); // 3 digits, padded with zeros
    }

    private static int getLastIDNumber(String fileName) {
        int lastNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Pattern pattern = Pattern.compile("^" + getPrefix(fileName) + "(\\d+)");
            Matcher matcher;
            while ((line = reader.readLine()) != null) {
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    if (number > lastNumber) {
                        lastNumber = number;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastNumber;
    }

    private static String getPrefix(String fileName) {
        if (fileName.equals(FOOD_FILE)) return "FD";
        if (fileName.equals(BEVERAGE_FILE)) return "BV";
        if (fileName.equals(INGREDIENT_FILE)) return "IG";
        return "";
    }
}
