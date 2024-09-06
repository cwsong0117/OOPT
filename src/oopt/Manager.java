import java.util.Scanner;

public class Manager extends Employee {
    private String department;

    public Manager(String name, String staffID, String phoneNum, String email, String age, String password, String department) {
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
        Employee employee = Employee.signup();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        return new Manager(employee.getName(), employee.getStaffID(), employee.getPhoneNum(), employee.getEmail(), employee.getAge(), employee.getPassword(), department);
    }

    @Override
    public void updateProfile() {
        super.updateProfile();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Update department (current: " + this.department + "): ");
        String newDepartment = scanner.nextLine();
        if (!newDepartment.isEmpty()) {
            this.department = newDepartment;
        }

        System.out.println("Manager profile updated successfully!");
    }

    public void recruitment() {
        System.out.println(getName() + " is handling recruitment in the " + this.department + " department.");
    }

    public void decisionMaking() {
        System.out.println(getName() + " is making important decisions for the company.");
    }
}
