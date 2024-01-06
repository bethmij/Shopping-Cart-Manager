import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ShoppingCartController extends JFrame {
    private JPanel panel1, panel2, panel3;
    private JLabel lbl1;
    private JComboBox<String> comboBox;
    private JButton btn1;
    private JTable table;

    public  ShoppingCartController(){

        List<Map<String, String>> productList = WestministerShoppingManager.getProductList();
        setGUI(productList);
    }

    public void setGUI(List<Map<String, String>> productList){
        setSize(900, 700);
        setTitle("Westminister Shopping Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        getContentPane().setBackground(Color.BLUE);


        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
//        panel1.setBackground(Color.BLUE);
        panel1.add(Box.createVerticalStrut(100));

        lbl1=new JLabel("Select Product Category");
        lbl1.setFont(new Font("Arial",Font.PLAIN,20));
        lbl1.setHorizontalAlignment(JLabel.LEFT);
        lbl1.setLocation(200, 500);
        panel1.add(lbl1, BorderLayout.PAGE_START);
        panel1.add(Box.createHorizontalStrut(40));


        String[] options = {"All", "Electronics", "Clothing"};
        comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Arial",Font.PLAIN,16));
        comboBox.setMaximumSize(new Dimension(comboBox.getMaximumSize().width, comboBox.getPreferredSize().height));
        panel1.add(comboBox);
        panel1.add(Box.createHorizontalStrut(250));

        btn1=new JButton("Shopping Cart");
        btn1.setFont(new Font("",0,16));
        btn1.setHorizontalAlignment(JButton.CENTER);
        panel1.add(btn1);
        panel1.add(Box.createHorizontalStrut(10));


        int rows = (int) Math.ceil((double) productList.size() / 5);
        panel2 = new JPanel(new GridLayout(rows, 5, 10, 10));
//        panel1.setBackground(Color.BLUE);
        panel1.add(Box.createVerticalStrut(100));

        String[] columnNames = {"Product ID", "Name", "Category", "Price ($)", "Info"};
        String[][] data = setDataToArray(productList);

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
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

        panel2.add(scrollPane, BorderLayout.CENTER);
        int paddingSize = 10;
        Border paddingBorder = new EmptyBorder(10, 40, 10, 40);
        panel2.setBorder(paddingBorder);

        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));




        add(panel1,BorderLayout.PAGE_START);
        add(panel2,BorderLayout.CENTER);
        add(panel3,BorderLayout.PAGE_END);
    }

    private String[][] setDataToArray(List<Map<String, String>> productList) {
        Collections.sort(productList, Comparator.comparing(m -> m.get("Product ID")));
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




}

