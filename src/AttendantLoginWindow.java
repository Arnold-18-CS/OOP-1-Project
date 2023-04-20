import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class AttendantLoginWindow extends JFrame {
    private JPanel LoginPanel;
    private JButton LOGINButton;
    private JTextField tfUserID;
    private JPasswordField pfPassword;
    private JButton CLEARButton;
    private JButton BACKButton;
    private JLabel lblNotRegistered;

    public User user;
    String ConfirmationPassword;
    String Password = String.valueOf(pfPassword.getPassword());

    public AttendantLoginWindow() {
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Login Page");
        setContentPane(LoginPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(450, 450));
        setVisible(true);
        pack();
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home_Page.main();
            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfUserID.setText("");
                pfPassword.setText("");
            }
        });
        lblNotRegistered.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                AttendantRegistrationWindow.main();
            }
        });
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String User_ID = String.valueOf(tfUserID.getText());
                    String Password = String.valueOf(pfPassword.getPassword());

                    if (User_ID.isEmpty() || Password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields to continue.", "Incomplete entries.", JOptionPane.ERROR_MESSAGE);
                        dispose();
                        AttendantLoginWindow.main();
                    }
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Welcome " + User_ID, "Welcome to your account"
                                , JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        ShopAttendantMenu.main();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Login failed",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        dispose();
                        AttendantLoginWindow.main();
                    }
            checkUser(Password);
            }
        });
    }
    private User checkUser(String password){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");
            System.out.println("CONNECTION SUCCESSFUL.");

            // TO PREVENT ENTRY WITH WRONG PASSWORD
            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT Password FROM `attendant_details`");
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            System.out.println("QUERY CREATED");

            while (resultSet2.next()) {
                ConfirmationPassword = resultSet2.getString(1);
                System.out.println(ConfirmationPassword);

                if (!Objects.equals(password, ConfirmationPassword)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Password entered.\n" +
                            " Please put in the correct password.", "INCORRECT PASSWORD!", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    Home_Page.main();
                } else {
                    ShopAttendantMenu.main();
                }
            }
            preparedStatement2.close();
            connection.close();
        } catch (Exception ex) {

            dispose();
            AttendantLoginWindow.main();
            throw new RuntimeException(ex);
        }

        return user;
    }
    public static void main() {
        new AttendantLoginWindow();
    }
}