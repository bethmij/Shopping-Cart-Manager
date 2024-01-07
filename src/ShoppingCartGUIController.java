import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ShoppingCartGUIController extends JFrame {
    User user = new User();

    ShoppingCartGUIController(){
        List<Map<String, String>> shoppingCartList = ShoppingCenterGUIController.shoppingCartList;
//        List<Map<String, String>> productList = WestministerShoppingManager.getProductList();

//        setShoppingCartGUI(shoppingCartList, productList);



//        String productID, List<Map<String,String>> productList
    }

    private void setShoppingCartGUI(List<Map<String, String>> shoppingCartList, List<Map<String, String>> productList) {
        setSize(700, 700);
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel(new GridLayout(1, 5, 10, 10));

        String[] columnNames = {"Product", "Quantity", "Price"};
        String[][] data = setShoppingCartList(shoppingCartList, productList);

        DefaultTableModel tableModel1 = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel1);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(70);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.PLAIN, 20));


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane);
        panel1.add(scrollPane, BorderLayout.CENTER);
        Border paddingBorder = new EmptyBorder(10, 40, 0, 40);
        panel1.setBorder(paddingBorder);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        Border paddingBorder2 = new EmptyBorder(30, 10, 5, 100);
        panel2.setBorder(paddingBorder2);

        int total = calculateTotal(shoppingCartList);
        double discountThreeItem = calculateThreeItemDiscount(shoppingCartList, total);
        double discountFirstPurchase = calculateFirstDiscount(shoppingCartList, total);


        JLabel lbl1 = new JLabel("<html><div style='text-align: right;'>Total : " +
                                                ""+total+"</div></html>");
        lbl1.setFont(new Font("Arial",Font.PLAIN,18));
        lbl1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel2.add(lbl1);
        panel2.add(Box.createVerticalStrut(10));

        JLabel lbl2 = new JLabel("<html><div style='text-align: right;'>First Purchase Discount (10%)"+
                                                ""+discountFirstPurchase+"  </div></html>");
        lbl2.setFont(new Font("Arial",Font.PLAIN,18));
        lbl2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel2.add(lbl2);
        panel2.add(Box.createVerticalStrut(10));

        JLabel lbl3 = new JLabel("<html><div style='text-align: right;'>Three items in same Category Discount (20%) :"+
                                        discountThreeItem+"  </div></html>");
        lbl3.setFont(new Font("Arial",Font.PLAIN,18));
        lbl3.setHorizontalAlignment(JLabel.RIGHT);
        panel2.add(lbl3);
        panel2.add(Box.createVerticalStrut(10));

        JLabel lbl4 = new JLabel("<html><div style='text-align: right;'>Final Total  </div></html>");
        lbl4.setFont(new Font("Arial",Font.PLAIN,18));
        lbl4.setHorizontalAlignment(JLabel.RIGHT);
        panel2.add(lbl4);

        JPanel panel3 = new JPanel();


        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.PAGE_END);

    }

    public static String[][] setShoppingCartList(List<Map<String, String>> shoppingCartList, List<Map<String, String>> productList){
        String[][] newDateList = new String[shoppingCartList.size()][];
        String product = null;
        int i = 0;

        for (Map<String, String> map : shoppingCartList) {
            for (Map<String, String> list : productList) {
                if(map.get("Product ID").equals(list.get("Product ID"))){
                    if (map.get("Product Type").equals("Electronics")) {
                        product = list.get("Product ID") + "\n" + list.get("Product Name") + "\n" + list.get("Product Brand") +
                                ", " + list.get("Warranty Period");
                    }else if (map.get("Product Type").equals("Clothing")) {
                        product = list.get("Product ID") + "\n" + list.get("Product Name") + "\n" + list.get("Product Size") +
                                ", " + list.get("Product Color");
                    }
                }
            }

            newDateList[i] = new String[3];
            newDateList[i][0]= product;
            newDateList[i][1]= map.get("Product Quantity");
            newDateList[i][2]= map.get("Product Price");
            i++;
        }
        return newDateList;
    }

    public int calculateTotal(List<Map<String, String>> shoppingCartList){
        int total = 0;
        for (Map<String, String> map : shoppingCartList) {
            total+=Double.parseDouble(map.get("Product Price"));
        }
        return total;
    }

    public double calculateThreeItemDiscount(List<Map<String, String>> shoppingCartList, int total){
        double discount = 0;
        int electronicCount = 0;
        int clothingCount = 0;

        for (Map<String, String> map : shoppingCartList) {
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
            discount = (20 / 100.0) * total;
        }
        return discount;
    }

    public double calculateFirstDiscount(List<Map<String, String>> shoppingCartList, int total){
        double discount = 0;

        for (Map<String, String> map : shoppingCartList) {
            if(map.get("Product UserName").equals(user.getUserName())) {
                discount = (10 / 100.0) * total;
            }
        }
        return discount;
    }
}
