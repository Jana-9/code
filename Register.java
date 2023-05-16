/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project1;

import Project1.Login;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Register extends JFrame {

    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel Fristname;
    private JLabel Lastname;
    private JLabel PasswordLabel;
    private JLabel Password_con_Label;
    private JLabel EmailLabel;
    public JLabel address1;
    public JTextField LastnameText;
    public JTextField FirstnameText;
    public JTextField Password;
    public JTextField Passwordcon;
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
    final int WINDOW_WIDTH = 280;
    final int WINDOW_HEIGHT = 300;

    public Register() {

        setTitle("Register ");

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
        panel3 = new JPanel(new GridLayout(6, 2));

        Fristname = new JLabel("Frist name:");
        Lastname = new JLabel("Last name:");
        address1 = new JLabel("Address:");
        PasswordLabel = new JLabel("Password:");
        Password_con_Label = new JLabel("Confirm Password:");
        EmailLabel = new JLabel("Email:");
        TypeOfUser = new JLabel("Who You Are:");
        User = new JRadioButton("User");
        Emp = new JRadioButton("Delivery");
        ProductFamily = new JRadioButton("Productive Family");
        panel1 = new JPanel(new GridLayout(3, 1));
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));

        group = new ButtonGroup();
        group.add(User);
        group.add(Emp);
        group.add(ProductFamily);

        Fristname.setFont(new Font("serif", Font.BOLD, 15));
        Lastname.setFont(new Font("serif", Font.BOLD, 15));
        PasswordLabel.setFont(new Font("serif", Font.BOLD, 15));

        Password_con_Label.setFont(new Font("serif", Font.BOLD, 15));
        address1.setFont(new Font("serif", Font.BOLD, 15));
        EmailLabel.setFont(new Font("serif", Font.BOLD, 15));
        TypeOfUser.setFont(new Font("serif", Font.BOLD, 15));
        LastnameText = new JTextField(10);
        FirstnameText = new JTextField(10);
        Password = new JTextField(10);
        Passwordcon = new JTextField(10);
        EmailTextField = new JTextField(10);
        address = new JTextField(10);
        ImageIcon Image = new ImageIcon("welcome.png");
        register = new JButton("Register", Image);
        nextButton = new JButton("Next");
        register.setBackground(Color.lightGray);
        nextButton.setBackground(Color.lightGray);
        register.addActionListener(new registerListener());
        nextButton.addActionListener(new NextButton());
        User.addActionListener(new RadioListener());
        Emp.addActionListener(new RadioListener());
        nextButton.addActionListener(new RadioListener());
        panel2.add(TypeOfUser);
        panel1.add(User);
        panel1.add(Emp);
        panel1.add(ProductFamily);

        panel3.add(Fristname);
        panel3.add(FirstnameText);
        panel3.add(Lastname);
        panel3.add(LastnameText);

        panel3.add(EmailLabel);
        panel3.add(EmailTextField);
        panel3.add(address1);
        panel3.add(address);
        panel3.add(PasswordLabel);
        panel3.add(Password);
        panel3.add(Password_con_Label);
        panel3.add(Passwordcon);
        panel.add(panel3);
        panel.add(panel2);
        panel.add(panel1);
        panel.add(register);
        panel.add(nextButton);

    }

    private class registerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int pass = Integer.valueOf(Password.getText());
            int pass1 = Integer.valueOf(Passwordcon.getText());

            if (FirstnameText.getText().equalsIgnoreCase("") && LastnameText.getText().equalsIgnoreCase("")
                    && EmailTextField.getText().equalsIgnoreCase("") && address.getText().equalsIgnoreCase("")
                    && Password.getText().equalsIgnoreCase("") && Passwordcon.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please enter First Name.");
            } else if (LastnameText.getText().equalsIgnoreCase("") && EmailTextField.getText().equalsIgnoreCase("")
                    && address.getText().equalsIgnoreCase("") && Password.getText().equalsIgnoreCase("")
                    && Passwordcon.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please enter Last Name.");
            } else if (EmailTextField.getText().equalsIgnoreCase("")
                    && address.getText().equalsIgnoreCase("") && Password.getText().equalsIgnoreCase("")
                    && Passwordcon.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please enter Email Id.");
            } else if (address.getText().equalsIgnoreCase("") && Password.getText().equalsIgnoreCase("")
                    && Passwordcon.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please enter password.");
            } else if (address.getText().equalsIgnoreCase("") && Passwordcon.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please confirm password.");
                Password.setText("**********");

            } else if (address.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid address.");
                Password.setText("**********");
                Passwordcon.setText("**********");
            } else if (address.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid address.");
            } else if (!Password.equals(Passwordcon)) {
                JOptionPane.showMessageDialog(null, "Password does not match.");
                Password.setText("**********");
                Passwordcon.setText("**********");
            } else {
                JOptionPane.showMessageDialog(null, "Registered successfully.");
            }

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
                outputFile1.println(Password);
                outputFile1.println(name);
                outputFile1.close();

                FileWriter file2 = new FileWriter("order.txt", true);
                PrintWriter outputFile2 = new PrintWriter(file2);
                outputFile2.println(name);
                outputFile2.println(Password);
                outputFile2.println(Email);
                outputFile2.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

   

    private class RadioListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (User.getActionCommand() == "User") {
                TypeOfUser1 = "User";
            }
            if (Emp.getActionCommand() == "Delivery") {
                TypeOfUser1 = "Delivery";
            }
            if (ProductFamily.getActionCommand() == "ProductFamily") {
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
