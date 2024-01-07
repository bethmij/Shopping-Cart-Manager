import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WestministerShoppingManager implements ShoppingManager{
    static String filePath = "data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.println("----------------------------------------------------");
        System.err.println("|      WELCOME TO WESTMINISTER SHOPPING MANAGER    |");
        System.err.println("----------------------------------------------------");

//        List<Map<String, String>> productList = getProductList();
        List<Product> productList = getProductList();
//        System.out.println(productList.get(0).getProductID());

        while (true) {
            System.out.println("\n A) Add a new product\n B) Print the list of products\n C) Delete a product\n D) End program");
            System.out.print(" Choice your option (A/B/C/D) -> ");
            String choice = scanner.next().toUpperCase();
            if (choice.length()==1 && choice.charAt(0)=='A' || choice.charAt(0)=='B' || choice.charAt(0)=='C' || choice.charAt(0)=='D') {
                switch (choice.charAt(0)){
                    case 'A' :
                        addNewProduct(productList, scanner);
                        break;

                    case 'B' :
                        displayProductList(productList, scanner);
                        break;

                    case 'C' :
//                        deleteProduct(productList, scanner);
                        break;

                    case 'D' : System.exit(0);
                }
            }else {
                System.out.println(" Invalid Input! Please try again");
            }
        }
    }

    public static void addNewProduct(List<Product> productList, Scanner scanner){
        System.err.println("|  ADD A NEW PRODUCT  |");
        scanner.nextLine();

        System.out.print("\nProduct ID : ");
        String productID = scanner.nextLine();

        System.out.print("Product name : ");
        String productName = scanner.nextLine();
        int productAblNo;
        double productPrice;

        while (true){
            System.out.print("Number of available products : ");
            String input = scanner.nextLine();
            if(input.matches("\\d+")){
                productAblNo = Integer.parseInt(input);
                break;
            }else {
                System.out.println("Only numerics!");
            }
        }

        while (true){
            System.out.print("Product price : ");
            String input = scanner.nextLine();
            if(input.matches("\\d+(\\.\\d{0,3})?")){
                productPrice = Double.parseDouble(input);
                break;
            }else {
                System.out.println("Only numerics!");
            }
        }


            while (true) {
            System.out.print("State whether its electronic or clothing Product \n Electronic : E/e \n Clothing : C/c \n -> ");
            String choice = scanner.next().toUpperCase();
            if (choice.length() == 1 && choice.charAt(0) == 'E' || choice.charAt(0) == 'C') {
                char productType = choice.charAt(0);
                Map<String, String> products;

                if (productType == 'E') {
                    System.out.print("Product brand name : ");
                    String productBrand = scanner.next().trim();

                    System.out.print("Product warranty period : ");
                    String warrantyPeriod = scanner.next();

                    while (true) {
                        System.out.print("Do you want to save this product Y/N : ");
                        choice = scanner.next().toUpperCase();

                        if (choice.length() == 1 && choice.charAt(0) == 'Y' || choice.charAt(0) == 'N') {
                            if (choice.charAt(0) == 'Y') {
                                if(productList.size()<50) {
                                    Electronics electronics =
                                       new Electronics(productID, productName, productAblNo, productPrice, "Electronics", productBrand, warrantyPeriod);
                                    productList.add(electronics);
                                    break;
                                }
                            } else if (choice.charAt(0) == 'N') {
                                break;
                            } else {
                                System.out.println("Invalid Input \n Please Try Again");
                            }
                        }
                    }
                } else if (productType == 'C') {
                    System.out.print("Product size (XXS/XS/S/M/L/XL/XXL): ");
                    String productSize = scanner.next().trim();

                    System.out.print("Product productColor : ");
                    String productColor = scanner.next().trim();

                    while (true) {
                        System.out.print("Do you want to save this product Y/N : ");
                        choice = scanner.next().toUpperCase();
                        if (choice.length() == 1 && choice.charAt(0) == 'Y' || choice.charAt(0) == 'N') {
                            if (choice.charAt(0) == 'Y') {
                                if(productList.size()<50) {
                                    Clothing clothing =
                                            new Clothing(productID, productName, productAblNo, productPrice, "Clothing", productSize, productColor);
                                    productList.add(clothing);
                                    break;
                                }
                            } else if (choice.charAt(0) == 'N') {
                                break;
                            } else {
                                System.out.println("Invalid Input! Please Try Again");
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid Input! Please Try Again");
                }
                saveFile(productList, "Product saved successfully!");
                break;
            }else{
                System.out.println(" Invalid Input! Please try again");
            }
        }
    }

    private static void saveFile(List<Product> productList, String msg) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(productList);
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }


    public static void deleteProduct(List<Map<String, String>> productList, Scanner scanner){
        System.err.println("|  DELETE A PRODUCT  |");
        scanner.nextLine();
        int count = 0, prevCount = productList.size();

        while (true) {
            System.out.print("\nInsert the product ID : ");
            String productID = scanner.nextLine();

            Iterator<Map<String, String>> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Map<String, String> map = iterator.next();
                if (map.get("Product ID").equals(productID)) {
                    iterator.remove();
//                    saveFile(productList, map.get("Product Name") + " Deleted Successfully!\n Products left = " + productList.size());
                } else {
                    count++;
                }
            }

            if(count == prevCount){
                System.out.println("Invalid product ID! Please try again");
            }else {
                break;
            }
        }
    }

    public static List<Product> getProductList() {
        List<Product> resultSet = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            resultSet = (List) ois.readObject();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return resultSet;
    }

    public static void displayProductList(List<Product> productList, Scanner scanner) {
        System.err.println("|  DISPLAY PRODUCT LIST  |");
        scanner.nextLine();

        while (true) {
            System.out.println("\n A) Full List\n B) Electronics\n C) Clothing");
            System.out.print(" Choice your option (A/B/C) -> ");
            String choice = scanner.next().toUpperCase();
            if (choice.length()==1 && choice.charAt(0)=='A' || choice.charAt(0)=='B' || choice.charAt(0)=='C') {
                System.out.println();
                if (choice.charAt(0)=='A'){
                    productList.sort(Comparator.comparing(Product::getProductID));
                    for (Product product : productList) {
                        System.out.println(product);
//                        for (Map.Entry<String, String> entry : product.entrySet()) {
//                            System.out.println(" "+entry.getKey() + " : " + entry.getValue());
//                        }
                        System.out.println("\n");
                    }
                    break;
                }else if (choice.charAt(0)=='B'){
                    sortByProductType(productList, "Electronics");
                    break;
                }else if (choice.charAt(0)=='C'){
                    sortByProductType(productList, "Clothing");
                    break;
                }



            }

        }
    }

    private static void sortByProductType(List<Product> productList, String ProductType) {
//        Iterator<Map<String, String>> iterator = productList.iterator();
//        List<Map<String, String>> newList = new ArrayList<>();
//        while (iterator.hasNext()) {
//            Map<String, String> map = iterator.next();
//            if (map.get("Product Type").equals(ProductType)) {
//                newList.add(map);
//            }
//        }
//        newList.sort(Comparator.comparing(m -> m.get("Product ID")));
//        for (Map<String, String> product : newList) {
//            for (Map.Entry<String, String> entry : product.entrySet()) {
//                System.out.println(" "+entry.getKey() + " : " + entry.getValue());
//            }
//            System.out.println();
//        }
        List<Product> newList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductID().equals(ProductType)) {
                newList.add(product);
            }
        }
        newList.sort(Comparator.comparing(Product::getProductID));
        for (Product product : newList) {
            System.out.println(product);
            System.out.println();
        }

    }


    private static List<Map<String, String>> convertStringToList(String input) {
        List<Map<String, String>> resultList = new ArrayList<>();

        if (input!=null && !input.isEmpty()) {
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

