public class Electronics extends Product{

    private String brand;
    private String warrantyPeriod;

    public Electronics() {
    }

    public Electronics(String brand, String warrantyPeriod) {
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public Electronics(String productID, String name, int productAblNo, double price, String productType, String brand, String warrantyPeriod) {
        super(productID, name, productAblNo, price, productType);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return  "Product ID : " + getProductID() +"\n"+
                "Product Type : " + "Electronics" + '\n' +
                "Product Name : " + getName() + '\n' +
                "Product Available : " + getProductAblNo() + '\n' +
                "Product Price : " + getPrice() + '\n' +
                "Product Brand : " + brand + '\n' +
                "Product Warranty Period : " + warrantyPeriod +"" ;
    }
}
