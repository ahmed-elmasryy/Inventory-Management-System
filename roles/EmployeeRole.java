import java.time.LocalDate;

public class EmployeeRole {
    ProductDatabase productDatabase = new ProductDatabase();
    CustomerProductDatabase customerProductDatabase = new CustomerProductDatabase();
    public EmployeeRole() {}

    public void addProduct(String productId,String productName,
                           String manufacturerName,String supplierName,int quantity){

    }

    public Product[] getListOfProducts(){
        return null;
    }

    public CustomerProduct[] getListOfPurchasingOperations(){
        return null;
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        return false;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate,LocalDate returnDate){
        return 0;
    }

    public boolean applyPayment(String customerSSN,LocalDate purchaseDate){
        return false;
    }

    public void logout(){

    }
}