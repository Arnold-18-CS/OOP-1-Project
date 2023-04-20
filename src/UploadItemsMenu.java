import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UploadItemsMenu  extends JFrame {

    private JPanel UploadPanel;
    private JButton UPLOADButton;
    private JButton CLEARButton;
    private JTextField tfItemName;
    private JTextField tfItemPrice;
    private JTextArea taItemDescription;
    private JTextField tfItemStock;
    private JButton BACKButton;

    String ItemName = String.valueOf(tfItemName.getText());
    String ItemPrice = String.valueOf(tfItemPrice.getText());
    String ItemDescription = String.valueOf(taItemDescription.getText());
    String ItemStock = String.valueOf(tfItemStock.getText());

    public UploadItemsMenu(){
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Upload Page");
        setContentPane(UploadPanel);
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
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfItemName.setText("");
                tfItemPrice.setText("");
                taItemDescription.setText("");
                tfItemStock.setText("");
            }
        });
        UPLOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadItem();
            }
        });
    }

//    private Item addItemToDatabase(String itemName,String itemDescription, String itemPrice,  String itemStock) {
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");
//            System.out.println("Database Connection Success");
//
//            Statement statement = connection.createStatement();
//
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `item_details`(`Item Name`, `Item Description`, `Item Price`, `Item Stock`) VALUES (?,?,?,?)");
//            preparedStatement.setString(1, ItemName);
//            preparedStatement.setString(2, ItemDescription);
//            preparedStatement.setString(3, ItemPrice);
//            preparedStatement.setString(4, ItemStock);
//
//            int addedRows = preparedStatement.executeUpdate();
//            if (addedRows > 0) {
//                item = new Item();
//                item.ItemName = itemName;
//                item.ItemDescription = itemDescription;
//                item.ItemPrice = itemPrice;
//                item.ItemStock = itemStock;
//            }
//            System.out.println("Insertion Completed.");
//            preparedStatement.close();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return item;
//    }
    private void uploadItem() {
        String ItemName = String.valueOf(tfItemName.getText());
        String ItemPrice = String.valueOf(tfItemPrice.getText());
        String ItemDescription = String.valueOf(taItemDescription.getText());
        String ItemStock = String.valueOf(tfItemStock.getText());

        if (ItemName.isEmpty() || ItemPrice.isEmpty() || ItemDescription.isEmpty() || ItemStock.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill all the fields to continue",
                    "Incomplete entries.",JOptionPane.ERROR_MESSAGE);
        }
        if (item == null) {
            JOptionPane.showMessageDialog(null,ItemName + " has been uploaded to Duka.",
                    "Item Successfully Uploaded.",JOptionPane.INFORMATION_MESSAGE);
            dispose();
            ShopAttendantMenu.main();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to upload the item",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");
            System.out.println("Database Connection Success");

            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `item_details`(`Item Name`, `Item Description`, `Item Price`, `Item Stock`) VALUES (?,?,?,?)");
            preparedStatement.setString(1, ItemName);
            preparedStatement.setString(2, ItemDescription);
            preparedStatement.setString(3, ItemPrice);
            preparedStatement.setString(4, ItemStock);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                item = new Item();
                item.ItemDescription = ItemName;
                item.ItemDescription = ItemDescription;
                item.ItemPrice = ItemPrice;
                item.ItemStock = ItemStock;
            }
            System.out.println("Insertion Completed.");
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Item item;

    public static void main() {
        new UploadItemsMenu();
    }
}

