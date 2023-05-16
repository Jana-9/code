/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class MainScreen extends JFrame {

    private JPanel panel;
    public JLabel welcome;

    private JButton loginButton;
    private JButton RegisterButton;

    final int WINDOW_WIDTH = 250;
    final int WINDOW_HEIGHT = 160;

    public MainScreen() {

        setTitle("Main Screen ");

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
        welcome = new JLabel("Welcome To Home Made");
        

        

        welcome.setFont(new Font("serif", Font.BOLD, 15));

        loginButton = new JButton("Login");
        RegisterButton = new JButton("Regiter");

        loginButton.setBackground(Color.lightGray);
        RegisterButton.setBackground(Color.lightGray);

        loginButton.addActionListener(new loginListener());
      
        RegisterButton.addActionListener(new registerButton());
        panel.add(welcome);

        panel.add(loginButton);

        panel.add(RegisterButton);

    }
private class registerButton implements ActionListener {
    
        
        public void actionPerformed(ActionEvent e) {
           Register  Register1 = new Register();
                Register1.show();
                dispose();
        }
    }
private class loginListener implements ActionListener {
    
        
        public void actionPerformed(ActionEvent e) {
             Login login = new Login();
                login.show();
                dispose();
        }
    }
  public static void main(String[] args) {
        MainScreen main = new MainScreen();
    }
}



