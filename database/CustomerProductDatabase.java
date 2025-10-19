package database;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import model.CustomerProduct;

public class CustomerProductDatabase extends AbstractDatabase<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                records.add(createRecordFrom(line));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");

        }
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        LocalDate date = LocalDate.parse(parts[2]);

        CustomerProduct cp = new CustomerProduct(parts[0], parts[1], date);
        boolean paid = Boolean.parseBoolean(parts[3]);
        cp.setPaid(paid);
        return cp;
    }

    @Override
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CustomerProduct getRecord(String key) {
        if (!this.contains(key)) {
            System.out.println("There is no this data :  " + key);
            return null;
        }
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) 
                return cp;
        
        }
        return null;
    }

    @Override
    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Duplicate record! This purchase already exists.");
        }
    }

    @Override
    public void deleteRecord(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                records.remove(cp);
                return;
            }
        }

        System.out.println("Doesn;t exist");
    }

    @Override
    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

            for (CustomerProduct record : records) {
                writer.println(record.lineRepresentation());
            }

            writer.close();
            System.out.println("File saved successfully!");

        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        
    
}
}

}
