package main;

import roles.AdminRole;
import roles.EmployeeRole;
import database.EmployeeUserDatabase;
import model.EmployeeUser;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Welcome to the Store Management System ===");
        System.out.print("Login as (1) Admin or (2) Employee: ");
        int roleChoice = Integer.parseInt(input.nextLine());

        if (roleChoice == 1) {
            // ----- Admin Role -----
            EmployeeUserDatabase empDb = new EmployeeUserDatabase("Employees.txt");
            empDb.readFromFile();
            AdminRole admin = new AdminRole(empDb);

            while (true) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Remove Employee");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Employee ID: ");
                        String id = input.nextLine();
                        System.out.print("Enter Name: ");
                        String name = input.nextLine();
                        System.out.print("Enter Email: ");
                        String email = input.nextLine();
                        System.out.print("Enter Address: ");
                        String address = input.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phone = input.nextLine();
                        admin.addEmployee(id, name, email, address, phone);
                    }
                    case 2 -> {
                        EmployeeUser[] employees = admin.getListOfEmployees();
                        if (employees.length == 0) System.out.println("No employees found.");
                        else {
                            System.out.println("\n--- Employee List ---");
                            for (EmployeeUser e : employees)
                                System.out.println(e.lineRepresentation());
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Employee ID to remove: ");
                        String removeId = input.nextLine();
                        admin.removeEmployee(removeId);
                    }
                    case 4 -> {
                        admin.logout();
                        System.out.println("Logged out successfully!");
                        input.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } else if (roleChoice == 2) {
            // ----- Employee Role -----
            EmployeeRole emp = new EmployeeRole();

            while (true) {
                System.out.println("\n--- Employee Menu ---");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Purchase Product");
                System.out.println("4. Return Product");
                System.out.println("5. Apply Payment");
                System.out.println("6. Logout");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Product ID: ");
                        String pid = input.nextLine();
                        System.out.print("Enter Product Name: ");
                        String pname = input.nextLine();
                        System.out.print("Enter Manufacturer Name: ");
                        String manu = input.nextLine();
                        System.out.print("Enter Supplier Name: ");
                        String supp = input.nextLine();
                        System.out.print("Enter Quantity: ");
                        int qty = Integer.parseInt(input.nextLine());
                        System.out.print("Enter Price: ");
                        float price = Float.parseFloat(input.nextLine());
                        emp.addProduct(pid, pname, manu, supp, qty, price);
                    }
                    case 2 -> {
                        var products = emp.getListOfProducts();
                        if (products.length == 0) System.out.println("No products found.");
                        else {
                            System.out.println("\n--- Product List ---");
                            for (var p : products)
                                System.out.println(p.lineRepresentation());
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Customer SSN: ");
                        String ssn = input.nextLine();
                        System.out.print("Enter Product ID: ");
                        String pid = input.nextLine();
                        System.out.print("Enter Purchase Date (yyyy-MM-dd): ");
                        LocalDate date = LocalDate.parse(input.nextLine());
                        emp.purchaseProduct(ssn, pid, date);
                    }
                    case 4 -> {
                        System.out.print("Enter Customer SSN: ");
                        String ssn = input.nextLine();
                        System.out.print("Enter Product ID: ");
                        String pid = input.nextLine();
                        System.out.print("Enter Purchase Date (yyyy-MM-dd): ");
                        LocalDate pdate = LocalDate.parse(input.nextLine());
                        System.out.print("Enter Return Date (yyyy-MM-dd): ");
                        LocalDate rdate = LocalDate.parse(input.nextLine());
                        emp.returnProduct(ssn, pid, pdate, rdate);
                    }
                    case 5 -> {
                        System.out.print("Enter Customer SSN: ");
                        String ssn = input.nextLine();
                        System.out.print("Enter Purchase Date (yyyy-MM-dd): ");
                        LocalDate date = LocalDate.parse(input.nextLine());
                        emp.applyPayment(ssn, date);
                    }
                    case 6 -> {
                        emp.logout();
                        System.out.println("Logged out successfully!");
                        input.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid role selected.");
        }
    }
}
