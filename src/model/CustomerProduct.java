package model;

import java.time.LocalDate;

public class CustomerProduct {

    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String lineRepresentation() {
        return (customerSSN + "," + productID + "," + purchaseDate + "," + paid + ".");
    }

    public boolean isPaid() {
        if(paid == true) {
            return true;
        }
        return false;
    }
    
    public String getSearchKey(){
     return (customerSSN + "," + productID + "," + purchaseDate + ".");
    }
    
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
