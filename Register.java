/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author User
 */

public class Register extends JFrame {

    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label;
    private JLabel Fristname;
    private JLabel Lastname;
    private JLabel PasswordLabel;
    private JLabel Password_con_Label;
    private JLabel EmailLabel;
    public JLabel address1;
    public JTextField LastnameText;
    public JTextField FirstnameText;
    public JPasswordField Password;
    public JPasswordField Passwordcon;
    public JTextField address;
    public JTextField EmailTextField;
    public JLabel TypeOfUser;
    public JRadioButton User;
    public JRadioButton Emp;
    public JRadioButton ProductFamily;
    public JButton nextButton;
    private ButtonGroup group;
    public JButton register;
    public String TypeOfUser1;
    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 600;

    public Register() {

        setTitle("Register");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildPanel() {
        panel = new JPanel();
        panel.setLayout(null);

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setSize(900, 400);
        panel1.setBackground(new Color(0, 0, 0, 0));
        panel1.setBounds(30, 10, 800, 500);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 2));
        panel2.setBounds(350, 30, 300, 55);
        panel2.setBackground(new Color(0, 0, 0, 40));

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(6, 2));
        panel3.setBounds(140, 100, 550, 350);
        panel3.setBackground(new Color(0, 0, 0, 50));
        panel3.setBorder(BorderFactory.createTitledBorder("Add your information : *"));

        panel4 = new JPanel();
        panel4.setLayout(null);
        panel4.setSize(800, 700);
        panel4.setBackground(new Color(0, 0, 0, 0));
        panel4.setBounds(100, 0, 800, 700);

        label = new JLabel("Register");
        label.setBounds(350, 10, 300, 100);
        label.setFont(new Font("", Font.BOLD + Font.PLAIN, 30));
        label.setForeground(Color.black);
        panel4.add(label);

        Fristname = new JLabel("First name:");
        Fristname.setBounds(250, 100, 150, 50);
        Fristname.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        Fristname.setForeground(Color.BLACK);
        panel1.add(Fristname);

        FirstnameText = new JTextField();
        FirstnameText.setBounds(350, 112, 150, 30);
        panel1.add(FirstnameText);

        Lastname = new JLabel("Last name:");
        Lastname.setBounds(250, 150, 150, 50);
        Lastname.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        Lastname.setForeground(Color.BLACK);
        panel1.add(Lastname);

        LastnameText = new JTextField();
        LastnameText.setBounds(350, 155, 150, 30);
        panel1.add(LastnameText);

        address1 = new JLabel("Address:");
        address1.setBounds(250, 188, 150, 50);
        address1.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        address1.setForeground(Color.BLACK);
        panel1.add(address1);

        address = new JTextField();
        address.setBounds(350, 199, 150, 30);
        panel1.add(address);

        EmailLabel = new JLabel("Email:");
        EmailLabel.setBounds(250, 240, 150, 50);
        EmailLabel.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        EmailLabel.setForeground(Color.BLACK);
        panel1.add(EmailLabel);

        EmailTextField = new JTextField();
        EmailTextField.setBounds(350, 255, 150, 30);
        panel1.add(EmailTextField);

        PasswordLabel = new JLabel("Password:");
        PasswordLabel.setBounds(250, 290, 200, 50);
        PasswordLabel.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        PasswordLabel.setForeground(Color.BLACK);
        panel1.add(PasswordLabel);

        Password = new JPasswordField();
        Password.setBounds(350, 300, 150, 30);
        panel1.add(Password);

        Password_con_Label = new JLabel("Confirm Password:");
        Password_con_Label.setBounds(200, 330, 200, 50);
        Password_con_Label.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        Password_con_Label.setForeground(Color.BLACK);
        panel1.add(Password_con_Label);

        Passwordcon = new JPasswordField();
        Passwordcon.setBounds(350, 340, 150, 30);
        panel1.add(Passwordcon);

        TypeOfUser = new JLabel("Who You Are:");
        TypeOfUser.setBounds(150, 380, 150, 50);
        TypeOfUser.setFont(new Font("", Font.BOLD + Font.PLAIN, 16));
        TypeOfUser.setForeground(Color.BLACK);
        panel1.add(TypeOfUser);

        User = new JRadioButton("User");
        User.setBounds(280, 390, 80, 30);
        panel1.add(User);

        Emp = new JRadioButton("Delivery");
        Emp.setBounds(375, 390, 100, 30);
        panel1.add(Emp);

        ProductFamily = new JRadioButton("Productive Family");
        ProductFamily.setBounds(485, 390, 150, 30);
        panel1.add(ProductFamily);

        group = new ButtonGroup();
        group.add(User);
        group.add(Emp);
        group.add(ProductFamily);
        User.addActionListener(new RadioListener());
        Emp.addActionListener(new RadioListener());
        ProductFamily.addActionListener(new RadioListener());

        register = new JButton("Register");
        register.setBounds(250, 450, 150, 40);
        register.setBackground(Color.WHITE);
        register.addActionListener(new registerListener());
        panel1.add(register);

        nextButton = new JButton("Back");
        nextButton.setBounds(450, 450, 150, 40);
        nextButton.setBackground(Color.WHITE);
        nextButton.addActionListener(new NextButton());
        panel1.add(nextButton);

        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel2);
        panel.add(panel1);

        // Background image
        ImageIcon background_image = new ImageIcon("4.jpg");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel background = new JLabel("", background_image, JLabel.CENTER);
        background.setBounds(0, 0, 900, 600);
        panel.add(background);

        // Image
        ImageIcon image = new ImageIcon("logo homemade.jpg");
        Image imge = image.getImage();
        Image temp_imge = imge.getScaledInstance(150, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(temp_imge);
        JLabel Image = new JLabel("", image, JLabel.CENTER);
        Image.setBounds(50, 10, 300, 100);
        panel4.add(Image);
    }


    private class registerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String password = new String(Password.getPassword());
            String confirmPassword = new String(Passwordcon.getPassword());

            if (FirstnameText.getText().isEmpty() || LastnameText.getText().isEmpty()
                    || EmailTextField.getText().isEmpty() || address.getText().isEmpty()
                    || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the required fields.");
            } else if (password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Registered successfully.");

                try {
                    FileWriter file = new FileWriter("CustomerData.txt", true);
                    PrintWriter outputFile = new PrintWriter(file);
                    String name = FirstnameText.getText();
                    String name1 = LastnameText.getText();
                    String Password1 = Password.getText();
                    String Email = EmailTextField.getText();
                    outputFile.println(name);
                    outputFile.println(name1);
                    outputFile.println(Password1);
                    outputFile.println(Email);
                    outputFile.close();

                    FileWriter file1 = new FileWriter("Customer.txt", true);
                    PrintWriter outputFile1 = new PrintWriter(file1);
                    outputFile1.println(Password1);
                    outputFile1.println(Email);
                    outputFile1.close();

                    FileWriter file2 = new FileWriter("order.txt", true);
                    PrintWriter outputFile2 = new PrintWriter(file2);
                    outputFile2.println(name);
                    outputFile2.println(Password);
                    outputFile2.println(Email);
                    outputFile2.close();

                    FileWriter file3 = new FileWriter("CustomerEmail.txt", true);
                    PrintWriter outputFile3 = new PrintWriter(file3);
                    outputFile3.println(Email);
                    outputFile3.println(Password1);
                    outputFile3.close();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password does not match.");
                Password.setText("");
                Passwordcon.setText("");
            }
        }
    }

    private class RadioListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (User.isSelected()) {
                TypeOfUser1 = "User";
            } else if (Emp.isSelected()) {
                TypeOfUser1 = "Delivery";
            } else if (ProductFamily.isSelected()) {
                TypeOfUser1 = "Productive Family";
            }

            try {
                FileWriter file3 = new FileWriter("CustomerData.txt", true);
                PrintWriter outputFile3 = new PrintWriter(file3);

                outputFile3.println(TypeOfUser1);
                outputFile3.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private class NextButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Login login = new Login();
            login.show();
            dispose();
        }
    }

    public static void main(String[] args) {
        Register r = new Register();
    }
}