/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

/**
 *
 * @author User
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class StaffLogin {

    final static List<Employee> people = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Staff Login System");

        int choice;
        do {
            System.out.println("\n=======Menu=======");
            System.out.println("1. Login");
            System.out.println("2. Signup (Staff)");
            System.out.println("3. Signup (Manager)");
            System.out.println("4. Update Profile");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter staff ID: ");
                    String staffID = scanner.nextLine();

                    Employee employee = Employee.loadFromFile(staffID);
                    if (employee != null) {
                        System.out.println("Welcome back, " + employee.getName() + "!\n");
                        OOPT.functionMenu();
                    } else {
                        System.out.println("No matching record found.");
                    }
                    break;

                case 2:
                    Employee newStaff = Staff.signup();
                    addPerson(newStaff);
                    newStaff.saveToFile();
                    break;

                case 3:
                    Employee newManager = Manager.signup();
                    addPerson(newManager);
                    newManager.saveToFile();
                    break;

                case 4:
                    System.out.print("Enter your staff ID to update profile: ");
                    String updateStaffID = scanner.nextLine();

                    Employee employeeToUpdate = Employee.loadFromFile(updateStaffID);
                    if (employeeToUpdate != null) {
                        System.out.print("Enter your password to update profile: ");
                        String updatePassword = scanner.nextLine();

                        if (employeeToUpdate.getPassword().equals(updatePassword)) {
                            employeeToUpdate.updateProfile();
                            employeeToUpdate.saveToFile();
                        } else {
                            System.out.println("Incorrect password. Profile update failed.");
                        }
                    } else {
                        System.out.println("No matching record found. Please login first.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        // Auto-load users
        loadAllEmployees();

        scanner.close();
    }

    public static void addPerson(Employee employee) {
        people.add(employee);
        System.out.println(employee.getName() + " has been successfully signed up.");
    }

    public static Employee findEmployee(String staffID) {
        return Employee.loadFromFile(staffID);
    }

    public static void loadAllEmployees() {
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                String staffID = file.getName().replace(".txt", "");
                Employee employee = Employee.loadFromFile(staffID);
                if (employee != null) {
                    people.add(employee);
                }
            }
        }
    }
}
