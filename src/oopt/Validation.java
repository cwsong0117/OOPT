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
}
