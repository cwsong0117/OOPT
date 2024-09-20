
import java.util.InputMismatchException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Lee Jun Ting
 */
public class Branches {

    SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");

    private String branchID = "B00001"; //B00000
    private double distance;
    private String address;
    private String state;
    private int post_code;
    private Date dateCreated;
    
    public Branches() {
        dateCreated = new Date();
    }

    public Branches(String address, String state, int post_code) {
        this.address = address;
        this.state = state;
        this.post_code = post_code;
        dateCreated = new Date();
    }

    public Branches(String branchID, double distance, String address, String state, int post_code, Date dateCreated) {
        distance = Math.abs(distance);
        address = address.toUpperCase();

        this.branchID = branchID;
        this.distance = distance;
        this.address = address;
        this.state = state;
        this.post_code = post_code;
        this.dateCreated = dateCreated;
    }
    
    public Branches(String branchID){
        this.branchID = branchID;
        this.distance = 0;
        this.address = "";
        this.state = "";
        this.post_code = 0;
        this.dateCreated = new Date();
    }

    public void generateID(String lastId) {
        branchID = String.format("B%05d", Integer.parseInt(lastId.substring(1, 6)) + 1);
    }

    public String getBranchID() {
        return branchID;
    }

    public double getDistance() {
        return distance;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public int getPost_code() {
        return post_code;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    
    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDistance(double distance) {
        distance = Math.abs(distance);
        this.distance = distance;
    }

    public void setAddress(String address) {
        address = address.toUpperCase();
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }

    public boolean validateAddress() {
        int countNum = 0, count_letter = 0, count_comma = 0, countNon = 0;
        for (Character ch : address.toCharArray()) {
            if (Character.isLetter(ch)) {
                count_letter++;
            } else if (Character.isDigit(ch)) {
                countNum++;
            } else if (ch == ',') {
                count_comma++;
            } else {
                countNon++;
            }
        }

        return !(count_letter < 10 || countNon > countNum + count_letter || count_comma < 1);

    }

    public boolean validateDate() {
        Date currentDate = new Date();

        if (dateCreated.compareTo(currentDate) == 1) {
            return false;
        } else if (dateCreated.equals(currentDate)) {
            return true;
        } else {
            return true;
        }
    }

    public boolean validatePostCode() {
        return !(post_code < 0 || post_code >= 100000);
    }

    public void displayFormat() {
        String[] parts = address.split(",");
        parts[0] += ",";
        System.out.printf("| %-9s | %-30s | %-12s |   %05d   | %-13s |\n", branchID, parts[0], state, post_code, distance, dateForm.format(dateCreated));
        for (int i = 1; i < parts.length; i++) {
            if (i < parts.length - 1) {
                parts[i] += ",";
            }
            parts[i] = parts[i].trim();
            System.out.printf("| %-9s | %-30s | %-12s |   %5s   | %-13s |\n", " ", parts[i], " ", " ", " ", " ");
        }
        System.out.printf("| %-9s | %-30s | %-12s |   %-5s   | %-13s |\n", " ", " ", " ", " ", " ", " ");
        System.out.println("=========================================================================================");

    }

    @Override
    public String toString() {
        return String.format(branchID + "|" + distance + "|" + address + "|" + state + "|" + "%05d" + "|" + dateForm.format(dateCreated) + "|\n", post_code);
    }

    @Override
    public boolean equals(Object obj) {
        Branches that = (Branches) obj;
        return (this.address.replace(" ", "").toUpperCase().equals(that.address.replace(" ", "").toUpperCase()) && this.state.equals(that.state) && this.post_code == that.post_code);
    }

}

class actionBranches {

    public static SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
    private static String fileAddress = "Branches.txt";
    private static ArrayList<Branches> branches = new ArrayList<Branches>();

    public static void menu() {
        int option;
        boolean exit;
        Scanner scanner = new Scanner(System.in);
        readFile();

        do {
            exit = true;
            try {
                System.out.println("\n1. Add Branch");
                System.out.println("2. Update Branch Information");
                System.out.println("3. Remove Branch");
                System.out.println("4. Search Branch");
                System.out.println("5. Exit");
                System.out.print("Enter your choose: ");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addBranch();
                        break;
                    case 2:
                        modifyBranch();
                        break;
                    case 3:
                        removeBranch();
                        break;
                    case 4:
                        searchBranch();
                        break;
                    case 5:
                        exit = false;
                        break;
                    default:
                        System.out.println("\nPlease enter 1-5!!");
                        break;
                }

            } catch (InputMismatchException ex) {
                System.out.println("\nPlease enter 1-5!!");
                scanner.nextLine();
            }

        } while (exit);
        writeFile();
    }

    public static void writeFile() {
        try {
            FileWriter write = new FileWriter(fileAddress);
            for (Branches b : branches) {
                write.write(b.toString());
            }
            write.close();
        } catch (IOException e) {
            System.out.println("\nError opening file!!");
        }

    }

    public static void readFile() {
        try {
            File file = new File(fileAddress);
            Scanner read = new Scanner(file);

            if (!branches.isEmpty()) {
                branches.clear();
            }

            while (read.hasNextLine()) {
                String data = read.nextLine();
                String[] parts = data.split("\\|");

                //convert to correct type of value
                String branchID = parts[0];
                double distance = Double.parseDouble(parts[1]);
                String address = parts[2];
                String state = parts[3];
                int post_code = Integer.parseInt(parts[4]);
                Date dateCreated = dateForm.parse(parts[5]);

                branches.add(new Branches(branchID, distance, address, state, post_code, dateCreated));

            }
            read.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nError openning file!!");
        } catch (ParseException e) {
            System.out.println("\nInvalid file data");
        }
    }

    public static void addBranch() {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput; //use for try and catch
        branches.add(new Branches());
        int index = branches.size() - 1;

        if (index != 0) {
            branches.get(index).generateID(branches.get(index - 1).getBranchID());
        }

        System.out.println("\nNew Branch ID: " + branches.get(index).getBranchID());

        do {
            continueInput = true;
            try {
                String[] state = {"Kuala Lumpur", "Selangor", "Kuantan", "Putrajaya", "Petaling Jaya"};
                System.out.println("Select Branch State:");
                System.out.println("1. Kuala Lumpur");
                System.out.println("2. Selangor");
                System.out.println("3. Kuantan");
                System.out.println("4. Putrajaya");
                System.out.println("5. Petaling Jaya");
                System.out.print("Enter your option: ");
                int option = scanner.nextInt();
                scanner.nextLine();
                continueInput = false;
                branches.get(index).setState(state[option - 1]);

            } catch (InputMismatchException e) {
                System.out.println("\nYour input is invalid!!Please enter 1-5.");
                scanner.nextLine();
            } catch (Exception ex) {
                System.out.println("\nYour input is invalid!!Please enter 1-5.");
            }
        } while (continueInput);

        do {
            try {
                continueInput = true;
                System.out.print("Enter Post Code: ");
                int postCode = scanner.nextInt();
                scanner.nextLine();
                branches.get(index).setPost_code(postCode);
                //validate
                if (branches.get(index).validatePostCode()) {
                    continueInput = false;
                } else {
                    System.out.println("\nInvalid input!!Please try agian(exampler: 68000).");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input!!Please try agian(exampler: 68000).");
                scanner.nextLine();
            }

        } while (continueInput);

        do {
            //address
            do {
                System.out.println("Enter Branch Address:");
                String address = scanner.nextLine();
                branches.get(index).setAddress(address);
                //validate (count_letter < 10 || countNon > countNum + count_letter || count_comma < 1)
                if (!branches.get(index).validateAddress()) {
                    System.out.println("\nAddress is invalid!!Please try again.");
                    continueInput = true;
                } else {
                    continueInput = false;
                }

            } while (continueInput);

            continueInput = false;
            for (int i = 0; i < index; i++) {
                // check address, state, and post code
                if (branches.get(index).equals(branches.get(i))) {
                    continueInput = true;
                    System.out.println("\nThe address is repeated!!Please try again.");
                }
            }
        } while (continueInput);

        do {
            continueInput = true;
            try {
                System.out.print("Enter Distance Between Warehouse and Branch(in km): ");
                double distance = scanner.nextDouble();
                scanner.nextLine();
                branches.get(index).setDistance(Math.abs(distance));
                continueInput = false;
            } catch (InputMismatchException ex) {
                System.out.println("\nInvalid input! Please try again.");
                scanner.nextLine();
            }
        } while (continueInput);

        System.out.println("\nBranch ID: " + branches.get(index).getBranchID() + " is added!!");
    }

    public static void modifyBranch() {
        dateForm.setLenient(false);
        if (branches.isEmpty()) {
            System.out.println("Without any branch record!");

        } else {
            Scanner scanner = new Scanner(System.in);
            String id;
            int index = 0;
            boolean continueInput = true, exit = false, found = false;

            //find ID
            do {

                System.out.print("\nEnter Branch ID(XXX to exit): ");
                id = scanner.nextLine();
                id = id.toUpperCase();
                id = id.replace(" ", "");

                exit = id.equals("XXX");

                if (!exit) {
                    for (int i = 0; i < branches.size(); i++) {
                        if (branches.get(i).getBranchID().equals(id)) {
                            index = i;
                            found = true;
                        }
                    }
                    if (found) {
                        int option = 0;
                        do {
                            System.out.println("\nSelect which you want to modify:");
                            System.out.println("1. Distance");
                            System.out.println("2. Address");
                            System.out.println("3. Date of Creation");
                            System.out.println("4. Exit");
                            do {
                                continueInput = true;
                                try {

                                    System.out.print("Enter your option: ");
                                    option = scanner.nextInt();
                                    scanner.nextLine();
                                    continueInput = false;
                                } catch (InputMismatchException ex) {
                                    scanner.nextLine();
                                    System.out.println("\nInvalid Input!!Please Enter 1-5.");
                                }
                            } while (continueInput);

                            switch (option) {
                                case 1:

                                    System.out.println("\nCurrent Distance: " + branches.get(index).getDistance() + "km");
                                    continueInput = true;
                                    do {
                                        try {
                                            System.out.print("Change to(km): ");
                                            double distance = scanner.nextDouble();
                                            scanner.nextLine();
                                            branches.get(index).setDistance(distance);
                                            continueInput = false;
                                        } catch (InputMismatchException ex) {
                                            System.out.println("\nInvalid Input Please Try Again!!");
                                            scanner.nextLine();
                                        }
                                    } while (continueInput);
                                    break;
                                case 2:
                                    Branches temp = new Branches(branches.get(index).getAddress(), branches.get(index).getState(), branches.get(index).getPost_code());

                                    do {
                                        String address = temp.getAddress(), newAddress;
                                        String part[] = address.split(",");
                                        System.out.println("Current Address:");
                                        for (String a : part) {
                                            System.out.println(a.trim() + ",");
                                        }
                                        do {

                                            System.out.println("Address Change to:");
                                            newAddress = scanner.nextLine();
                                            branches.get(index).setAddress(newAddress);
                                            if (!branches.get(index).validateAddress()) {
                                                System.out.println("\nInvalid Input!Please try again");
                                            }
                                        } while (!branches.get(index).validateAddress());

                                        System.out.println("Current State is " + temp.getState());
                                        do {

                                            continueInput = true;
                                            try {
                                                String[] state = {"Kuala Lumpur", "Selangor", "Kuantan", "Putrajaya", "Petaling Jaya"};
                                                System.out.println("Select Branch State:");
                                                System.out.println("1. Kuala Lumpur");
                                                System.out.println("2. Selangor");
                                                System.out.println("3. Kuantan");
                                                System.out.println("4. Putrajaya");
                                                System.out.println("5. Petaling Jaya");
                                                System.out.print("Enter your option: ");
                                                option = scanner.nextInt();
                                                scanner.nextLine();
                                                continueInput = false;
                                                branches.get(index).setState(state[option - 1]);

                                            } catch (InputMismatchException e) {
                                                System.out.println("\nYour input is invalid!!Please enter 1-5.");
                                                scanner.nextLine();
                                            } catch (Exception ex) {
                                                System.out.println("\nYour input is invalid!!Please enter 1-5.");
                                            }
                                        } while (continueInput);

                                        System.out.println("Curent Post Code: " + String.format("%05d", temp.getPost_code()));
                                        do {
                                            continueInput = true;
                                            try {

                                                System.out.print("Enter Post Code: ");
                                                int postCode = scanner.nextInt();
                                                scanner.nextLine();
                                                branches.get(index).setPost_code(postCode);
                                                //validate
                                                if (branches.get(index).validatePostCode()) {
                                                    continueInput = false;
                                                } else {
                                                    System.out.println("\nInvalid input!!Please try agian(exampler: 68000).");
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("\nInvalid input!!Please try agian(exampler: 68000).");
                                                scanner.nextLine();
                                            }

                                        } while (continueInput);

                                        continueInput = false;
                                        for (Branches br : branches) {
                                            // check address, state, and post code
                                            if (branches.get(index).getBranchID().equals(br.getBranchID())) {
                                                break;
                                            } else if (branches.get(index).equals(br)) {
                                                continueInput = true;
                                                System.out.println("\nThe address is repeated!!Please try again.");
                                            }
                                        }
                                    } while (continueInput);

                                    break;
                                case 3:
                                    System.out.println("Date of Creation: " + dateForm.format(branches.get(index).getDateCreated()));
                                    do {
                                        continueInput = true;
                                        try {
                                            System.out.print("Enter Date(format: DD/MM/YYYY): ");
                                            Date date = dateForm.parse(scanner.nextLine());
                                            continueInput = false;
                                            branches.get(index).setDateCreated(date);
                                            if (!branches.get(index).validateDate()) {
                                                System.out.println("\nInvalid Input. Can't write about the future");
                                            }

                                        } catch (ParseException e) {
                                            System.out.println("\nInvalid format!Please follow the format.");
                                        }
                                    } while (continueInput || !branches.get(index).validateDate());
                                    break;
                                case 4:
                                    exit = true;
                                    break;
                                default:
                                    System.out.println("\nInvalid input please enter 1-4");
                                    break;
                            }

                        } while (!exit);
                    } else {
                        System.out.println("ID: " + id + "not found!");
                    }

                } else {
                    System.out.println("Exited!");
                }
            } while (!exit);
        }
    }

    public static void removeBranch() {
        if (branches.isEmpty()) {
            System.out.println("\nWithout any branches record!!\n");
        } else {
            Scanner scanner = new Scanner(System.in);
            int index = 0;
            boolean found;
            boolean exit;
            String branchId;

            do {
                exit = false;
                found = false;
                System.out.print("Enter branch ID want to remove(XXX to exit): ");
                branchId = scanner.nextLine();
                branchId = branchId.replace(" ", "");
                branchId = branchId.toUpperCase();
                for (int i = 0; i < branches.size(); i++) {
                    if (branches.get(i).getBranchID().equals(branchId)) {
                        found = true;
                        index = i;
                    }
                }

                if (found) {
                    branches.remove(index);
                    System.out.println(branchId + " was removed!!");
                } else if (branchId.equals("XXX")) {
                    exit = true;
                } else {
                    System.out.println("\n" + branchId + " not found!!");
                }
            } while (!exit);
        }
    }

    public static void searchBranch() {
        if (branches.isEmpty()) {
            System.out.println("\nWithout any branches record!!\n");
        } else {
            Scanner scanner = new Scanner(System.in);
            int index = 0;
            boolean found;
            boolean exit = false;
            int option = 0;
            ArrayList<Branches> display = new ArrayList<>();

            do {
                display.clear();
                boolean continueInput = true;
                System.out.println("\nSelect what you are looking for:");
                System.out.println("1. Branch ID");
                System.out.println("2. Within distance");
                System.out.println("3. Specific Address");
                System.out.println("4. Post Code");
                System.out.println("5. State");
                System.out.println("6. Exit");
                try {
                    System.out.print("Enter your selection: ");
                    option = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException ex) {
                    System.out.println("\nPlease enter 1-6!!");
                    scanner.nextLine();
                }

                switch (option) {
                    case 1:
                        do {
                            continueInput = true;
                            found = false;

                            System.out.print("\nEnter Branch ID(XXX to exit):");
                            String id = scanner.nextLine();
                            id = id.trim().replace(" ", "").toUpperCase();
                            if (!id.equals("XXX")) {
                                for (Branches br : branches) {
                                    if (br.getBranchID().equals(id)) {
                                        found = true;
                                        display.add(br);
                                        displayBranch(display);
                                        continueInput = false;
                                    }
                                }

                                if (!found) {
                                    System.out.println("\nBranch ID: " + id + " didn't found!!Please try again.");
                                }
                            } else {
                                System.out.println("\nExited Search ID.");
                                continueInput = false;
                            }
                        } while (continueInput);

                        break;
                    case 2:
                        do {
                            continueInput = true;
                            try {
                                System.out.print("Enter the distance(in km): ");
                                double distance = scanner.nextDouble();
                                scanner.nextLine();
                                continueInput = false;
                                for (Branches br : branches) {
                                    if (br.getDistance() <= distance) {
                                        display.add(br);
                                    }
                                }

                                if (display.isEmpty()) {
                                    System.out.println("\nDidn't found branch within " + distance + "km.");
                                } else {
                                    displayBranch(display);
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println("\nInvalid input!Please try again.");
                            }
                        } while (continueInput);
                        break;
                    case 3:

                        String s_state = "",
                         s_address = "";
                        int postCode = 0;
                        do {
                            continueInput = true;
                            try {
                                System.out.print("Enter post code(example: 90000): ");
                                postCode = scanner.nextInt();
                                scanner.nextLine();
                                continueInput = false;
                            } catch (InputMismatchException ex) {
                                System.out.println("\nInvalid input please try again.");
                                scanner.nextLine();
                            }
                        } while (continueInput);
                        do {
                            try {
                                String[] state = {"Kuala Lumpur", "Selangor", "Kuantan", "Putrajaya", "Petaling Jaya"};
                                System.out.println("Select Branch State:");
                                System.out.println("1. Kuala Lumpur");
                                System.out.println("2. Selangor");
                                System.out.println("3. Kuantan");
                                System.out.println("4. Putrajaya");
                                System.out.println("5. Petaling Jaya");
                                System.out.print("Enter your option: ");
                                int select = scanner.nextInt();
                                scanner.nextLine();
                                s_state = state[select - 1];
                                continueInput = false;
                            } catch (InputMismatchException ex) {
                                System.out.println("\nInvalid input please try again.");
                                scanner.nextLine();
                            }
                        } while (continueInput);

                        System.out.println("Enter address:");
                        String address = scanner.nextLine();
                        Branches br = new Branches(address, s_state, postCode);
                        found = false;
                        for (Branches b : branches) {
                            if (b.equals(br)) {
                                found = true;
                                display.add(b);
                            }
                        }
                        if (found) {
                            displayBranch(display);
                        } else {
                            System.out.println("\nDidn't found the address.");
                        }
                        break;
                    case 4:
                        continueInput = true;
                        do {
                            try {
                                System.out.print(" \nEnter Post Code:");
                                int post_code = scanner.nextInt();
                                scanner.nextLine();
                                continueInput = false;
                                found = false;
                                for (Branches b : branches) {
                                    if (b.getPost_code() == post_code) {
                                        display.add(b);
                                        found = true;
                                    }
                                }
                                if (found) {
                                    displayBranch(display);
                                } else {
                                    System.out.printf("\nNo any branch in %05d post code.\n", post_code);
                                }

                            } catch (InputMismatchException ex) {
                                System.out.println("\nInvalid input!Please try again.");
                            }
                        } while (continueInput);
                        break;
                    case 5:
                        continueInput = true;
                        do {
                            try {
                                String[] state = {"Kuala Lumpur", "Selangor", "Kuantan", "Putrajaya", "Petaling Jaya"};
                                System.out.println("Select Branch State:");
                                System.out.println("1. Kuala Lumpur");
                                System.out.println("2. Selangor");
                                System.out.println("3. Kuantan");
                                System.out.println("4. Putrajaya");
                                System.out.println("5. Petaling Jaya");
                                System.out.print("Enter your option: ");
                                int select = scanner.nextInt();
                                scanner.nextLine();
                                s_state = state[select - 1];
                                continueInput = false;
                                found = false;
                                for (Branches b : branches) {
                                    if (b.getState().equals(s_state)) {
                                        display.add(b);
                                        found = true;
                                    }
                                }
                                if (found) {
                                    displayBranch(display);
                                } else {
                                    System.out.println("\nNo any branch in " + s_state);
                                }

                            } catch (InputMismatchException ex) {
                                System.out.println("\nInvalid input please enter 1-5.");
                                scanner.nextLine();
                            }
                        } while (continueInput);
                        break;
                    case 6:
                        exit = true;
                        break;
                }

            } while (!exit);
        }
    }

    public static void displayBranch(ArrayList<Branches> display) {
        //String branchID, double distance, String address, String state, int post_code, Date dateCreated
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================================================================");
        System.out.printf("| %-9s | %-30s | %-12s | %-9s | %-13s |\n", "Branch ID", "Address", "State", "Post Code", "Distance", "Date Creation");
        System.out.println("=========================================================================================");
        for (Branches dp : display) {
            dp.displayFormat();
        }
        System.out.println("Enter to exit");
        scanner.nextLine();
    }

    public static String passBranches() {
        Scanner scanner = new Scanner(System.in);
        readFile();
        int count = 0;
        Branches temp = new Branches();
        boolean continueInput = true;

        if (branches.isEmpty()) {
            System.out.println("\nWithout any Branches!!");
        } else {
            System.out.println("Select Branch ID:");
            for (Branches br : branches) {
                count++;
                System.out.println(count + ". " + br.getBranchID());
            }
            do {
                try {
                    System.out.print("Enter (1-" + count + "): ");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    continueInput = false;
                    if (option > count || option <= 0) {
                        continueInput = true;
                        System.out.println("\nInvalid Input!Please try again.");
                    } else {
                        temp = branches.get(option - 1);
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("\nInvalid Input!Please try again.");
                    scanner.nextLine();
                }
            } while (continueInput);

        }
        return temp.getBranchID();
    }
    
    public static boolean findBranches(String branchId){
        readFile();
        for(Branches br: branches){
            if(br.getBranchID().equals(branchId)){
                return true;
            }
        }
        return false;
    }
    
    public static double getBranchesDistance(String branchId){
        readFile();
        for(Branches br: branches){
            if(br.getBranchID().equals(branchId)){
                return br.getDistance();
            }
        }
        return 0;
    }
    
}


