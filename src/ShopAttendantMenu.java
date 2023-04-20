import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShopAttendantMenu extends JFrame{
    private JPanel AttendantPanel;
    private JButton BACKButton;
    private JLabel lblUpload;
    private JLabel lblUpdate;
    private JLabel lblDelete;

    public ShopAttendantMenu(){
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Shop Attendant Menu.");
        setContentPane(AttendantPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(450,450));
        setVisible(true);
        pack();



        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home_Page.main();
            }
        });
        lblUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                UploadItemsMenu.main();
            }
        });

        lblUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                UpdateItemsMenu.main();
            }
        });

    }

    public static void main() {
        new ShopAttendantMenu();
    }
}
