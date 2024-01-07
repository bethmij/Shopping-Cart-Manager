public class Clothing extends Product{

    private String size;
    private String color;

    public Clothing() {
    }

    public Clothing(String size, String color) {
        this.size = size;
        this.color = color;
    }

    public Clothing(String productID, String name, int productAblNo, double price, String productType, String size, String color) {
        super(productID, name, productAblNo, price, productType);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
