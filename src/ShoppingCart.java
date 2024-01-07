import javax.swing.*;
import java.util.*;

public class ShoppingCart  {

    private List<Product> productList;

    public ShoppingCart() {
    }

    public ShoppingCart(List<Product> productList) {
        this.productList = productList;
    }


    public List<Product> getProductList() {
        return productList;
    }


    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addItems(String productID, List<Product> productList, JLabel lbl5){
        String[] id = (productID.split("Product ID : "));
        String[] category = (lbl5.getText().split("Category : "));
        Map<String, String> list = new HashMap<>();


        for (Product product : productList) {
            if (product.getProductID().equals(id[1])) {
                list.put("Product ID", id[1]);
                list.put("Product Quantity", String.valueOf(1));
                list.put("Product Price", String.valueOf(product.getPrice()));
                list.put("Product Type", category[1]);
                Iterator<Map<String, String>> iterator = ShoppingCenterGUIController.shoppingCartList.iterator();
                while (iterator.hasNext()) {
                    Map<String, String> dataList = iterator.next();
                    if (product.getProductID().equals(dataList.get("Product ID"))) {
                        iterator.remove();
                        list.put("Product Quantity", String.valueOf(Integer.parseInt(dataList.get("Product Quantity"))+1));
                        double currentPrice = Double.parseDouble(dataList.get("Product Price"));
                        double additionalPrice = product.getPrice();
                        double newPrice = currentPrice + additionalPrice;
                        list.put("Product Price", String.valueOf(newPrice));
                    }
                }
            }
        }
        ShoppingCenterGUIController.shoppingCartList.add(list);
    }


    public void removeItems(List<Product> productList, Scanner scanner){

    }


    public List<Double> calcTotalCost(){

        OrderDetails orderDetails = ShoppingCartGUIController.getOrderDetails();
        User user = new User();
        List<Double> totalCal = new ArrayList<>();

        double total = 0;
        for (Map<String, String> map : ShoppingCenterGUIController.shoppingCartList) {
            total+=Double.parseDouble(map.get("Product Price"));
        }

        double discountThreePurchase = 0;
        int electronicCount = 0;
        int clothingCount = 0;

        for (Map<String, String> map : ShoppingCenterGUIController.shoppingCartList) {
            if(map.get("Product Type").equals("Electronics")){
                if(map.get("Product Quantity").compareTo("1")>0){
                    electronicCount+=Integer.parseInt(map.get("Product Quantity"));
                }else {
                    electronicCount++;
                }
            }else if(map.get("Product Type").equals("Clothing")){
                if(map.get("Product Quantity").compareTo("1")>0){
                    clothingCount+=Integer.parseInt(map.get("Product Quantity"));
                }else {
                    clothingCount++;
                }
            }
        }
        if(electronicCount==3 || clothingCount==3){
            discountThreePurchase = (20 / 100.0) * total;
        }

        double discountFirstPurchase = 0;
        if (orderDetails.getCustomerName()==null || !orderDetails.getCustomerName().equals(user.getUserName())) {
            discountFirstPurchase = (10 / 100.0) * total;
        }

        double finalTotal = total-(discountFirstPurchase+discountThreePurchase);

        totalCal.add(total);
        totalCal.add(discountFirstPurchase);
        totalCal.add(discountThreePurchase);
        totalCal.add(finalTotal);

        return totalCal;

    }




}
