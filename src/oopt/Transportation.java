package oopt;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Transportation implements Manage{

    SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
    private String plateNo;
    private int weight_capacity;
    private double net_weight;
    private String vehicle_status;
    private Date vehicle_license;
    public static double fuel_cost; //in RM

    //default
    public Transportation() {
        plateNo = "";
        weight_capacity = 0;
        net_weight = 0.0;
        vehicle_status = "Available";
        vehicle_license = new Date(); // current
    }

    public Transportation(String plateNo) {
        weight_capacity = 0;
        net_weight = 0.0;
        vehicle_status = "Available";
        vehicle_license = new Date();
        this.plateNo = plateNo;
    }

    public Transportation(String plateNo, int weight_capacity, double net_weight, String vehicle_status, Date vehicle_license) {
        this.plateNo = plateNo;
        this.weight_capacity = weight_capacity;
        this.net_weight = net_weight;
        this.vehicle_status = vehicle_status;
        this.vehicle_license = vehicle_license;
    }

    public Transportation(String plateNo, int weight_capacity, double net_weight, Date vehicle_license) {
        this.plateNo = plateNo;
        this.weight_capacity = weight_capacity;
        this.net_weight = net_weight;
        vehicle_status = "Available";
        this.vehicle_license = vehicle_license;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public int getWeight_capacity() {
        return weight_capacity;
    }

    public double getNet_weight() {
        return net_weight;
    }

    public String getVehicle_status() {
        return vehicle_status;
    }

    public Date getVehicle_license() {
        return vehicle_license;
    }

    public void setVehicle_license(Date vehicle_license) {
        this.vehicle_license = vehicle_license;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public void setWeight_capacity(int weight_capacity) {
        this.weight_capacity = weight_capacity;
    }

    public void setNet_weight(double net_weight) {
        this.net_weight = net_weight;
    }

    public void setVehicle_status(String vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    public static double getFuelCost() {
        return fuel_cost;
    }

    public static void setFuelCost(double price) {
        Transportation.fuel_cost = price;
    }

    //check vehicle license 
    public String license_status() {
        Date currentDate = new Date();
        // 1 is greater, 0 is less than or equal
        if (vehicle_license.compareTo(currentDate) == 1) {
            return "Valid";
        } else {
            return "Expired";
        }
    }

    //use in update
    public boolean validatePlateNo() {
        int count_num = 0;
        int count_letter = 0;

        for (int i = 0; i < plateNo.length(); i++) {
            if (Character.isDigit(plateNo.charAt(i)) == true) {
                count_num++;
            } else if (Character.isLetter(plateNo.charAt(i)) == true) {
                count_letter++;
            } else { //is special char:@!#
                System.out.println("Invalid plate number! Please try again.");
                return false;
            }
        }

        if (plateNo.length() > 8) {
            System.out.println("\nInvalid plate number! Please try again.");
            return false;
        } else if (count_num > 4 || count_num == 0) {
            System.out.println("\nInvalid plate number! Please try again.");
            return false;
        } else if (count_letter == 0 || count_letter > 4) {
            System.out.println("\nInvalid plate number! Please try again.");
            return false;
        } else {
            //check sequeces
            ArrayList<Integer> digit = new ArrayList<>();
            for (int i = 0; i < plateNo.length(); i++) {
                if (Character.isDigit(plateNo.charAt(i))) {
                    digit.add(i);
                }
            }

            //first number cannot be 0
            if (plateNo.charAt(digit.get(0)) == '0') {
                System.out.println("\nInvalid plate number! Please try again.");
                return false;
            }

            for (int i = 0; i < digit.size() - 1; i++) {
                if (!(digit.get(i) == digit.get(i + 1) - 1)) {
                    System.out.println("\nInvalid plate number! Please try again.");
                    return false;
                }
            }

            return true;
        }
    }

    public void display() {
        System.out.printf("| %-9s| %-,24d| %-,19.2f| %-18s| %-17s|\n", plateNo, weight_capacity, net_weight, vehicle_status, dateForm.format(vehicle_license));
        System.out.printf("| %-9s| %-24s| %-19s| %-18s| %-17s|\n", " ", " ", " ", " ", license_status());
    }

    @Override
    public String toString() {
        return plateNo + "|" + weight_capacity + "|" + net_weight + "|" + vehicle_status + "|" + dateForm.format(vehicle_license) + "|";
    }

    @Override
    public boolean equals(Object obj) {
        Transportation that = (Transportation) obj;
        return this.plateNo.equals(that.plateNo);
    }

}

class actionTransportation {

    private static String fileAddress = "Transportation.txt";
    private static ArrayList<Transportation> transportation = new ArrayList<Transportation>();
    public static SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");

    public static void menu() {
        int option;
        boolean exit = true;
        Scanner scanner = new Scanner(System.in);
        readFile();
        do {
            try {
                System.out.println("\n1. Add New Transportation");
                System.out.println("2. Update Transportation Information");
                System.out.println("3. Remove Transportation");
                System.out.println("4. List Out All Transportation");
                System.out.println("5. Exit");
                System.out.print("Enter your choose:");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        addTransportation();
                        break;
                    case 2:
                        updateTransportation();
                        break;
                    case 3:
                        removeTransportation();
                        break;
                    case 4:
                        displayAll();
                        break;
                    case 5:
                        exit = false;
                        break;
                    default:
                        System.out.println("Please enter 1-4!!");
                        break;

                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number 1-4");
                scanner.nextLine();
            }

        } while (exit);
        writeFile();
    }

    public static void readFile() {

        try {
            File file = new File(fileAddress);
            Scanner read = new Scanner(file);
            int line = 0;

            if (!transportation.isEmpty()) {
                transportation.clear();
            }

            while (read.hasNextLine()) {
                line++;
                if (line == 1) {
                    double fuel_price = Double.parseDouble(read.nextLine());

                    Transportation.setFuelCost(fuel_price);
                } else {
                    String data = read.nextLine();
                    String[] parts = data.split("\\|");

                    //convert to correct type of value
                    String plateNo = parts[0];
                    int weight_capacity = Integer.parseInt(parts[1]);
                    double net_weight = Double.parseDouble(parts[2]);
                    String vehicle_status = parts[3];
                    Date vehicle_license = dateForm.parse(parts[4]);

                    transportation.add(new Transportation(plateNo, weight_capacity, net_weight, vehicle_status, vehicle_license));
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
        } catch (ParseException ex) {
            System.out.println("Data in file is error");
        }

    }

    public static void addTransportation() {
        dateForm.setLenient(false);
        Scanner scanner = new Scanner(System.in);
        transportation.add(new Transportation());
        int index = transportation.size() - 1;
        String plateNo;
        boolean continueInput; //use for try and catch
        System.out.println("");
        do {
            continueInput = false;
            System.out.print("Enter Number Plate: ");
            plateNo = scanner.nextLine();
            plateNo = plateNo.replace(" ", "");
            plateNo = plateNo.toUpperCase();
            transportation.get(index).setPlateNo(plateNo);
            if (transportation.get(index).validatePlateNo()) {
                for (int i = 0; i < index; i++) {
                    if (transportation.get(index).equals(transportation.get(i))) {
                        continueInput = true;
                        System.out.println("\nPlate No. is repeated");
                    }
                }
            } else {
                continueInput = true;
            }

        } while (continueInput);

        do {
            continueInput = true;
            System.out.print("Enter Weight Capacity(in ton): ");
            try {

                int weight_capacity = scanner.nextInt();
                scanner.nextLine();
                continueInput = false;
                transportation.get(transportation.size() - 1).setWeight_capacity(weight_capacity);
            } catch (InputMismatchException ex) {
                System.out.println("Please enter integer number!!");
                scanner.nextLine();
            }
        } while (continueInput);

        do {
            continueInput = true;
            try {
                System.out.print("Enter Net Weight of vehicle(in ton): ");
                double net_weight = scanner.nextDouble();
                scanner.nextLine();
                continueInput = false;
                transportation.get(transportation.size() - 1).setNet_weight(net_weight);

            } catch (InputMismatchException ex) {
                System.out.println("Please enter number/float number!!");
                scanner.nextLine();
            }
        } while (continueInput);

        do {
            continueInput = true;
            try {

                System.out.print("Enter vehicle license expired date(DD/MM/YYYY): ");
                String date = scanner.nextLine();
                Date vehicle_license = dateForm.parse(date);
                transportation.get(transportation.size() - 1).setVehicle_license(vehicle_license);
                continueInput = false;
            } catch (ParseException e) {
                System.out.println("Please enter follow the format(DD/MM/YYYY)");
            }
        } while (continueInput);

    }

    public static void writeFile() {
        try {
            FileWriter write = new FileWriter(fileAddress);
            write.write(String.format("%.2f\n", Transportation.getFuelCost()));
            for (int i = 0; i < transportation.size(); i++) {
                write.write(transportation.get(i).toString() + "\n");
            }
            write.close();
        } catch (IOException e) {
            System.out.println("Error opening file!!");
        }
    }

    public static void updateTransportation() {
        dateForm.setLenient(false);
        if (transportation.isEmpty()) {
            System.out.println("Without any transportation record!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            int index = 0;
            boolean found;
            String plateNo;
            boolean continueInput;
            int option = 0;

            do {
                try {
                    System.out.println("Select which you want to Update:");
                    System.out.println("1. Fuel Price");
                    System.out.println("2. Transportation Information");
                    System.out.println("3. Exit");
                    System.out.print("Enter your option:");
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            do {

                                continueInput = true;
                                try {
                                    System.out.printf("Previous fuel price: RM%.2f\n", Transportation.getFuelCost());
                                    System.out.print("Enter Current fuel price(in RM/litre): ");
                                    double price = scanner.nextDouble();
                                    continueInput = false;
                                    Transportation.setFuelCost(price);
                                } catch (InputMismatchException e) {
                                    System.out.println("\nYour Input is invalid!!");
                                    scanner.nextLine();
                                }
                            } while (continueInput);
                            break;
                        case 2:
                            do {
                                System.out.print("Enter the plate No.(XXX to exit):");
                                plateNo = scanner.nextLine();
                                plateNo = plateNo.replace(" ", "");
                                plateNo = plateNo.toUpperCase();
                                found = false;
                                for (int i = 0; i < transportation.size(); i++) {
                                    if (transportation.get(i).equals(new Transportation(plateNo))) {
                                        index = i;
                                        found = true;
                                    }
                                }
                                if (found) {

                                    boolean exit = true;

                                    do {
                                        try {
                                            System.out.println("Select the option you want to update:");
                                            System.out.println("1. Plate No.");
                                            System.out.println("2. Weight Capacity");
                                            System.out.println("3. Net Weight");
                                            System.out.println("4. Vehicle Status");
                                            System.out.println("5. Expired Date of vehicle license");
                                            System.out.println("6. End to update information");
                                            System.out.print("Enter your option: ");
                                            option = scanner.nextInt();
                                            scanner.nextLine();

                                            switch (option) {
                                                case 1:
                                                    String plate,
                                                     currentPlate;
                                                    currentPlate = transportation.get(index).getPlateNo();
                                                    do {
                                                        continueInput = false;
                                                        transportation.get(index).setPlateNo(currentPlate);
                                                        System.out.println("Previous Plate No.: " + transportation.get(index).getPlateNo());
                                                        System.out.print("Enter new Plate No.: ");
                                                        plate = scanner.nextLine();
                                                        plate = plate.replace(" ", "");
                                                        plate = plate.toUpperCase();
                                                        transportation.get(index).setPlateNo(plate);
                                                        if (transportation.get(index).validatePlateNo()) {
                                                            for (int i = 0; i < transportation.size() - 1; i++) {
                                                                if (transportation.get(index).equals(transportation.get(i)) && i != index) {
                                                                    System.out.println("\nPlate No. is repeated");
                                                                    continueInput = true;
                                                                }
                                                            }
                                                        } else {
                                                            continueInput = true;
                                                        }
                                                        //user able to repeat the same plateNo with the current instance
                                                    } while (continueInput);
                                                    System.out.println("\nThe Information was updated.\n");
                                                    transportation.get(index).setPlateNo(plate);
                                                    break;

                                                case 2:

                                                    do {
                                                        continueInput = true;
                                                        try {
                                                            int weight_capacity;
                                                            System.out.println("Previous Weight Capacity: " + transportation.get(index).getWeight_capacity() + "(tonnes)");
                                                            System.out.print("Enter Weight Capacity(in ton): ");
                                                            weight_capacity = scanner.nextInt();
                                                            transportation.get(index).setWeight_capacity(weight_capacity);
                                                            continueInput = false;
                                                            System.out.println("The Information was updated.\n");
                                                        } catch (InputMismatchException e) {
                                                            scanner.nextLine();
                                                            System.out.println("\nInput invalid!!Please try again");
                                                        }
                                                    } while (continueInput);
                                                    break;
                                                case 3:

                                                    do {
                                                        continueInput = true;
                                                        try {
                                                            double net_weight;
                                                            System.out.println("Previous Net Weight: " + transportation.get(index).getNet_weight() + "(tonnes)");
                                                            System.out.print("Enter Net Weight(in ton): ");
                                                            net_weight = scanner.nextDouble();
                                                            transportation.get(index).setNet_weight(net_weight);
                                                            continueInput = false;
                                                            System.out.println("The Information was updated.\n");
                                                        } catch (InputMismatchException e) {
                                                            scanner.nextLine();
                                                            System.out.println("Input invalid!!Please try again");
                                                        }
                                                    } while (continueInput);
                                                    break;
                                                case 4:

                                                    do {
                                                        continueInput = true;
                                                        try {
                                                            int vehicle_status;
                                                            String[] status = {"Available", "Maintenance"};
                                                            System.out.println("Previous Status: " + transportation.get(index).getVehicle_status());
                                                            for (int i = 0; i < status.length; i++) {
                                                                System.out.println((i + 1) + " " + status[i]);
                                                            }

                                                            System.out.print("Select Current Status: ");
                                                            vehicle_status = scanner.nextInt();

                                                            if (vehicle_status > status.length || vehicle_status < 0) {
                                                                System.out.println("Please enter 1-" + status.length);
                                                            } else {
                                                                transportation.get(index).setVehicle_status(status[vehicle_status - 1]);
                                                                continueInput = false;
                                                            }
                                                        } catch (InputMismatchException e) {
                                                            scanner.nextLine();
                                                            System.out.println("Input invalid!!Please try again");
                                                        }
                                                    } while (continueInput);
                                                    break;

                                                case 5:
                                                    do {
                                                        continueInput = true;
                                                        try {
                                                            Date date;
                                                            System.out.println("Vehicle Licence status: " + transportation.get(index).license_status());
                                                            System.out.print("Enter vehicle license expired date(DD/MM/YYYY): ");
                                                            String license = scanner.nextLine();
                                                            date = dateForm.parse(license);
                                                            transportation.get(index).setVehicle_license(date);
                                                            System.out.println("Current License Status is " + transportation.get(index).license_status());
                                                            continueInput = false;
                                                            System.out.println("The Information was updated.\n");
                                                        } catch (ParseException e) {
                                                            System.out.println("\nPlease follow this format(DD/MM/YYYY)");
                                                        }
                                                    } while (continueInput);
                                                    break;
                                                case 6:
                                                    exit = false;
                                                    plateNo = "XXX";
                                                    break;
                                                default:
                                                    System.out.println("\nPlease enter 1-6! Please try again");
                                                    break;
                                            }

                                        } catch (InputMismatchException e) {
                                            System.out.println("\nPlease enter 1-6! Please try again");
                                            scanner.nextLine();
                                        }
                                    } while (exit);

                                } else if (!plateNo.equals("XXX")) {
                                    System.out.println("\nEntered plate No. didn't found!!\n");
                                }
                            } while (!plateNo.equals("XXX"));
                            break;

                        default:
                            System.out.println("\nPlease enter 1-3! Please try again.");
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input!!Please enter 1-3! Please try again.");
                    scanner.nextLine();
                }
            } while (option
                    != 3);
        }

    }

    public static void removeTransportation() {
        if (transportation.isEmpty()) {
            System.out.println("\nWithout any transportation record!!\n");
        } else {
            Scanner scanner = new Scanner(System.in);
            int index = 0;
            boolean found;
            boolean exit;
            String plateNo;

            do {
                exit = false;
                found = false;
                System.out.print("Enter Plate No. want to remove(XXX to exit): ");
                plateNo = scanner.nextLine();
                plateNo = plateNo.replace(" ", "");
                plateNo = plateNo.toUpperCase();
                for (int i = 0; i < transportation.size(); i++) {
                    if (transportation.get(i).equals(new Transportation(plateNo))) {
                        found = true;
                        index = i;
                    }
                }

                if (found) {
                    transportation.remove(index);
                    System.out.println(plateNo + " was removed!!");
                } else if (plateNo.equals("XXX")) {
                    exit = true;
                } else {
                    System.out.println("\n" + plateNo + " not found!!");
                }
            } while (!exit);
        }
    }

    public static void displayAll() {
        Scanner scanner = new Scanner(System.in);
        //plateNo + "|" + weight_capacity + "|" + net_weight + "|" + vehicle_status + "|" + dateForm.format(vehicle_license) + "|"
        System.out.println(" ________________________________________________________________________________________________");
        System.out.printf("| %-9s| %-24s| %-19s| %-18s| %-17s|\n", "Plate No", "Weight Capacity(tonnes)", "Net Weight(tonnes)", "Vehicle Status", "Vehicle License");
        System.out.println("|__________|_________________________|____________________|___________________|__________________|");
        for (Transportation tr : transportation) {
            tr.display();
            System.out.printf("| %-9s| %-24s| %-19s| %-18s| %-17s|\n", " ", " ", " ", " ", " ");
        }
        System.out.println("|__________|_________________________|____________________|___________________|__________________|");
        System.out.print("Enter to exit");
        scanner.nextLine();
    }

    public static String passTransportation(String plateNo) {
        Scanner scanner = new Scanner(System.in);
        readFile();
        Transportation transport = new Transportation();
        int count = 0;
        if (transportation.isEmpty()) {
            System.out.println("\nNo any transportation!!");

        } else {
            for (Transportation tr : transportation) {
                if (tr.license_status().equals("Valid") && tr.getVehicle_status().equals("Available")) {
                    count++;
                }
            }

            if (count == 0) {
                System.out.println("\nNo transportation license is Available!!");
            } else {
                do {
                    for (Transportation tr : transportation) {
                        if (plateNo.toUpperCase().replace(" ", "").equals(tr.getPlateNo())) {
                            transport = tr;
                        }
                    }

                    if (transport.getPlateNo().isEmpty()) {
                        System.out.println("\nPlease try again plate No. not found!!");
                        System.out.print("Enter transportation plate No.:");
                        plateNo = scanner.nextLine();
                        plateNo = plateNo.toUpperCase().replace(" ", "");
                    }
                } while (transport.getPlateNo().isEmpty());
            }
        }
        return transport.getPlateNo();
    }
    
    public static boolean findTransportation(String plateNo){
        readFile();
        for(Transportation tr: transportation){
            if(tr.getPlateNo().equals(plateNo)){
                return true;
            }
        }
        return false;
    }
    
}
