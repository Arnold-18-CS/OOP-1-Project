import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttendantRegistrationWindow extends JFrame {
    private JPanel RegistrationPanel;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirm;
    private JButton REGISTERButton;
    private JButton CLEARButton;
    private JButton BACKButton;
    private JButton btnBack;

    String name = String.valueOf(tfUsername.getText());
    String password = String.valueOf(pfPassword.getPassword());
    String confirm = String.valueOf(pfConfirm.getPassword());
    String userID = getUserID();
    public String getUserID(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery("SELECT `User ID` FROM `attendant_details`" +
                    " WHERE Username LIKE '%" + name + "%' AND Password LIKE '%" + password + "%'");
            while (resultSet1.next()) {
                userID = resultSet1.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userID;
    }
    public AttendantRegistrationWindow() {
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Registration Page");
        setContentPane(RegistrationPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(600, 400));
        pack();
        setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AttendantLoginWindow.main();
            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfUsername.setText("");
                pfPassword.setText("");
                pfConfirm.setText("");
            }
        });
        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerUser();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public User user;
    private User addUserToDatabase(String name, String password, String confirm) throws SQLException {
        //user = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_149900", "root", "");
            System.out.println("Database Connection Success");

            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `attendant_details`(`Username`, `Password`, `Confirm Password`) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, confirm);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.password = password;
                user.confirm = confirm;
            }
            System.out.println("Insertion Completed.");
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;
    }
    private void registerUser() throws SQLException {
        String name = String.valueOf(tfUsername.getText());
        String password = String.valueOf(pfPassword.getPassword());
        String confirm = String.valueOf(pfConfirm.getPassword());
        String userID = getUserID();

        if (name.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please fill all fields to continue",
                    "Incomplete entries.",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Java program to validate
// the password using ReGex

        class GFG {

            // Function to validate the password.
            public static boolean
            isValidPassword(String password) {

                // Regex to check valid password.
                String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

                // Compile the ReGex
                Pattern p = Pattern.compile(regex);

                // If the password is empty
                // return false
                if (password == null) {
                    return false;
                }
                // Pattern class contains matcher() method
                // to find matching between given password
                // and regular expression.
                Matcher m = p.matcher(password);

                // Return if the password
                // matched the ReGex
                return m.matches();
            }
        }
        if (user == null) {
            JOptionPane.showMessageDialog(this, "USERNAME: " + name + " has been successfully registered.\n"+
                            "\nPlease remember your account information in detail.",
                    "Successful Registration", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            AttendantLoginWindow.main();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        addUserToDatabase(name, password, confirm);

        JOptionPane.showMessageDialog(null, "USER ID: " + getUserID(), "NEW USER ID.",JOptionPane.INFORMATION_MESSAGE);

    }

    public static void main() {
        new AttendantRegistrationWindow();
    }
}

