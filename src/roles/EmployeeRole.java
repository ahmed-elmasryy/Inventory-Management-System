package roles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.CustomerProduct;
import model.Product;
import database.CustomerProductDatabase;
import database.ProductDatabase; 

public class EmployeeRole {
    ProductDatabase productDatabase = new ProductDatabase("Products.txt");
    CustomerProductDatabase customerProductDatabase = new CustomerProductDatabase("CustomerProducts.txt");
    public EmployeeRole() {
        productDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productId,String productName,
                           String manufacturerName,String supplierName,int quantity){
        if(productDatabase.contains(productId)){
            System.out.println("Product with ID " + productId + " already exists.");
            return;
        }
        Product newProduct = new Product(productId, productName, manufacturerName, supplierName, quantity,750.0f);
        productDatabase.insertRecord(newProduct);
        System.out.println("Product with ID " + productId + " added successfully.");
    }

    public void addProduct(String productId,String productName,
                           String manufacturerName,String supplierName,int quantity, float price){
        if(productDatabase.contains(productId)){
            System.out.println("Product with ID " + productId + " already exists.");
            return;
        }
        Product newProduct = new Product(productId, productName, manufacturerName, supplierName, quantity, price);
        productDatabase.insertRecord(newProduct);
        System.out.println("Product with ID " + productId + " added successfully.");
    }

    public Product[] getListOfProducts(){
        ArrayList<Product> products = productDatabase.returnAllRecords();
        return products.toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations(){
        ArrayList<CustomerProduct> customerProducts = customerProductDatabase.returnAllRecords();
        return customerProducts.toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        if(productDatabase.contains(productID)){
            if(productDatabase.getRecord(productID).getQuantity() ==0){
                System.out.println("Product with ID " + productID + " is out of stock.");
                return false;
            }
            CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate);
            customerProductDatabase.insertRecord(newPurchase);
            Product product = productDatabase.getRecord(productID);
            product.setQuantity(product.getQuantity() - 1);
            System.out.println("Purchase recorded successfully for product ID " + productID + ".");
            return true;
        }
        System.out.println("Product with ID " + productID + " does not exist.");
        return false;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate,LocalDate returnDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        purchaseDate.format(formatter);
        if(returnDate.isBefore(purchaseDate) || returnDate.isAfter(purchaseDate.plusDays(14))){
            System.out.println("Return period has expired.");
            return -1;
        }
        if(productDatabase.getRecord(productID) == null || !customerProductDatabase.contains(customerSSN + "," + productID+ "," + purchaseDate + ".")){
            System.out.println("Invalid product ID.");
            return -1;
        }
        Product product = productDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(customerSSN + "," + productID+ "," + purchaseDate + ".");
        System.out.println("Product with ID " + productID + " returned successfully.");
        return 0;
    }

    public boolean applyPayment(String customerSSN,LocalDate purchaseDate){
        CustomerProduct[] purchases = getListOfPurchasingOperations();
        for(CustomerProduct cp : purchases){
            if(cp.getCustomerSSN().equals(customerSSN) && cp.getPurchaseDate().equals(purchaseDate)){
                if(cp.isPaid()){
                    System.out.println("Payment has already been made for this purchase.");
                    return false;
                }
                cp.setPaid(true);
                System.out.println("Payment applied successfully for customer SSN " + customerSSN + ".");
                customerProductDatabase.saveToFile();
                return true;
            }
        }
        System.out.println("No matching purchase found for customer SSN " + customerSSN + ".");
        return false;
    }

    public void logout(){
        System.out.println("Employee logged out.");
        productDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}