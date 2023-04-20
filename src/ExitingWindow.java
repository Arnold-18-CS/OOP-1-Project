import javax.swing.*;
import java.awt.*;

public class ExitingWindow extends JFrame{
    private JPanel ExitPanel;

    public ExitingWindow(){
        ImageIcon logo = new ImageIcon("C:/Users/user/Pictures/OFFICIAL DUKA LOGO.png");

        setTitle("DUKA - Home Page");
        setContentPane(ExitPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setIconImage(logo.getImage());
        setMinimumSize(new Dimension(500,500));
        setResizable(false);
        setVisible(true);
    }

    public static void main() {
        new ExitingWindow();
    }
}
