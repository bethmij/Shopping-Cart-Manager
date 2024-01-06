import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ShoppingCartController extends JFrame {
    private JPanel panel1, panel2;
    private JLabel lbl1;
    private JComboBox<String> comboBox;
    private JButton btn1;

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

        String[] columnNames = {"Product ID", "Name", "Category", "Age", "Gender"};

        // Create a DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);




        add(panel1,BorderLayout.PAGE_START);
    }

}

