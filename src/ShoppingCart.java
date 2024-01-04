import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List productList;

    public ShoppingCart() {
    }

    public ShoppingCart(List productList) {
        this.productList = productList;
    }

    public List getProductList() {
        return productList;
    }

    public void setProductList(List productList) {
        this.productList = productList;
    }

    public void addItems(){}

    public void removeItems(){}

    public void calcTotalCost(){}


}
