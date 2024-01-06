import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class ShoppingCartController extends JFrame {
    private DefaultTableModel tableModel;
    private JLabel lbl4,lbl5,lbl6,lbl7,lbl8,lbl9;


    public  ShoppingCartController(){
        List<Map<String, String>> productList = WestministerShoppingManager.getProductList();
        setGUI(productList);
    }



    public void setGUI(List<Map<String, String>> productList){
        setSize(900, 800);
        setTitle("Westminister Shopping Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        getContentPane().setBackground(Color.BLUE);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
//        panel1.setBackground(Color.BLUE);
        panel1.add(Box.createVerticalStrut(100));

        JLabel lbl1 = new JLabel("Select Product Category");
        lbl1.setFont(new Font("Arial",Font.PLAIN,20));
        lbl1.setHorizontalAlignment(JLabel.LEFT);
        lbl1.setLocation(200, 500);
        panel1.add(lbl1, BorderLayout.PAGE_START);
        panel1.add(Box.createHorizontalStrut(40));


        String[] options = {"All", "Electronics", "Clothing"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Arial",Font.PLAIN,16));
        comboBox.setMaximumSize(new Dimension(comboBox.getMaximumSize().width, comboBox.getPreferredSize().height));
        panel1.add(comboBox);
        panel1.add(Box.createHorizontalStrut(250));

        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            switch (Objects.requireNonNull(selectedOption)){
                case "All":
                    updateTable (tableModel, productList);
                    break;
                case "Electronics":
                    List<Map<String, String>> electronicList = sortByCategory(productList, "Electronics");
                    updateTable (tableModel, electronicList);
                    break;
                case "Clothing":
                    List<Map<String, String>> clothingList = sortByCategory(productList, "Clothing");
                    updateTable (tableModel, clothingList);

            }
        });

        JButton btn1 = new JButton("Shopping Cart");
        btn1.setFont(new Font("", Font.PLAIN,16));
        btn1.setHorizontalAlignment(JButton.CENTER);
        panel1.add(btn1);
        panel1.add(Box.createHorizontalStrut(10));


        int rows = (int) Math.ceil((double) productList.size() / 5);
        JPanel panel2 = new JPanel(new GridLayout(Math.min(rows, 2), 5, 10, 10));
//        panel2.setBackground(Color.BLUE);
        panel1.add(Box.createVerticalStrut(100));

        String[] columnNames = {"Product ID", "Name", "Category", "Price ($)", "Info"};
        String[][] data = getCustomizedDataArray(productList);

        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String productID = (String) table.getValueAt(selectedRow, 0);
                    setSelectedProductDetails(productID, productList);
                }
            }
        });


        panel2.add(scrollPane, BorderLayout.CENTER);
        Border paddingBorder = new EmptyBorder(10, 40, 0, 40);
        panel2.setBorder(paddingBorder);


        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        Border paddingBorder2 = new EmptyBorder(10, 40, 30, 10);
        panel3.setBorder(paddingBorder2);


        JLabel lbl2 = new JLabel("Selected Product - Details");
        lbl2.setFont(new Font("Arial",Font.PLAIN,20));
        lbl2.setHorizontalAlignment(JLabel.CENTER);
        panel3.add(lbl2, BorderLayout.CENTER);


        JLabel lbl3 = new JLabel(" ");
        lbl3.setFont(new Font("Arial",Font.PLAIN,20));
        panel3.add(lbl3);

        lbl4 = new JLabel("Product ID :");
        lbl4.setFont(new Font("Arial",Font.PLAIN,18));
        lbl4.setHorizontalAlignment(JLabel.CENTER);
        lbl4.setOpaque(true);
        lbl4.setPreferredSize(new Dimension(10, lbl4.getPreferredSize().height));
        panel3.add(lbl4);

        lbl5 = new JLabel("Category :");
        lbl5.setFont(new Font("Arial",Font.PLAIN,18));
        lbl4.setHorizontalAlignment(JLabel.CENTER);
        panel3.add(lbl5);

        lbl6 = new JLabel("Name : ");
        lbl6.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl6);

        lbl7 = new JLabel("Size : ");
        lbl7.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl7);

        lbl8 = new JLabel("Colour : ");
        lbl8.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl8);

        lbl9 = new JLabel("Items Available : ");
        lbl9.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl9);

        JPanel panel4 = new JPanel();
        JButton btn2 = new JButton("Add to Shopping Cart");
        btn2.setFont(new Font("", Font.PLAIN,16));
        btn2.setHorizontalAlignment(JButton.CENTER);
        panel4.add(btn2);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setShoppingCartGUI(lbl4.getText(), productList);
            }
        });

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        Border paddingBorder3 = new EmptyBorder(10, 10, 30, 10);
        panel5.setBorder(paddingBorder3);
        panel5.add(panel3);
        panel5.add(panel4);


        add(panel1,BorderLayout.PAGE_START);
        add(panel2,BorderLayout.CENTER);
        add(panel5,BorderLayout.PAGE_END);
    }

    private String[][] getCustomizedDataArray(List<Map<String, String>> productList) {
        productList.sort(Comparator.comparing(m -> m.get("Product ID")));
        Iterator<Map<String, String>> iterator = productList.iterator();
        String[][] data = new String[productList.size()][5];
        String[] name = {"Product ID", "Product Name", "Product Type", "Product Price"};
        int i=0;
        while (iterator.hasNext()) {
            Map<String, String> map = iterator.next();
                for (int j = 0; j < 5; j++) {
                    if(j!=4) {
                        data[i][j] = map.get(name[j]);
                    }else {
                        if (map.get("Product Type").equals("Electronics")) {
                            data[i][4] = map.get("Product Brand") + ", " + map.get("Warranty Period");
                        } else {
                            data[i][4] = map.get("Product Size") + ", " + map.get("Product Color");
                        }
                    }
                }
            i++;
        }

        return data;
    }

    public List<Map<String, String>> sortByCategory(List<Map<String,String>> productList, String productType){
        Iterator<Map<String, String>> iterator = productList.iterator();
        List<Map<String, String>> newList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map<String, String> map = iterator.next();
            if (map.get("Product Type").equals(productType)) {
                newList.add(map);
            }
        }
        newList.sort(Comparator.comparing(m -> m.get("Product ID")));
        return newList;
    }

    public void updateTable (DefaultTableModel table, List<Map<String, String>> productList){
        String[][] customizedArray = getCustomizedDataArray(productList);
        table.setRowCount(0);
        for (String[] productDataList : customizedArray) {
            table.addRow(productDataList);
        }
    }

    public void setSelectedProductDetails(String productID, List<Map<String, String>> productList){

        for (Map<String, String> map : productList) {
            if (map.get("Product ID").equals(productID)) {
                lbl4.setText("Product ID : " + map.get("Product ID"));
                lbl5.setText("Category : " + map.get("Product Type"));
                lbl6.setText("Name : " + map.get("Product Name"));
                lbl9.setText("Items Available : " + map.get("Product Available Count"));

                if (map.get("Product Type").equals("Electronics")) {
                    lbl7.setText("Brand : " + map.get("Product Brand"));
                    lbl8.setText("Warranty Period : " + map.get("Warranty Period"));
                } else if (map.get("Product Type").equals("Clothing")) {
                    lbl7.setText("Size : " + map.get("Product Size"));
                    lbl8.setText("Colour : " + map.get("Product Color"));
                }
            }
        }
    }

    private void setShoppingCartGUI(String productID, List<Map<String,String>> productList) {
        setSize(700, 600);
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel2 = new JPanel(new GridLayout(1, 5, 10, 10));

        String[] columnNames = {"Product", "Quantity", "Price"};
        String[][] data = new String[0][];

        DefaultTableModel tableModel2 = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel2);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel2.add(scrollPane);


    }

    public void updateShoppingCart(List<Map<String,String>> productList, String productID, DefaultTableModel table){
        String[] newDateList = new String[0];
        String product = null;
        for (Map<String, String> map : productList) {
            if (map.get("Product ID").equals(productID)) {
                if (map.get("Product Type").equals("Electronics")) {
                    product = map.get("Product ID") + "\n" + map.get("Product Name") + "\n" + map.get("Product Brand") +
                            ", " + map.get("Warranty Period");
                } else if (map.get("Product Type").equals("Clothing")) {
                    product = map.get("Product ID") + "\n" + map.get("Product Name") + "\n" + map.get("Product Size") +
                            ", " + map.get("Product Color");
                }
            }
            newDateList = new String[]{product, String.valueOf(1), map.get("Product Price")};
        }

        table.addRow(newDateList);
    }


}

