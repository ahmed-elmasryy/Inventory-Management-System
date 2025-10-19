package model;

public class Product {

    //Atributes 
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    //Constructor
    public Product(String productID, String productName, String manufacturerName, String supplierName,
            int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    //Methodes 
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String lineRepresentation() {
        return (productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + price + ".");
    }

    public String getSearchKey() {
        return productID;
    }

}
