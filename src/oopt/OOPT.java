/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oopt;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class OOPT {

    /**
     * @param args the command line arguments
     */
    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        StockMenu stockMenu = new StockMenu();
        Boolean running = true;
        while(running) {
            System.out.println("Cincai Warehouse Management System");
            System.out.println("1. Stock Management");//add stock, update stock, remove stock, view stock level, search stock
            System.out.println("2. Order Management");//Create new order, Update order status(Processing, Shipped, Delivered, etc.), Cancel Order, view order history
            System.out.println("3. Supplier Management");//Add new supplier, update supplier information, remove supplier, view supplier list
            System.out.println("4. Report");//Overview of stock flow.
            System.out.println("5. EXIT");//Quit the whole program
            System.out.print("Your Option > ");
            int option = scanner.nextInt();

            switch(option) {
                case 1:
                    System.out.println("\nStock Management");
                    StockMenu.stockMenu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("You're quit...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;
            }
        }
        System.exit(0);
    }
    
}
