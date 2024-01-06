import javax.swing.*;
import java.awt.*;

public class ShoppingCartController extends JFrame {
    private JPanel panel1;
    private JLabel lbl1;

    public  ShoppingCartController(){
        setGUI();
    }

    public void setGUI(){
        setSize(900, 700);
        setTitle("Westminister Shopping Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        lbl1=new JLabel("Select Product Category");
        lbl1.setFont(new Font("Serif",Font.BOLD,18));
        lbl1.setHorizontalAlignment(JLabel.LEFT);
        panel1.add(lbl1,BorderLayout.PAGE_START);
    }

}

