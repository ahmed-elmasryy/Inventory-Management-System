import java.time.LocalDate;
import java.util.ArrayList;

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
        Product newProduct = new Product(productId, productName, manufacturerName, supplierName, quantity, 0.0f);
        productDatabase.insertRecord(newProduct);
        productDatabase.saveToFile();
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
            customerProductDatabase.saveToFile();
            Product product = productDatabase.getRecord(productID);
            product.setQuantity(product.getQuantity() - 1);
            productDatabase.saveToFile();
            System.out.println("Purchase recorded successfully for product ID " + productID + ".");
            return true;
        }
        System.out.println("Product with ID " + productID + " does not exist.");
        return false;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate,LocalDate returnDate){
        if(returnDate.isBefore(purchaseDate) || returnDate.isAfter(purchaseDate.plusDays(14))){
            System.out.println("Return period has expired.");
            return -1;
        }
        if(productDatabase.getRecord(productID) == null || customerProductDatabase.getRecord(productID) == null){
            System.out.println("Invalid product ID.");
            return -1;
        }
        Product product = productDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        productDatabase.saveToFile();
        customerProductDatabase.deleteRecord(productID);
        customerProductDatabase.saveToFile();
        System.out.println("Product with ID " + productID + " returned successfully.");
        return 0;
    }

    public boolean applyPayment(String customerSSN,LocalDate purchaseDate){
        if(customerProductDatabase.getRecord(customerSSN) == null ){
            System.out.println("No purchase record found for customer SSN " + customerSSN + ".");
            return false;
        }
        CustomerProduct customerProduct = customerProductDatabase.getRecord(customerSSN);
        if(!customerProduct.isPaid()){
            customerProduct.setPaid(true);
            customerProductDatabase.saveToFile();
            System.out.println("Payment applied successfully for customer SSN " + customerSSN + ".");
            return true;
        }
        return true;
    }

    public void logout(){
        System.out.println("Employee logged out.");
        productDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}