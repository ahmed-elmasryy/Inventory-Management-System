package roles;

import database.EmployeeUserDatabase;
import java.util.ArrayList;
import model.EmployeeUser;

public class AdminRole {

    // Attribute
    private EmployeeUserDatabase database = new EmployeeUserDatabase("Employees.txt");

    // Constructor
    public AdminRole(EmployeeUserDatabase database) {
        this.database = database;
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        
        database.insertRecord(employee);
        
        database.saveToFile();
    }

    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> employees = database.returnAllRecords();
        EmployeeUser[] arr = new EmployeeUser[employees.size()];
        return employees.toArray(arr);
    }

    public void removeEmployee(String key) {
        database.deleteRecord(key);  
        database.saveToFile();       
    }

    public void logout() {
        database.saveToFile();
    }
}
