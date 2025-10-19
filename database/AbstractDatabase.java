package database; 


import java.util.*;

public abstract class AbstractDatabase<T> {

    protected ArrayList<T> records = new ArrayList<>();
    protected String filename;

    public AbstractDatabase(String filename) {
        this.filename=filename;
        }

    protected AbstractDatabase() {
    }

    public abstract void readFromFile();
    public abstract T createRecordFrom(String line);
    public ArrayList<T> returnAllRecords() {
        return records;
    }
    public abstract boolean contains(String key ) ;
    public abstract T getRecord(String key);
    public abstract void insertRecord(T record);
    public abstract void deleteRecord(String key);
    public abstract void saveToFile();
}
