import java.io.*;
import java.util.*;

public class WestministerShoppingManager implements ShoppingManager {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.println("----------------------------------------------------");
        System.err.println("|      WELCOME TO WESTMINISTER SHOPPING MANAGER    |");
        System.err.println("----------------------------------------------------");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProductList(getProductList());

        while (true) {
            System.out.println("\n A) Add a new product\n B) Print the list of products\n C) Delete a product\n D) End program");
            System.out.print(" Choice your option (A/B/C/D) -> ");
            String choice = scanner.next().toUpperCase();
            if (choice.length() == 1 && choice.charAt(0) == 'A' || choice.charAt(0) == 'B' || choice.charAt(0) == 'C' || choice.charAt(0) == 'D') {
                switch (choice.charAt(0)) {
                    case 'A':
                        addNewProducts(shoppingCart.getProductList(), scanner);
                        break;
                    case 'B':
                        displayProductList(shoppingCart.getProductList(), scanner);
                        break;
                    case 'C':
                        removeProducts(shoppingCart.getProductList(), scanner);
                        break;

                    case 'D':
                        System.exit(0);
                }
            } else {
                System.out.println(" Invalid Input! Please try again");
            }
        }
    }

    public static List<Product> getProductList() {
        String filePath = "data.txt";
        List<Product> resultSet = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            resultSet = (List<Product>) ois.readObject();
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return resultSet;
    }

    public static void addNewProducts(List<Product> productList, Scanner scanner){
        ShoppingManager shoppingManager = new WestministerShoppingManager();
        shoppingManager.addItems(productList, scanner);
    }

    public static void removeProducts(List<Product> productList, Scanner scanner){
        ShoppingManager shoppingManager = new WestministerShoppingManager();
        shoppingManager.removeItems(productList, scanner);
    }

    public static void displayProductList(List<Product> productList, Scanner scanner) {
        ShoppingManager shoppingManager = new WestministerShoppingManager();
        shoppingManager.displayItems(productList, scanner);
    }

    private static void sortByProductType(List<Product> productList, String productType) {
        List<Product> newList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductType().equals(productType)) {
                newList.add(product);
            }
        }
        if (!newList.isEmpty()) {
            newList.sort(Comparator.comparing(Product::getProductID));
            for (Product product : newList) {
                System.out.println(product);
                System.out.println();
            }
        } else {
            System.out.println("No products to display in " + productType);
        }
    }


    @Override
    public void addItems(List<Product> productList, Scanner scanner) {
        System.err.println("|  ADD A NEW PRODUCT  |");
        scanner.nextLine();

        String productID;
        boolean isContains=false;
        while (true){
            System.out.print("\nProduct ID : ");
            String id = scanner.nextLine();
            for (Product product:productList) {
                if (product.getProductID().equals(id)) {
                    isContains = true;
                }
            }
            if (!isContains){
                productID=id;
                break;
            }else {
                isContains=false;
                System.out.println("Product already added!");
            }
        }

        System.out.print("Product name : ");
        String productName = scanner.nextLine();
        int productAblNo;
        double productPrice;

        while (true) {
            System.out.print("Number of available products : ");
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                productAblNo = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Only numerics!");
            }
        }

        while (true) {
            System.out.print("Product price : ");
            String input = scanner.nextLine();
            if (input.matches("\\d+(\\.\\d{0,3})?")) {
                productPrice = Double.parseDouble(input);
                break;
            } else {
                System.out.println("Only numerics!");
            }
        }


        while (true) {
            System.out.print("State whether its electronic or clothing Product \n Electronic : E/e \n Clothing : C/c \n -> ");
            String choice = scanner.next().toUpperCase();
            if (choice.length() == 1 && choice.charAt(0) == 'E' || choice.charAt(0) == 'C') {
                char productType = choice.charAt(0);

                String productBrand;
                if (productType == 'E') {
                    System.out.print("Product brand name : ");
                    productBrand = scanner.nextLine();
                    productBrand = scanner.nextLine();

                    System.out.print("Product warranty period : ");
                    String warrantyPeriod = scanner.nextLine();

                    while (true) {
                        System.out.print("Do you want to save this product Y/N : ");
                        choice = scanner.next().toUpperCase();

                        if (choice.length() == 1 && choice.charAt(0) == 'Y' || choice.charAt(0) == 'N') {
                            if (choice.charAt(0) == 'Y') {
                                if (productList.size() < 50) {
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

                    List<String> validSizes = Arrays.asList("XXS", "XS", "S", "M", "L", "XL", "XXL");

                    String productSize;
                    while (true) {
                        System.out.print("Product size (XXS/XS/S/M/L/XL/XXL): ");
                        String input = scanner.next().toUpperCase().trim();

                        if (validSizes.contains(input)){
                            productSize=input;
                            break;
                        }else {
                            System.out.println("Invalid Input! Please try again");
                        }
                    }

                    System.out.print("Product productColor : ");
                    String productColor = scanner.next().trim();

                    while (true) {
                        System.out.print("Do you want to save this product Y/N : ");
                        choice = scanner.next().toUpperCase();
                        if (choice.length() == 1 && choice.charAt(0) == 'Y' || choice.charAt(0) == 'N') {
                            if (choice.charAt(0) == 'Y') {
                                if (productList.size() < 50) {
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
            } else {
                System.out.println(" Invalid Input! Please try again");
            }
        }
    }

    @Override
    public void removeItems(List<Product> productList, Scanner scanner) {
        System.err.println("|  DELETE A PRODUCT  |");
        scanner.nextLine();
        int count = 0, prevCount = productList.size();

        while (true) {
            System.out.print("\nInsert the product ID : ");
            String productID = scanner.nextLine();

            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getProductID().equals(productID)) {
                    iterator.remove();
                    saveFile(productList, product.getName() + " Deleted Successfully!\n Products left = " + productList.size());
                } else {
                    count++;
                }
            }
            if (count == prevCount) {
                System.out.println("Invalid product ID! Please try again");
            } else {
                break;
            }
        }
    }

    @Override
    public void displayItems(List<Product> productList, Scanner scanner) {
        System.err.println("|  DISPLAY PRODUCT LIST  |");
        scanner.nextLine();

        while (true) {
            System.out.println("\n A) Full List\n B) Electronics\n C) Clothing");
            System.out.print(" Choice your option (A/B/C) -> ");
            String choice = scanner.next().toUpperCase();
            if (choice.length() == 1 && choice.charAt(0) == 'A' || choice.charAt(0) == 'B' || choice.charAt(0) == 'C') {
                System.out.println();
                if (choice.charAt(0) == 'A') {
                    productList.sort(Comparator.comparing(Product::getProductID));
                    for (Product product : productList) {
                        System.out.println(product);
                        System.out.println("\n");
                    }
                    break;
                } else if (choice.charAt(0) == 'B') {
                    sortByProductType(productList, "Electronics");
                    break;
                } else if (choice.charAt(0) == 'C') {
                    sortByProductType(productList, "Clothing");
                    break;
                }
            }
        }
    }

    static void saveFile(List<Product> productList, String msg) {
        String filePath = "data.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(productList);
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}

