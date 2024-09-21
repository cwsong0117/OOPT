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
    public static void functionMenu() {

        Scanner scanner = new Scanner(System.in);
        StockMenu stockMenu = new StockMenu();
        PurchaseOrderMain po = new PurchaseOrderMain();
        SupplierMain supplier = new SupplierMain();
        actionTransportation transportation = new actionTransportation();
        actionBranches branch = new actionBranches();
        actionShipment shipment = new actionShipment();
        
        Boolean running = true;
        while(running) {
            System.out.println("Cincai Warehouse Management System");
            System.out.println("1. Stock Management");//add stock, update stock, remove stock, view stock level, search stock
            System.out.println("2. Purchcase Order Management");//Create new order, Update order status(Processing, Shipped, Delivered, etc.), Cancel Order, view order history
            System.out.println("3. Supplier Management");//Add new supplier, update supplier information, remove supplier, view supplier list
            System.out.println("4. Shipment Management");
            System.out.println("5. Transportation Management");
            System.out.println("6. Branch Management");
            System.out.println("7. LOG OUT");//Quit the whole program
            System.out.print("Your Option > ");
            int option = scanner.nextInt();

            switch(option) {
                case 1:
                    System.out.println("\nStock Management\n");
                    StockMenu.stockMenu();
                    break;
                case 2:
                    System.out.println("\nPurchcase Order Management\n");
                    po.PurchaseOrderMenu();
                    break;
                case 3:
                    supplier.SupplierMenu();
                    break;
                case 4:
                    shipment.shipmentMenu();
                    break;
                case 5:
                    transportation.menu();    
                    break;
                case 6:
                    branch.menu();    
                    break;
                case 7:
                    System.out.println("You're quit...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option...");
                    return;
            }
        }
    }
    
}
