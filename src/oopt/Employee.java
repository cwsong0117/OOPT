import java.io.*;
import java.util.Scanner;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String staffID;
    private String phoneNum;
    private String email;
    private String age;
    private String password;

    public Employee(String name, String staffID, String phoneNum, String email, String age, String password) {
        this.name = name;
        this.staffID = staffID;
        this.phoneNum = phoneNum;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public static Employee signup() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        String staffID;
        do {
            System.out.print("Enter staff ID: ");
            staffID = scanner.nextLine();
            if (doesStaffIDExist(staffID)) {
                System.out.println("A user with this Staff ID already exists. Please type a different Staff ID.");
            }
        } while (doesStaffIDExist(staffID));

        String phoneNum;
        do {
            System.out.print("Enter phone number: ");
            phoneNum = scanner.nextLine();
            if (!isValidPhoneNum(phoneNum)) {
                System.out.println("Invalid Phone Number Format. Please type the correct phone number!");
            }
        } while (!isValidPhoneNum(phoneNum));

        String email;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid Email Format. Please type the correct email format!");
            }
        } while (!isValidEmail(email));

        System.out.print("Enter age: ");
        String age = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return new Employee(name, staffID, phoneNum, email, age, password);
    }

    public void updateProfile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Update name (current: " + this.name + ") or press Enter to keep current: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            this.name = newName;
        }

        System.out.print("Update phone number (current: " + this.phoneNum + ") or press Enter to keep current: ");
        String newPhoneNum = scanner.nextLine();
        if (!newPhoneNum.isEmpty() && isValidPhoneNum(newPhoneNum)) {
            this.phoneNum = newPhoneNum;
        } else if (!newPhoneNum.isEmpty()) {
            System.out.println("Invalid Phone Number Format.");
        }

        System.out.print("Update email (current: " + this.email + ") or press Enter to keep current: ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty() && isValidEmail(newEmail)) {
            this.email = newEmail;
        } else if (!newEmail.isEmpty()) {
            System.out.println("Invalid Email Format.");
        }

        System.out.print("Update age (current: " + this.age + ") or press Enter to keep current: ");
        String newAgeStr = scanner.nextLine();
        if (!newAgeStr.isEmpty()) {
            try {
                this.age = newAgeStr;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Age not updated.");
            }
        }

        System.out.print("Update password or press Enter to keep current: ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            this.password = newPassword;
        }

        System.out.println("Profile updated successfully!");
    }

    private static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailPattern);
    }

    private static boolean isValidPhoneNum(String phoneNum) {
        String phonePattern = "^\\+?\\d{10,15}$";
        return phoneNum.matches(phonePattern);
    }

    private static boolean doesStaffIDExist(String staffID) {
        File file = new File(staffID + ".txt");
        return file.exists();
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.staffID + ".txt"))) {
            writer.write("Name: " + this.name);
            writer.newLine();
            writer.write("Staff ID: " + this.staffID);
            writer.newLine();
            writer.write("Phone Number: " + this.phoneNum);
            writer.newLine();
            writer.write("Email: " + this.email);
            writer.newLine();
            writer.write("Age: " + this.age);
            writer.newLine();
            writer.write("Password: " + this.password);
            writer.newLine();
            System.out.println("Profile saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving profile: " + e.getMessage());
        }
    }

    public static Employee loadFromFile(String staffID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(staffID + ".txt"))) {
            String name = reader.readLine().split(": ", 2)[1];
            String phoneNum = reader.readLine().split(": ", 2)[1];
            String email = reader.readLine().split(": ", 2)[1];
            String age = reader.readLine().split(": ", 2)[1];
            String password = reader.readLine().split(": ", 2)[1];

            return new Employee(name, staffID, phoneNum, email, age, password);
        } catch (IOException e) {
            System.out.println("Error loading profile: " + e.getMessage());
            return null;
        }
    }
}
