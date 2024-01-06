import java.util.HashMap;

public class OrderDetails {
    private String customerName;
    private HashMap<String, Integer> productDetails;
    private double price;

    public OrderDetails() {
    }

    public OrderDetails(String customerName, HashMap<String, Integer> productDetails, double price) {
        this.customerName = customerName;
        this.productDetails = productDetails;
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, Integer> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(HashMap<String, Integer> productDetails) {
        this.productDetails = productDetails;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

