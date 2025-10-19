package database;

import java.io.*;
import java.util.*;
import model.CustomerProduct;

public class CustomerProductDatabase extends AbstractDatabase<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    
}
