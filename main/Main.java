package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.CustomerProduct;
import database.CustomerProductDatabase;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        CustomerProductDatabase db = new CustomerProductDatabase("CustomerProducts.txt");
        db.readFromFile();

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
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- All Records ---");
                    for (CustomerProduct cp : db.getAllRecords()) {
                        System.out.println(cp.lineRepresentation());
                    }
                    break;

                case 2:
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
                    break;

                case 3:
                    System.out.print("Enter search key (SSN,ProductID,DD-MM-YYYY): ");
                    String key = input.nextLine();
                    System.out.println("Record exists? " + db.contains(key));
                    break;

                case 4:
                    System.out.print("Enter key to mark as paid: ");
                    String payKey = input.nextLine();
                    CustomerProduct cp = db.getRecord(payKey);
                    if (cp != null) {
                        cp.setPaid(true);
                        System.out.println("Marked as paid!");
                    } else {
                        System.out.println("Record not found!");
                    }
                    break;

                case 5:
                    db.saveToFile();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        input.close();
    }
}
