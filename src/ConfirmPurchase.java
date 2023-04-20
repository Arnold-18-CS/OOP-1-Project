import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmPurchase extends JFrame {
    private JPanel PurchasePanel;
    private JTextField tfItemName;
    private JSpinner quantitySpin;
    private JButton PURCHASEButton;

    String ItemName = String.valueOf(tfItemName.getText());
    String ItemQuantity = String.valueOf(quantitySpin.getValue());

    public ConfirmPurchase() {
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Home Page");
        setContentPane(PurchasePanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(500,500));
        setResizable(false);
        setVisible(true);

        PURCHASEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ItemName = String.valueOf(tfItemName.getText());
                String ItemQuantity = String.valueOf(quantitySpin.getValue());

                JOptionPane.showMessageDialog(null, "ORDER HAS BEEN PLACED SUCCESSFULLY.\n Item Name: " +ItemName+ "\n" +
                        "Item Quantity: "+ItemQuantity+".", "SUCCESSFUL ORDER PLACEMENT.", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }

    public static void main() {
        new ConfirmPurchase();
    }
}
