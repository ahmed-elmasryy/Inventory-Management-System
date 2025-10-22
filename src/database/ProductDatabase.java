package database;

import java.io.*;
import java.util.*;
import model.Product;

public class ProductDatabase extends AbstractDatabase<Product> {

    //Constructor 
    public ProductDatabase(String filename) {
        this.filename = filename;
    }

    //Methods
    @Override
    public void readFromFile() {
        try (Scanner sc = new Scanner(new File(this.filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                this.records.add(createRecordFrom(line));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");

        if(parts.length != 6){
            System.out.println("Incorrect line format!");
            return null;
        }

        return new Product(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),Float.parseFloat(parts[5]));
    }

    public ArrayList<Product> returnAllRecords() {
        return records;
    }

    public boolean contains(String key){
        for(int i = 0; i < this.records.size(); i++) {
            if(this.records.get(i).getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Product getRecord(String key){
        if(!this.contains(key)) {
            System.out.println("There is no employee with the ID " + key);
            return null;
        }
        else {
            for (int i = 0; i < this.records.size(); i++) {
                if (this.records.get(i).getSearchKey().equals(key)) {
                    return this.records.get(i);
                }
            }
        }
        return null;
    }

    public void insertRecord(Product record){
        if(this.contains(record.getSearchKey())) {
            System.out.println("Record with key " + record.getSearchKey() + " already exists.");
            return;
        }
        records.add(record);
        saveToFile();
    }

    public void deleteRecord(String key){
        if(!this.contains(key)) {
            System.out.println("There is no employee with the ID " + key);
            return;
        }
        else{
            for (int i = 0; i < this.records.size(); i++) {
                if (this.records.get(i).getSearchKey().equals(key)) {
                    this.records.remove(i);
                    System.out.println("Employee with the ID " + key + " has been deleted");
                    return;
                }
            }
        }

    }

    public void saveToFile(){
        try {
            File file = new File(this.filename);
            PrintWriter writer = new PrintWriter(new FileWriter(file, false));

            for (int i = 0; i < this.records.size() ;i++) {
                writer.println(this.records.get(i).lineRepresentation());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing data to file.");
        }
    }

}

