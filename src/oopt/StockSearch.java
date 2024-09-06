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
public class StockSearch {
    
    public static void searchMenu(int opt) {
        //opt represent the category as parameter that pass to this method
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        StockMenu stockMenu = new StockMenu();
        
        while(running) {
        
            System.out.println("Search By :");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Supplier");
            System.out.println("4. Expiry Date");
            System.out.println("5. Arrival Date");
            System.out.println("6. Location");
            System.out.println("7. Exit");
            System.out.print("Option > ");
            int option = scanner.nextInt();
            
            System.out.printf("\n\n");
        
            switch(option) {
            
                //pass the value into the method
                case 1:
                    searchByID(opt);
                    break;
                case 2:
                    searchByName(opt);
                    break;
                case 3:
                    searchBySupplier(opt);
                    break;
                case 4:
                    searchByExpiryDate(opt);
                    break;
                case 5:
                    searchByArrivalDate(opt);
                    break;
                case 6:
                    searchByLocation(opt);
                    break;
                case 7:
                    System.out.println("Exiting...\n\n");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option...Please enter again...\n\n");
                    break;
            }
        }
        stockMenu.stockMenu();
    }
    
    public static String searchByID(int n) {
        //n means the number entered by user,food, beverage or ingredient
        //search and display the relevant id
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        Display d = new Display();
        
        System.out.print("Enter the ID : ");
        String id = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockAddFood f : foods) {
                if(f.getStockID().equalsIgnoreCase(id)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockAddBeverage b : beverages) {
                if(b.getStockID().equalsIgnoreCase(id)) {
                    System.out.println(b.toString());
                    found = true;
                }
            }
        }else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockAddIngredient i : ingredients) {
                if(i.getStockID ().equalsIgnoreCase(id)) {
                    System.out.println(i.toString());
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("No Such Supplier in file.\n");
        }
        operationAfterSearch();
        
        return id;
    }
    
    public static void searchByName(int n) {
    
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Name : ");
        String name = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockAddFood f : foods) {
                if(f.getName().equalsIgnoreCase(name)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockAddBeverage b : beverages) {
                if(b.getName().equalsIgnoreCase(name)) {
                    System.out.println(b.toString());
                    found = true;
                }
            }
        }else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockAddIngredient i : ingredients) {
                if(i.getName().equalsIgnoreCase(name)) {
                    System.out.println(i.toString());
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("No Such Supplier in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchBySupplier(int n) {
        
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Supplier : ");
        String supplier = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockAddFood f : foods) {
                if(f.getSupplier().equalsIgnoreCase(supplier)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockAddBeverage b : beverages) {
                if(b.getSupplier().equalsIgnoreCase(supplier)) {
                    System.out.println(b.toString());
                    found = true;
                }
            }
        }else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockAddIngredient i : ingredients) {
                if(i.getSupplier().equalsIgnoreCase(supplier)) {
                    System.out.println(i.toString());
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("No Such Supplier in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchByExpiryDate(int n) {
    
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Expiry Date : ");
        String expiryDate = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockAddFood f : foods) {
                if(f.getExpiryDate().equalsIgnoreCase(expiryDate)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockAddBeverage b : beverages) {
                if(b.getExpiryDate().equalsIgnoreCase(expiryDate)) {
                    System.out.println(b.toString());
                    found = true;
                }
            }
        }else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockAddIngredient i : ingredients) {
                if(i.getExpiryDate().equalsIgnoreCase(expiryDate)) {
                    System.out.println(i.toString());
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("No Such Expiry Date in file.\n");
        }
        operationAfterSearch();
    }
        
    public static void searchByArrivalDate(int n) {
        
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Arrival Date : ");
        String arrivalDate = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockAddFood f : foods) {
                if(f.getArrivalDate().equalsIgnoreCase(arrivalDate)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockAddBeverage b : beverages) {
                if(b.getArrivalDate().equalsIgnoreCase(arrivalDate)) {
                    System.out.println(b.toString());
                    found = true;
                }
            }
        }else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockAddIngredient i : ingredients) {
                if(i.getArrivalDate().equalsIgnoreCase(arrivalDate)) {
                    System.out.println(i.toString());
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("No Such Arrival Date in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void searchByLocation(int n) {
        
        Scanner scanner = new Scanner(System.in);
        StockFile file = new StockFile();
        boolean found = false;
        
        System.out.print("Enter the Location : ");
        String location = scanner.nextLine();
        
        Display display = new Display();
        if(n == 1) {
            StockAddFood[] foods = file.readFood();
            
            display.displayFoodTitle();
        
            for(StockAddFood f : foods) {
                if(f.getLocation().equalsIgnoreCase(location)) {
                    System.out.println(f.toString());
                    found = true;
                }
            }
        }else if(n == 2) {
            StockAddBeverage[] beverages = file.readBeverage();
            
            display.displayBeverageTitle();
        
            for(StockAddBeverage b : beverages) {
                if(b.getLocation().equalsIgnoreCase(location)) {
                    System.out.println(b.toString());
                    found = true;
                }
            }
        }else if(n == 3) {
            StockAddIngredient[] ingredients = file.readIngredient();
            
            display.displayIngredientTitle();
        
            for(StockAddIngredient i : ingredients) {
                if(i.getLocation().equalsIgnoreCase(location)) {
                    System.out.println(i.toString());
                    found = true;
                }
            }
        }
        if(!found) {
            System.out.println("No Such Location in file.\n");
        }
        operationAfterSearch();
    }
    
    public static void operationAfterSearch() {
        
        Scanner scanner = new Scanner(System.in);
        StockMenu stockMenu = new StockMenu();
        
        System.out.println("1. Update Stock Details");
        System.out.println("2. Exit");
        System.out.print("Option > ");
        int option = scanner.nextInt();
        
        switch(option) {
            case 1:
                stockMenu.updateStockMenu();
                break;
            case 2:
                stockMenu.stockMenu();
                break;
            default:
                System.out.println("Invalid Option!Please try again...\n");
        }
    }
}
