import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BuyerMenu extends JFrame{
    private JPanel BuyerPanel;
    private JButton BACKButton;
    private JButton SEARCHButton;
    private JTable AvailableItems;
    private JTextField textField1;
    private JButton PURCHASEButton;

    public BuyerMenu(){
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Home Page");
        setContentPane(BuyerPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(500,500));
        setResizable(false);
        setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home_Page.main();
            }
        });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                try   {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item_details");
                    ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM item_details");
                    while (resultSet.next()) {
                        DefaultTableModel tableModel = (DefaultTableModel) AvailableItems.getModel();
                        String ItemName = resultSet.getString("Item Name");
                        String ItemDescription = resultSet.getString("Item Description");
                        String ItemPrice = resultSet.getString("Item Price");
                        String ItemStock = resultSet.getString("Item Stock");

                        String[] tblData = {ItemName, ItemDescription, ItemPrice, ItemStock};
                        tableModel.addRow(tblData);

                        AvailableItems.setModel(DbUtils.resultSetToTableModel(resultSet));
                    }
                }
                catch(Exception e){
                    e.printStackTrace();

                }
            }
        });
        PURCHASEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmPurchase.main();
            }
        });
    }

    public static void main() {
        new BuyerMenu();
    }

}
