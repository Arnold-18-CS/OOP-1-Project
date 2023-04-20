import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home_Page extends JFrame {
    private JPanel HomePanel;
    private JLabel Icon;
    private JButton btnAttendant;
    private JButton btnBuyer;
    private JButton EXITButton;

    public Home_Page(){
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Home Page");
        setContentPane(HomePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(500,500));
        setResizable(false);
        setVisible(true);

        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Confirmation",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    dispose();
                    ExitingWindow.main();
                }
            }
        });
        btnAttendant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AttendantLoginWindow.main();
            }
        });
        btnBuyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BuyerMenu.main();
            }
        });
    }

    public static void main() {
        new Home_Page();
    }
}
