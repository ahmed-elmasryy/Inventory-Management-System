package database; 

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import model.EmployeeUser;

public class EmployeeUserDatabase extends AbstractDatabase <EmployeeUser> {

    public EmployeeUserDatabase(String fileName) {
        this.filename = fileName;
    }

    public void readFromFile() {
       try(Scanner reader = new Scanner(new File(this.filename))) {
           while(reader.hasNextLine()) {
               String line = reader.nextLine().trim();
               this.records.add(createRecordFrom(line));
           }
       }
       catch(Exception e){
           System.out.println(e);
       }
    }

    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if(parts.length != 5){
            System.out.println("Incorrect line format!");
            return null;
        }
        return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public ArrayList<EmployeeUser> returnAllRecords() {
        return this.records;
    }

    public boolean contains(String key) {
        for(int i = 0; i < this.records.size(); i++) {
            if(this.records.get(i).getEmployeeId().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key) {
        if(!this.contains(key)) {
            System.out.println("There is no employee with the ID " + key);
            return null;
        }
        else {
            for (int i = 0; i < this.records.size(); i++) {
                if (this.records.get(i).getEmployeeId().equals(key)) {
                    return this.records.get(i);
                }
            }
        }
        return null;
    }

    public void insertRecord(EmployeeUser record) {
        this.records.add(record);
    }

    public void deleteRecord(String key) {
        if(!this.contains(key)) {
            System.out.println("There is no employee with the ID " + key);
            return;
        }
        else{
            for (int i = 0; i < this.records.size(); i++) {
                if (this.records.get(i).getEmployeeId().equals(key)) {
                    this.records.remove(i);
                    System.out.println("Employee with the ID " + key + " has been deleted");
                    return;
                }
            }
        }
    }

    public void saveToFile() {
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