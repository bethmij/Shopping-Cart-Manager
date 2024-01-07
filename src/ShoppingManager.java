import java.util.List;
import java.util.Scanner;

public interface ShoppingManager {

    void addItems(List<Product> productList, Scanner scanner);

    void removeItems(List<Product> productList, Scanner scanner);

    void displayItems(List<Product> productList, Scanner scanner);

}
