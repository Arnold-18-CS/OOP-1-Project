import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UpdateItemsMenu extends JFrame {

    private JPanel UpdatePanel;
    private JButton BACKButton;
    private JButton UPDATEButton;
    private JButton CLEARButton;
    private JTextField tfItemDescription;
    private JTextField tfItemStock;

    public Item item;
    String itemDesc = String.valueOf(tfItemDescription.getText());
    String itemStock = String.valueOf(tfItemStock.getText());

    public UpdateItemsMenu(){
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Update Page");
        setContentPane(UpdatePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(450,450));
        setResizable(false);
        setVisible(true);
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShopAttendantMenu.main();
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItem();
            }
        });
    }

    private void updateItem() {
        String itemDescription = String.valueOf(tfItemDescription.getText());
        String itemStock = String.valueOf(tfItemStock.getText());


        if (itemDescription.isEmpty() || itemStock.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill all the fields to continue",
                    "Incomplete entries.",JOptionPane.ERROR_MESSAGE);
        }
        if (item == null) {
            JOptionPane.showMessageDialog(null,"ITEM NAME: "+ itemDescription + " stock has been updated.",
                    "Item Successfully Updated.",JOptionPane.INFORMATION_MESSAGE);
            dispose();
            ShopAttendantMenu.main();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update the item",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");
            System.out.println("Database Connection Success");

            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `item_details` SET `Item Description`= ? ,`Item Stock`= ?");
            preparedStatement.setString(1, itemDescription);
            preparedStatement.setString(2, itemStock);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                item = new Item();
                item.ItemDescription = itemDescription;
                item.ItemStock = itemStock;
            }
            System.out.println("Update Completed.");
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main() {
        new UpdateItemsMenu();
    }
}
