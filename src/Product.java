import java.io.Serializable;

public abstract class Product implements Serializable {

    private String productID;
    private String name;
    private int productAblNo;
    private double price;
    private String productType;

    public Product() {
    }

    public Product(String productID, String name, int productAblNo, double price, String productType) {
        this.productID = productID;
        this.name = name;
        this.productAblNo = productAblNo;
        this.price = price;
        this.productType = productType;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductAblNo() {
        return productAblNo;
    }

    public void setProductAblNo(int productAblNo) {
        this.productAblNo = productAblNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", name='" + name + '\'' +
                ", productAblNo=" + productAblNo +
                ", price=" + price +
                ", productType='" + productType + '\'' +
                '}';
    }
}
