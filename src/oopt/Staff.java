///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package oopt;
//
///**
// *
// * @author User
// */
//import java.util.Scanner;
//        
//public class Staff extends Employee{
//        private String position;
//
//    public Staff(String name, String staffID, String phoneNum, String email, int age, String password, String position) {
//        super(name, staffID, phoneNum, email, age, password);
//        this.position = position;
//    }
//
//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    public static Staff signup() {
//        Employee employee = Employee.signup();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter position: ");
//        String position = scanner.nextLine();
//
//        return new Staff(employee.getName(), employee.getStaffID(), employee.getPhoneNum(),
//                employee.getEmail(), employee.getAge(), employee.getPassword(), position);
//    }
//
//    @Override
//    public void updateProfile() {
//        super.updateProfile();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Update position (current: " + this.position + "): ");
//        this.position = scanner.nextLine();
//
//        System.out.println("Staff profile updated successfully!");
//    }
//}
