/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopt;

/**
 *
 * @author User
 */
import java.util.Scanner;

public class Manager extends Employee {
    private String department;

    public Manager(String name, String staffID, String phoneNum, String email, int age, String password, String department) {
        super(name, staffID, phoneNum, email, age, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static Manager signup() {
        Employee baseEmployee = Employee.signup();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        return new Manager(baseEmployee.getName(), baseEmployee.getStaffID(), baseEmployee.getPhoneNum(),
                baseEmployee.getEmail(), baseEmployee.getAge(), baseEmployee.getPassword(), department);
    }

    @Override
    public void updateProfile() {
        super.updateProfile();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Update department (current: " + this.department + "): ");
        this.department = scanner.nextLine();

        System.out.println("Manager profile updated successfully!");
    }

    public void recruitment() {
        System.out.println(getName() + " is handling recruitment in the " + this.department + " department.");
    }

    public void decisionMaking() {
        System.out.println(getName() + " is making decisions for the " + this.department + " department.");
    }
}
