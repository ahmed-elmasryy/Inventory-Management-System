package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.CustomerProduct;
import database.CustomerProductDatabase;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        CustomerProductDatabase db = new CustomerProductDatabase("CustomerProducts.txt");
        db.readFromFile();

        // ✅ try-with-resources automatically closes the Scanner at the end
        try (Scanner input = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n=== Customer Product Database ===");
                System.out.println("1. View all records");
                System.out.println("2. Add new record");
                System.out.println("3. Search for a record");
                System.out.println("4. Mark a record as paid");
                System.out.println("5. Save changes");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                // check valid input
                while (!input.hasNextInt()) {
                    System.out.print("Please enter a number: ");
                    input.next();
                }
                choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- All Records ---");
                        for (CustomerProduct cp : db.returnAllRecords()) {
                            System.out.println(cp.lineRepresentation());
                        }
                    }

                    case 2 -> {
                        System.out.print("Enter customer SSN: ");
                        String ssn = input.nextLine();

                        System.out.print("Enter product ID: ");
                        String pid = input.nextLine();

                        System.out.print("Enter purchase date (dd-MM-yyyy): ");
                        String dateStr = input.nextLine();
                        LocalDate date = LocalDate.parse(dateStr, formatter);

                        CustomerProduct newRecord = new CustomerProduct(ssn, pid, date);
                        newRecord.setPaid(false);
                        db.insertRecord(newRecord);
                    }

                    case 3 -> {
                        System.out.print("Enter search key (SSN,ProductID,DD-MM-YYYY): ");
                        String key = input.nextLine();
                        System.out.println("Record exists? " + db.contains(key));
                    }

                    case 4 -> {
                        System.out.print("Enter key to mark as paid (SSN,ProductID,DD-MM-YYYY): ");
                        String payKey = input.nextLine();
                        CustomerProduct cp = db.getRecord(payKey);
                        if (cp != null) {
                            cp.setPaid(true);
                            System.out.println("Record marked as paid!");
                        } else {
                            System.out.println("Record not found!");
                        }
                    }

                    case 5 -> db.saveToFile();

                    case 0 -> System.out.println("Exiting...");

                    default -> System.out.println("Invalid choice!");
                }

            } while (choice != 0);
        }
    }
}
