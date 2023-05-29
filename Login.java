package Project1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import project1.AdminMenu;
import project1.MainScreen;
import project1.Menu;
import project1.Person;
import project1.User;

/**
 *
 * @author User
 */
public class Login extends JFrame {

    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label;
    private JLabel passLabel;
    private JLabel EmailLabel;
    public JTextField EmailField;
    public JTextField passField;
    private JButton loginButton;
    private JButton RegisterButton;

    Register Register1;
    Register register;

    public Login() {
        setTitle(" Login Page");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    MainScreen.writeDataToFiles();
                    System.exit(0);
                }
            }
        });
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(900, 600);
        panel.setBackground(new Color(0, 0, 0, 10));

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 2));
        panel1.setBounds(255, 290, 400, 80);
        panel1.setBackground(new Color(0, 0, 0, 80));

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        panel2.setBounds(350, 390, 180, 150);
        panel2.setBackground(new Color(0, 0, 0, 0));

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(3, 1));
        panel3.setBounds(350, 460, 180, 150);
        panel3.setBackground(new Color(0, 0, 0, 0));

        label = new JLabel("--- LOGIN --- ");
        label.setBounds(355, 155, 400, 200);
        label.setFont(new Font("-- LOGIN -- ", Font.BOLD + Font.PLAIN, 30));
        label.setForeground(Color.darkGray);
        label.setBackground(new Color(0, 0, 0, 30));

//background
        ImageIcon background_image = new ImageIcon("4.jpg");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel background = new JLabel("", background_image, JLabel.CENTER);
        background.setBounds(0, 0, 900, 600);

        // Image
        ImageIcon image = new ImageIcon("logo homemade.jpg");
        Image imge = image.getImage();
        Image temp_imge = imge.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        image = new ImageIcon(temp_imge);
        JLabel Image = new JLabel("", image, JLabel.CENTER);
        Image.setBounds(195, 18, 510, 210);
        Image.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 15), 5));

        passLabel = new JLabel("  Password:");
        passLabel.setFont(new Font("Password", Font.BOLD, 20));
        passLabel.setForeground(Color.white);

        EmailLabel = new JLabel("  Email:");
        EmailLabel.setFont(new Font("Email", Font.BOLD, 20));
        EmailLabel.setForeground(Color.white);

        passField = new JPasswordField();
        EmailField = new JTextField();

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.DARK_GRAY);
        loginButton.setFont(new Font("Login", Font.BOLD, 17));

        RegisterButton = new JButton(" Register Now!");
        RegisterButton.setBackground(Color.WHITE);
        RegisterButton.setForeground(Color.DARK_GRAY);
        RegisterButton.setFont(new Font(" Register Now!", Font.BOLD, 17));

        loginButton.addActionListener(new loginListener());

        RegisterButton.addActionListener(new registerButton());

        panel.add(label);

        panel1.add(EmailLabel);
        panel1.add(EmailField);

        panel1.add(passLabel);
        panel1.add(passField);

        panel2.add(loginButton);
        panel3.add(RegisterButton);

        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        add(Image);
        add(background);
        setVisible(true);

    }

    private class registerButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new Register();
            dispose();
        }
    }

    private class loginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String email = EmailField.getText().trim();
            String pass = passField.getText().trim();
            if (email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the required fields.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
            MainScreen.currentPerson = null;
            for (Person p : MainScreen.allPersons) {
                if (p.getEmail().equals(email)
                        && p.getPassword().equals(pass)) {
                    MainScreen.currentPerson = p;
                    break;
                }
            }
            if (MainScreen.currentPerson == null) {
                JOptionPane.showMessageDialog(null, "Invalid Email or password ", "Login Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Login successfully. You are login in as " + MainScreen.currentPerson.getFullName(), "Login Successfully", JOptionPane.INFORMATION_MESSAGE);
                if (MainScreen.currentPerson instanceof User) {
                    new Menu();
                    dispose();
                }
                else{
                    new AdminMenu();
                    dispose();
                }
            }
        }
    }

}
