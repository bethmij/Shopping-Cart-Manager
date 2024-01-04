public abstract class Product {

    private String productID;
    private String name;
    private int productAblNo;
    private double price;

    public Product() {
    }

    public Product(String productID, String name, int productAblNo, double price) {
        this.productID = productID;
        this.name = name;
        this.productAblNo = productAblNo;
        this.price = price;
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


}
