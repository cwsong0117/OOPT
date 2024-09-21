
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
import java.util.Calendar;

public class Shipment {

    public SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
    public SimpleDateFormat timeForm = new SimpleDateFormat("HHmm");

    private final int MAX_TRANSPORT_STOCK = 10;
    private String shipmentId;
    private Date departureDate;
    private Date departureTime;
    private Branches branch;
    private Transportation transportation;
    private String status;
    private Stock[] stock = new Stock[MAX_TRANSPORT_STOCK];

//    private String[] itemId = new String[MAX_TRANSPORT_STOCK];
//    private int[] itemQuantity = new int[MAX_TRANSPORT_STOCK];
    public Shipment(String shipmentId, Date departureDate, Date departureTime, Branches branch, Transportation transportation, String status, Stock[] stock) {
        this.shipmentId = shipmentId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.branch = branch;
        this.transportation = transportation;
        this.status = status;
        this.stock = stock;
    }

    public Shipment() {
        this.shipmentId = "SP00001";
        this.departureDate = new Date();
        this.departureTime = new Date();
        this.branch = new Branches();
        this.transportation = new Transportation();
        this.status = "Pending";
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Branches getBranch() {
        return branch;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public String getStatus() {
        return status;
    }

    public Stock[] getStock() {
        return stock;
    }

    public int getMAX_TRANSPORT_STOCK() {
        return MAX_TRANSPORT_STOCK;
    }
    
    

    public void setStock(Stock[] stock) {
        this.stock = stock;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public boolean validateDate() {
        Date currentDate = new Date();

        if (departureDate.compareTo(currentDate) == 1) {
            return true;
        } else {
            return departureDate.equals(currentDate);
        }
    }

    public void displayForm() {

        System.out.printf("%-15s %-14s %-14s %-15s %-18s %-10s %-10s %-13d\n",
                shipmentId,
                dateForm.format(departureDate),
                timeForm.format(departureTime),
                branch.getBranchID(),
                transportation.getPlateNo(),
                status, stock[0].getStockID(), stock[0].getQuantity());
        for (int i = 1; i < stock.length; i++) {
            if (!stock[i].getStockID().equals("null")) {
                System.out.printf("%-15s %-14s %-14s %-15s %-18s %-10s %-10s %-13d\n", " ", " ", " ", " ", " ", " ", stock[i].getStockID(), stock[i].getQuantity());
            }
        }
    }

    public void generateID(String lastId) {
        shipmentId = String.format("SP%05d", Integer.parseInt(lastId.substring(2, 7)) + 1);
    }

    public Date expectedArrivalTime(double distance) {
        final double AVERAGE_SPEED = 60.0; // km/h
        double expectedTime = (distance / AVERAGE_SPEED) * 60; // in minutes

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departureDate);

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(departureTime);

        int departureHour = timeCalendar.get(Calendar.HOUR_OF_DAY);
        int departureMinute = timeCalendar.get(Calendar.MINUTE);

        calendar.set(Calendar.HOUR_OF_DAY, departureHour);
        calendar.set(Calendar.MINUTE, departureMinute);

        int totalMinutes = (int) Math.round(expectedTime);
        calendar.add(Calendar.MINUTE, totalMinutes);

        return calendar.getTime();
    }

    @Override
    public String toString() {
        String items = stock[0].getStockID();
        String qty = "" + stock[0].getQuantity();
        for (int i = 1; i < stock.length; i++) {
            items = items + "," + stock[i].getStockID();
        }

        for (int i = 1; i < stock.length; i++) {
            qty = qty + "," + stock[i].getQuantity();
        }
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|\n", shipmentId, dateForm.format(departureDate), timeForm.format(departureTime), branch.getBranchID(), transportation.getPlateNo(), status, items, qty);

    }

    @Override
    public boolean equals(Object O) {
        Shipment temp = (Shipment) O;
        if (transportation.getPlateNo().equals(temp.getTransportation().getPlateNo()) && !temp.getStatus().equals("Cancalled")) {
            return departureDate.equals(temp.getDepartureDate());
        } else {
            return false;
        }
    }

}

class actionShipment {
    actionTransportation transportation = new actionTransportation();
    actionBranches branch = new actionBranches();
    public ArrayList<Shipment> shipment = new ArrayList<Shipment>();
    public SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
    public SimpleDateFormat timeForm = new SimpleDateFormat("HHmm");
    private String fileAddress = "Shipment.txt";

    public void shipmentMenu() {
        Scanner scanner = new Scanner(System.in);
        readFile();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nShipment Menu:");
            System.out.println("1. Add New Shipment");
            System.out.println("2. Modify Shipment Information");
            System.out.println("3. Delete Cancelled Shipment Record");
            System.out.println("4. Update Shipment Status");
            System.out.println("5. Shipment Report");
            System.out.println("6. Exit");
            try {
                System.out.print("Enter your option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addShipment();
                        break;
                    case 2:
                        modifyShipment();
                        break;
                    case 3:
                        deleteShipmentRecord();
                        break;
                    case 4:
                        updateShipmentStatus();
                        break;
                    case 5:
                        writeFile();
                        readFile();
                        displayShipment();
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("\nInvalid input!!Please try again.");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("\nInvalid input!!Please try again!!");
                scanner.nextLine();
            }
        }
        writeFile();
    }

    public void readFile() {

        try {
            File file = new File(fileAddress);
            Scanner read = new Scanner(file);
            int line = 0;

            if (!shipment.isEmpty()) {
                shipment.clear();
            }

            while (read.hasNextLine()) {
                line++;

                String data = read.nextLine();
                String[] parts = data.split("\\|");

                //convert to correct type of value
                Stock[] tempStock = new Stock[10];
                String shipmentId = parts[0];
                Date departureDate = dateForm.parse(parts[1]);
                Date departureTime = timeForm.parse(parts[2]);
                String branchId = parts[3];
                String plateNo = parts[4];
                String status = parts[5];
                String[] items = parts[6].split(",");
                String[] quantity = parts[7].split(",");                
             
                for (int i = 0; i < items.length; i++) {
                    tempStock[i] = new Stock(items[i] ,Integer.parseInt(quantity[i]));
                
                }

                shipment.add(new Shipment(shipmentId, departureDate, departureTime, new Branches(branchId), new Transportation(plateNo), status, tempStock));
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
        } catch (ParseException ex) {
            System.out.println("Data in file is error");
        }
    }

    public void writeFile() {
        try {
            FileWriter write = new FileWriter(fileAddress);
            for (Shipment s : shipment) {
                write.write(s.toString());
            }
            write.close();
        } catch (IOException e) {
            System.out.println("\nError opening file!!");
        }
    }

    public void addShipment() {
        int index = shipment.size();
        shipment.add(new Shipment());
        if (index != 0) {
            shipment.get(index).generateID(shipment.get(index - 1).getShipmentId());
        }

        dateForm.setLenient(false);
        timeForm.setLenient(false);
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        boolean empty = false;

        //let user select which branch and get the branch information
        System.out.println("\nShipment ID: " + shipment.get(index).getShipmentId());
        String branchId = branch.passBranches();
        if (branchId.isEmpty()) {
            empty = true;
        } else {
            shipment.get(index).setBranch(new Branches(branchId));
        }

        //get transportation
        if (!empty) {
            System.out.print("Enter transportation plate No.: ");
            String plateNo = scanner.nextLine();
            plateNo = transportation.passTransportation(plateNo);
            shipment.get(index).setTransportation(new Transportation(plateNo));
            if (!plateNo.isEmpty()) {
                do {
                    continueInput = true;
                    try {
                        System.out.print("Enter Departure date(dd/mm/yyyy): ");
                        String date = scanner.nextLine();
                        Date departureDate = dateForm.parse(date);
                        shipment.get(index).setDepartureDate(departureDate);
                        continueInput = false;
                        if (!shipment.get(index).validateDate()) {
                            System.out.println("\nDeparture date cannot at today and before.");
                            continueInput = true;
                        }
                        for (int i = 0; i < shipment.size() - 1; i++) {
                            if (shipment.get(index).equals(shipment.get(i))) {
                                continueInput = true;
                                System.out.println("\nOne transportation only can have one shipment at the same date");
                            }
                        }

                    } catch (ParseException ex) {
                        System.out.println("\nInvalid format!Please follow (dd/mm/yyyy) format.");
                    }
                } while (continueInput);

                do {
                    continueInput = true;
                    try {
                        System.out.print("Enter Departure time in HHMM format(example: 2315): ");
                        String time = scanner.nextLine();
                        Date departureTime = timeForm.parse(time);
                        shipment.get(index).setDepartureTime(departureTime);
                        continueInput = false;
                    } catch (ParseException ex) {
                        System.out.println("\nInvalid format!Please follow HHMM format(example: 2315).");
                    }
                } while (continueInput);

                String[] stock = new String[10];
                int[] qty = new int[new Shipment().getMAX_TRANSPORT_STOCK()];
                int count = 0;
                boolean exit = false;
                System.out.println("Enter item list(Maximum " + new Shipment().getMAX_TRANSPORT_STOCK() + " itmes.");

                do {
                    boolean error;
                    Display.listFood();
                    do {
                        System.out.print("Enter food ID(XXX to stop): ");
                        String foodId = scanner.nextLine();
                        foodId = foodId.replace(" ", "").toUpperCase();

                        if (!foodId.equals("XXX")) {
                            stock[count] = foodId;
                            do {
                                continueInput = true;
                                try {
                                    System.out.print("Enter quantity: ");
                                    qty[count] = scanner.nextInt();
                                    scanner.nextLine();
                                    count++;
                                    continueInput = false;
                                } catch (InputMismatchException ex) {
                                    System.out.println("\nInvalid input!!Please try again.");
                                    scanner.nextLine();
                                }
                            } while (continueInput);
                            error = !StockUpdate.updateStock(stock[count - 1], qty[count - 1]);
                            if (error) {
                                count--;
                            }

                        } else {
                            exit = true;
                            error = false;
                        }
                    } while (error && !exit);
                } while (!exit && count != new Shipment().getMAX_TRANSPORT_STOCK());
                exit = false;
                while (!exit && count != new Shipment().getMAX_TRANSPORT_STOCK()) {
                    boolean error;
                    Display.listBeverage();
                    do {
                        System.out.print("Enter beverage ID(XXX to stop): ");
                        String beverageId = scanner.nextLine();
                        beverageId = beverageId.replace(" ", "").toUpperCase();

                        if (!beverageId.equals("XXX")) {
                            stock[count] = beverageId;
                            do {
                                continueInput = true;
                                try {
                                    System.out.print("Enter quantity: ");
                                    qty[count] = scanner.nextInt();
                                    scanner.nextLine();
                                    count++;
                                    continueInput = false;
                                } catch (InputMismatchException ex) {
                                    System.out.println("\nInvalid input!!Please try again.");
                                    scanner.nextLine();
                                }
                            } while (continueInput);
                            error = !StockUpdate.updateStock(stock[count - 1], qty[count - 1]);
                            if (error) {
                                count--;
                            }

                        } else {
                            exit = true;
                            error = false;
                        }
                    } while (error && !exit);
                }
                exit = false;
                while (!exit && count != new Shipment().getMAX_TRANSPORT_STOCK()) {
                    boolean error;
                    Display.listIngredient();
                    do {
                        System.out.print("Enter ingredient ID(XXX to stop): ");
                        String ingredientId = scanner.nextLine();
                        ingredientId = ingredientId.replace(" ", "").toUpperCase();

                        if (!ingredientId.equals("XXX")) {
                            stock[count] = ingredientId;
                            do {
                                continueInput = true;
                                try {

                                    System.out.print("Enter quantity: ");
                                    qty[count] = scanner.nextInt();
                                    scanner.nextLine();
                                    count++;
                                    continueInput = false;
                                } catch (InputMismatchException ex) {
                                    System.out.println("\nInvalid input!!Please try again.");
                                    scanner.nextLine();
                                }
                            } while (continueInput);

                            error = !StockUpdate.updateStock(stock[count - 1], qty[count - 1]);
                            if (error) {
                                count--;
                            }

                        } else {
                            exit = true;
                            error = false;
                        }
                    } while (error && !exit);
                }
                Stock[] tempStock = new Stock[new Shipment().getMAX_TRANSPORT_STOCK()];

                for (int i = 0; i < stock.length; i++) {
                    tempStock[i] = new Stock(stock[i],qty[i]);
                }
                shipment.get(index).setStock(tempStock);

            }

        }

    }

    public void modifyShipment() {
        dateForm.setLenient(false);
        timeForm.setLenient(false);
        Scanner scanner = new Scanner(System.in);
        if (!shipment.isEmpty()) {
            int index = 0;
            boolean found = false, continueInput, exit = false;

            do {
                System.out.print("Enter Shipment ID to modify(SPXXXXX): ");
                String id = scanner.nextLine();
                id = id.replace(" ", "").toUpperCase();
                for (int i = 0; i < shipment.size(); i++) {
                    if (shipment.get(i).getShipmentId().equals(id)) {
                        index = i;
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nShipment ID can not found!!Please try again.");
                }

            } while (!found);
//String shipmentId, Date departureDate, Date departureTime, String branchId,
//String transportationPlateNo, String status, String[] itemId, int[] itemQuantity
            do {
                Shipment temp = new Shipment(shipment.get(index).getShipmentId(), shipment.get(index).getDepartureDate(), shipment.get(index).getDepartureTime(), shipment.get(index).getBranch(), shipment.get(index).getTransportation(), shipment.get(index).getStatus(), shipment.get(index).getStock());
                System.out.println("Select what to modify:");
                System.out.println("1. Departure Date and time");
                System.out.println("2. Transportation");
                System.out.println("3. Item List");
                System.out.println("4. Exit");

                do {
                    continueInput = true;
                    try {
                        System.out.print("Enter your option: ");
                        int option = scanner.nextInt();
                        scanner.nextLine();
                        switch (option) {
                            case 1:
                                do {
                                    continueInput = true;
                                    try {
                                        System.out.println("Previous Date: " + dateForm.format(temp.getDepartureDate()));
                                        System.out.print("Enter Departure date(dd/mm/yyyy): ");
                                        String date = scanner.nextLine();
                                        Date departureDate = dateForm.parse(date);
                                        shipment.get(index).setDepartureDate(departureDate);
                                        continueInput = false;
                                        if (!shipment.get(index).validateDate()) {
                                            System.out.println("\nDeparture date cannot at today and before.");
                                            continueInput = true;
                                        }
                                        for (int i = 0; i < shipment.size() - 1; i++) {
                                            if (shipment.get(index).equals(shipment.get(i))) {
                                                if (index != i) {
                                                    continueInput = true;
                                                    System.out.println("\nOne transportation only can have one shipment at the same date");
                                                }
                                            }
                                        }

                                    } catch (ParseException ex) {
                                        System.out.println("\nInvalid format!Please follow (dd/mm/yyyy) format.");
                                    }
                                } while (continueInput);

                                do {
                                    continueInput = true;
                                    try {
                                        System.out.println("Previous Time: " + timeForm.format(temp.getDepartureTime()));
                                        System.out.print("Enter Departure time in HHMM format(example: 2315): ");
                                        String time = scanner.nextLine();
                                        Date departureTime = timeForm.parse(time);
                                        shipment.get(index).setDepartureTime(departureTime);
                                        continueInput = false;
                                    } catch (ParseException ex) {
                                        System.out.println("\nInvalid format!Please follow HHMM format(example: 2315).");
                                    }
                                } while (continueInput);
                                break;
                            case 2:
                                do {
                                    continueInput = false;
                                    System.out.println("Previous Plate No.: " + temp.getTransportation().getPlateNo());
                                    System.out.print("Enter transportation plate No.: ");
                                    String plateNo = scanner.nextLine();
                                    plateNo = transportation.passTransportation(plateNo);
                                    shipment.get(index).setTransportation(new Transportation(plateNo));
                                    for (int i = 0; i < shipment.size() - 1; i++) {
                                        if (shipment.get(index).equals(shipment.get(i))) {
                                            if (index != i) {
                                                continueInput = true;
                                                System.out.println("\nOne transportation only can have one shipment at the same date");
                                            }
                                        }
                                    }
                                } while (continueInput);
                                break;
                            case 3:
                                returnStock(shipment.get(index).getStock());
                                String[] stock = new String[new Shipment().getMAX_TRANSPORT_STOCK()];
                                int[] qty = new int[new Shipment().getMAX_TRANSPORT_STOCK()];
                                int count = 0;
                                exit = false;
                                System.out.println("Enter item list(Maximum " + new Shipment().getMAX_TRANSPORT_STOCK() + " itmes.");

                                do {
                                    boolean error;
                                    Display.listFood();
                                    do {
                                        System.out.print("Enter food ID(XXX to stop): ");
                                        String foodId = scanner.nextLine();
                                        foodId = foodId.replace(" ", "").toUpperCase();

                                        if (!foodId.equals("XXX")) {
                                            stock[count] = foodId;
                                            do {
                                                continueInput = true;
                                                try {
                                                    System.out.print("Enter quantity: ");
                                                    qty[count] = scanner.nextInt();
                                                    scanner.nextLine();
                                                    count++;
                                                    continueInput = false;
                                                } catch (InputMismatchException ex) {
                                                    System.out.println("\nInvalid input!!Please try again.");
                                                    scanner.nextLine();
                                                }
                                            } while (continueInput);
                                            error = !StockUpdate.updateStock(stock[count - 1], qty[count - 1]);
                                            if (error) {
                                                count--;
                                            }

                                        } else {
                                            exit = true;
                                            error = false;
                                        }
                                    } while (error && !exit);
                                } while (!exit &&count != new Shipment().getMAX_TRANSPORT_STOCK());
                                exit = false;
                                while (!exit && count != new Shipment().getMAX_TRANSPORT_STOCK()) {
                                    boolean error;
                                    Display.listBeverage();
                                    do {
                                        System.out.print("Enter beverage ID(XXX to stop): ");
                                        String beverageId = scanner.nextLine();
                                        beverageId = beverageId.replace(" ", "").toUpperCase();

                                        if (!beverageId.equals("XXX")) {
                                            stock[count] = beverageId;
                                            do {
                                                continueInput = true;
                                                try {
                                                    System.out.print("Enter quantity: ");
                                                    qty[count] = scanner.nextInt();
                                                    scanner.nextLine();
                                                    count++;
                                                    continueInput = false;
                                                } catch (InputMismatchException ex) {
                                                    System.out.println("\nInvalid input!!Please try again.");
                                                    scanner.nextLine();
                                                }
                                            } while (continueInput);
                                            error = !StockUpdate.updateStock(stock[count - 1], qty[count - 1]);
                                            if (error) {
                                                count--;
                                            }

                                        } else {
                                            exit = true;
                                            error = false;
                                        }
                                    } while (error && !exit);
                                }
                                exit = false;
                                while (!exit && count != new Shipment().getMAX_TRANSPORT_STOCK()) {
                                    boolean error;
                                    Display.listIngredient();
                                    do {
                                        System.out.print("Enter ingredient ID(XXX to stop): ");
                                        String ingredientId = scanner.nextLine();
                                        ingredientId = ingredientId.replace(" ", "").toUpperCase();

                                        if (!ingredientId.equals("XXX")) {
                                            stock[count] = ingredientId;
                                            do {
                                                continueInput = true;
                                                try {
                                                    System.out.print("Enter quantity: ");
                                                    qty[count] = scanner.nextInt();
                                                    scanner.nextLine();
                                                    count++;
                                                    continueInput = false;
                                                } catch (InputMismatchException ex) {
                                                    System.out.println("\nInvalid input!!Please try again.");
                                                    scanner.nextLine();
                                                }
                                            } while (continueInput);

                                            error = !StockUpdate.updateStock(stock[count - 1], qty[count - 1]);
                                            if (error) {
                                                count--;
                                            }

                                        } else {
                                            exit = true;
                                            error = false;
                                        }
                                    } while (error && !exit);
                                }
                                exit = false;
                                Stock[] tempStock = new Stock[new Shipment().getMAX_TRANSPORT_STOCK()];

                                for (int i = 0; i < stock.length; i++) {
                                    tempStock[i] = new Stock(stock[i],qty[i]);
                                }
                                shipment.get(index).setStock(tempStock);

                                break;
                            case 4:
                                exit = true;
                                continueInput = false;
                                break;
                            default:
                                System.out.println("\nInvalid input!Please try again.");
                                break;
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("\nInvalid input!!Please try again.");
                    }
                } while (continueInput);
            } while (!exit);
        } else {
            System.out.println("\nWithout any shipment can modify!!");
        }
    }

    public void deleteShipmentRecord() {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput, exit = false, found;

        if (shipment.isEmpty()) {
            System.out.println("\nWithout any shipment.");
        } else {
            do {
                boolean have = false;
                for (Shipment s : shipment) {
                    if (s.getStatus().equals("Cancelled")) {
                        have = true;
                    }
                }
                if (have) {
                    do {
                        System.out.println("Delete for");
                        System.out.println("1. Specific Shipment ID(Only Cancelled)");
                        System.out.println("2. All Cancelled Shipment");
                        System.out.println("3. Exit");
                        continueInput = true;

                        try {
                            System.out.print("Enter your option:");
                            int option = scanner.nextInt();
                            scanner.nextLine();

                            switch (option) {
                                case 1:
                                    found = false;
                                    System.out.print("Enter Shipment ID:");
                                    String id = scanner.nextLine();
                                    id = id.toUpperCase().replace(" ", "");
                                    for (int i = 0; i < shipment.size(); i++) {
                                        if (shipment.get(i).getShipmentId().equals(id) && shipment.get(i).getStatus().equals("Cancelled")) {
                                            returnStock(shipment.get(i).getStock());
                                            System.out.println("Shipment(" + shipment.get(i).getShipmentId() + ") already removed!!");
                                            shipment.remove(i);
                                            found = true;
                                        }
                                    }
                                    if (!found) {
                                        System.out.println("\n" + id + " not found or status not in \"Cancelled\"");
                                    }
                                    break;
                                case 2:
                                    found = false;
                                    for (int i = 0; i < shipment.size(); i++) {
                                        if (shipment.get(i).getStatus().equals("Cancelled")) {
                                            returnStock(shipment.get(i).getStock());
                                            System.out.println("Shipment(" + shipment.get(i).getShipmentId() + ") already removed!!");
                                            shipment.remove(i);
                                            found = true;
                                        }

                                    }
                                    if (!found) {
                                        System.out.println("\nWithout any shipment status in \"Cancelled\" status!");
                                    }
                                    break;
                                case 3:
                                    exit = true;
                                    break;
                                default:
                                    System.out.println("\nInvalid option!!Please try again.");
                                    break;

                            }
                            continueInput = false;
                        } catch (InputMismatchException ex) {
                            System.out.println("\nInvalid input!!Please try again.");
                            scanner.nextLine();
                        }
                    } while (continueInput);
                } else {
                    exit = true;
                    System.out.println("\nWithout any shipment have been cancelled.");
                }
            } while (!exit);

        }

    }

    public void updateShipmentStatus() {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        do {
            System.out.println("Update with: ");
            System.out.println("1. Specific Shipment ID");
            System.out.println("2. Automatically");
            System.out.println("3. Exit");
            continueInput = true;

            try {
                System.out.print("Enter your option:");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:

                        int index = 0;
                        boolean found = false;

                        System.out.print("Enter Shipment ID:");
                        String id = scanner.nextLine();
                        id = id.toUpperCase().replace(" ", "");
                        for (int i = 0; i < shipment.size(); i++) {
                            if (shipment.get(i).getShipmentId().equals(id)) {
                                index = i;
                                found = true;
                            }
                        }

                        String[] status = {"Pending", "Cancelled", "Completed", "Shipping"};
                        if (found && !shipment.get(index).getStatus().equals("Cancelled")) {
                            System.out.println("Current Status: " + shipment.get(index).getStatus());
                            System.out.println("Select status to update:");
                            int count = 0;
                            for (String s : status) {
                                count++;
                                System.out.println(count + ". " + s);
                            }
                            do {
                                continueInput = true;
                                try {
                                    System.out.print("Enter your option: ");
                                    int select = scanner.nextInt();
                                    scanner.nextLine();
                                    if (select <= status.length && select > 0) {
                                        shipment.get(index).setStatus(status[select - 1]);
                                        continueInput = false;
                                    } else {
                                        System.out.println("\nInvalid input!Please try again.");
                                    }
                                } catch (InputMismatchException ex) {
                                    System.out.println("\nInvalid input!!Please try again.");
                                    scanner.nextLine();
                                }
                            } while (continueInput);
                        } else if (!found) {
                            System.out.println("\nShipment ID not found!");
                        } else {
                            System.out.println("\nShipment can not be cancalled!");
                        }
                        continueInput = true;
                        break;
                    case 2:
                        for (int i = 0; i < shipment.size(); i++) {

                            if (!branch.findBranches(shipment.get(i).getBranch().getBranchID()) && !shipment.get(i).getStatus().equals("Cancelled")) {
                                System.out.println("The Branch was removed!");
                                System.out.println("Status of shipment(" + shipment.get(i).getShipmentId() + ") changed to Cancelled.");
                                shipment.get(i).setStatus("Cancelled");
                                
                            } else if (!transportation.findTransportation(shipment.get(i).getTransportation().getPlateNo()) && !shipment.get(i).getStatus().equals("Cancelled")) {
                                System.out.println("The Transportation was removed!");
                                System.out.println("Status of shipment(" + shipment.get(i).getShipmentId() + ") changed to Cancelled.");
                                shipment.get(i).setStatus("Cancelled");
                            }

                            if (shipment.get(i).expectedArrivalTime(branch.getBranchesDistance(shipment.get(i).getBranch().getBranchID())).before(new Date()) && shipment.get(i).getStatus().equals("Shipping")) {
                                System.out.println("The expected Arrival Date is " + dateForm.format(shipment.get(i).expectedArrivalTime(branch.getBranchesDistance(shipment.get(i).getBranch().getBranchID()))));
                                System.out.println("Status of shipment(" + shipment.get(i).getShipmentId() + ") changed to \"Completed\"");
                                shipment.get(i).setStatus("Completed");
                            }

                            if (shipment.get(i).getDepartureDate().equals(new Date()) && shipment.get(i).getStatus().equals("Pending")) {
                                System.out.println("The Departure Date is " + dateForm.format(shipment.get(i).expectedArrivalTime(branch.getBranchesDistance(shipment.get(i).getBranch().getBranchID()))));
                                System.out.println("Status of shipment(" + shipment.get(i).getShipmentId() + ") changed to \"Shipping\"");
                                shipment.get(i).setStatus("CShipping");
                            }
                        }
                        break;
                    case 3:
                        continueInput = false;
                        System.out.println("\nExited!");
                        break;
                    default:
                        continueInput = true;
                        System.out.println("\nInvalid Input!!");
                        break;
                }

            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!!Please try again.");
            }
        } while (continueInput);
    }

    public void displayShipment() {

        System.out.println("====================================================================================================================");
        System.out.printf("%-15s %-14s %-14s %-15s %-18s %-10s %-10s %-13s\n",
                "Shipment ID", "Date", "Time", "Branch ID", "Plate No.", "Status", "Item ID", "Item Quantity");
        for (Shipment s : shipment) {
            System.out.println("\n====================================================================================================================");

            s.displayForm();
        }
        System.out.println("\n====================================================================================================================");

        System.out.println("Enter to exit");
        new Scanner(System.in).nextLine();

    }

    public void displayAllCancelledShipmentID() {

        System.out.println("All were cancelled Shipment:");
        for (int i = 0; i < shipment.size(); i++) {
            if (shipment.get(i).getStatus().equals("Cancelled")) {
                System.out.println((i + 1) + ". " + shipment.get(i).getShipmentId());
            }
        }
    }

    public void returnStock(Stock[] stock) {
        for (int i = 0; i < stock.length; i++) {
            if (stock[i].getQuantity() != 0) {
                StockUpdate.returnStock(stock[i].getStockID(), stock[i].getQuantity());
            }
        }
    }

}
