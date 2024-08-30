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
public class CategoryMenu {
    public static void categoryMenu() {
        
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        
        while(running) {
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Iegredient");
            System.out.println("4. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    System.out.println("Food Category Selected...");
                    Food.foodIn();
                    break;
                case 2:
                    System.out.println("Beverage Category Selected...");
                    //Beverage.beverageIn();
                    break;
                case 3:
                    System.out.println("Ingredient Category Selected...");
                    //Ingredient.ingredientIn();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;

            }
            return;
        }
        scanner.close();
    }
}
