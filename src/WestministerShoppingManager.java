import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WestministerShoppingManager implements ShoppingManager{
    static Map<String, String> products = new LinkedHashMap<>();
    static List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
    static List<Map<String, String>> newProductList = new ArrayList<>();
    static String filePath = "data.txt";

    public static void main(String[] args) {

        productList = getProductList();
        addNewProduct();
//        displayProductList(productList);
    }

    public static void addNewProduct(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product ID : ");
            String productID = scanner.nextLine();

            System.out.print("Enter product name : ");
            String productName = scanner.nextLine();

            System.out.print("Enter total number of products available : ");
            int productAblNo = scanner.nextInt();

            System.out.print("Enter product price : ");
            double productPrice = scanner.nextDouble();

            System.out.print("State whether its electronic or clothing Product \n Electronic : E/e \n Clothing : C/c \n -> ");
            char productType = scanner.next().charAt(0);
            if (productType == 'E' || productType == 'e') {

                System.out.print("Enter product brand name: ");
                String productBrand = scanner.next().trim();

                // Check if productBrand is empty and prompt the user until a non-empty string is entered
//            while (productBrand.isEmpty()) {
//                System.err.println("Product brand cannot be empty. Please enter a valid product brand.");
//                System.out.print("Enter product brand name: ");
//                productBrand = scanner.next().trim();
//            }

                System.out.print("Enter warranty period: ");
                int warrantyPeriod = scanner.nextInt();

//            while (true) {
//                try {
//                    warrantyPeriod =
//                    break;  // Exit the loop if a valid integer is entered
//                } catch (java.util.InputMismatchException e) {
//                    System.err.println("Invalid input for warranty period. Please enter a valid integer.");
//                    scanner.next(); // Consume invalid input to avoid infinite loop
//                }
//            }
                System.out.println("Do you want to save this product Y/y/N/n : ");
                char choice = scanner.next().charAt(0);

                if (choice == 'Y' || choice == 'y') {
                    products = new LinkedHashMap<>();
                    products.put("Product ID", productID);
                    products.put("Product Name", productName);
                    products.put("Product Available Count", String.valueOf(productAblNo));
                    products.put("Product Price", String.valueOf(productPrice));
                    products.put("Product Brand", productBrand);
                    products.put("Warranty Period", String.valueOf(warrantyPeriod));
                    productList.add(products);
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(String.valueOf(productList));
                    } catch (IOException e) {
                        System.err.println("Error writing to the file: " + e.getMessage());
                    }
                } else if (choice == 'N' || choice == 'n') {
                    break;
                } else {
                    System.out.println("Invalid Input \n Please Try Again");
                }


            } else if (productType == 'C' || productType == 'c') {
                System.out.print("Enter product productSize (XXS/XS/S/M/L/XL/XXL): ");
                String productSize = scanner.next().trim();

                System.out.print("Enter product productColor : ");
                String productColor = scanner.next().trim();

                System.out.println("Do you want to save this product Y/y/N/n : ");
                char choice = scanner.next().charAt(0);

                if (choice == 'Y' || choice == 'y') {
                    products = new LinkedHashMap<>();
                    products.put("Product ID", productID);
                    products.put("Product Name", productName);
                    products.put("Product Available Count", String.valueOf(productAblNo));
                    products.put("Product Price", String.valueOf(productPrice));
                    products.put("Product Size", productSize);
                    products.put("Product Color", productColor);
                    productList.add(products);
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(String.valueOf(productList));
                    } catch (IOException e) {
                        System.err.println("Error writing to the file: " + e.getMessage());
                    }
                } else if (choice == 'N' || choice == 'n') {
                    break;
                } else {
                    System.out.println("Invalid Input \n Please Try Again");
                }
            } else {
                System.out.println("Invalid Input \n Please Try Again");
            }


        }

    }

    public static void deleteProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the product ID");
        String productID = scanner.nextLine();
    }

    public static List<Map<String, String>> getProductList() {
        Path path = Paths.get(filePath);
        String fileContent = null;
        try {
            fileContent = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  convertStringToList(fileContent);
    }

    public static void displayProductList(List<Map<String, String>> productListReading) {
        for (Map<String, String> product :productListReading) {
            for (Map.Entry<String, String> entry : product.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println("\n");
        }
    }


    private static List<Map<String, String>> convertStringToList(String input) {
        List<Map<String, String>> resultList = new ArrayList<>();

        if (input.equals(" ")) {
            String content = input.substring(1, input.length() - 1);

            String[] mapRepresentations = content.split("\\},\\s*\\{");

            for (String mapRepresentation : mapRepresentations) {
                Map<String, String> map = new LinkedHashMap<>();
                mapRepresentation = mapRepresentation.replaceFirst("\\{", "").replaceFirst("\\}", "");
                String[] keyValues = mapRepresentation.split(",\\s*");

                for (String keyValue : keyValues) {
                    String[] parts = keyValue.split("=");

                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        map.put(key, value);
                    }
                }
                resultList.add(map);
            }
        }

        return resultList;
    }


}

