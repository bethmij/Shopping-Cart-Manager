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

        JButton btn1 = new JButton("Shopping Cart");
        btn1.setFont(new Font("",0,16));
        btn1.setHorizontalAlignment(JButton.CENTER);
        panel1.add(btn1);
        panel1.add(Box.createHorizontalStrut(10));


        int rows = (int) Math.ceil((double) productList.size() / 5);
        JPanel panel2 = new JPanel(new GridLayout(Math.min(rows, 2), 5, 10, 10));
//        panel2.setBackground(Color.BLUE);
        panel1.add(Box.createVerticalStrut(100));

        String[] columnNames = {"Product ID", "Name", "Category", "Price ($)", "Info"};
        String[][] data = setDataToArray(productList);

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(35);
//        table.setBackground(Color.RED);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);


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

        JLabel lbl4 = new JLabel("Product ID :");
        lbl4.setFont(new Font("Arial",Font.PLAIN,18));
        lbl4.setHorizontalAlignment(JLabel.CENTER);
        lbl4.setOpaque(true);
        lbl4.setPreferredSize(new Dimension(10, lbl4.getPreferredSize().height));
        panel3.add(lbl4);

        JLabel lbl5 = new JLabel("Category :");
        lbl5.setFont(new Font("Arial",Font.PLAIN,18));
        lbl4.setHorizontalAlignment(JLabel.CENTER);
        panel3.add(lbl5);

        JLabel lbl6 = new JLabel("Name : ");
        lbl6.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl6);

        JLabel lbl7 = new JLabel("Size : ");
        lbl7.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl7);

        JLabel lbl8 = new JLabel("Colour : ");
        lbl8.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl8);

        JLabel lbl9 = new JLabel("Items Available : ");
        lbl9.setFont(new Font("Arial",Font.PLAIN,18));
        panel3.add(lbl9);

        JPanel panel4 = new JPanel();
        JButton btn2 = new JButton("Add to Shopping Cart");
        btn2.setFont(new Font("",0,16));
        btn2.setHorizontalAlignment(JButton.CENTER);
        panel4.add(btn2);

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

