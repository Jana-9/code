package Project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author User
 */
public class MainScreen extends JFrame {
    private JPanel panel; 
    private JPanel panel2;
    private JPanel panel3;
    private JLabel welcome ;
    private JButton loginButton;
    private JButton RegisterButton;
    Register Register1;
    Register register;

   public MainScreen() {

    setTitle("Main Screen ");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(900, 600);
    setLocationRelativeTo(null);
    setLayout(null);
    
    panel=new JPanel(); 
    panel.setLayout(null);
    panel.setSize(900,600);
    panel.setBackground(new Color(0,0,0,10));

    panel2 =new JPanel();
    panel2.setLayout(new GridLayout(3,1));
    panel2.setBounds(250,400,190,155);
    panel2.setBackground (new Color(0,0,0,0));
    
    panel3 =new JPanel();
    panel3.setLayout(new GridLayout(3,1));
    panel3.setBounds(450,400,190,155);
    panel3.setBackground (new Color(0,0,0,0));
        
    welcome = new JLabel("--- Welcome To Home Made --- ");
    welcome.setBounds(230,250,600,200);
    welcome.setFont(new Font("-- LOGIN -- ",Font.BOLD+Font.PLAIN,30));
    welcome.setForeground(Color.darkGray);
    welcome.setBackground(new Color(0,0,0,30));   

       
//background

       ImageIcon background_image = new ImageIcon("4.jpg");
       Image img = background_image.getImage () ;
       Image temp_img = img.getScaledInstance(1000,800,Image.SCALE_SMOOTH);
       background_image = new ImageIcon(temp_img);
       JLabel background = new JLabel("",background_image,JLabel.CENTER);
       background.setBounds(0, 0, 900, 600);
       
     // Image
       ImageIcon image = new ImageIcon("logo homemade.jpg");
       Image imge = image.getImage () ;
       Image temp_imge = imge.getScaledInstance(600,300,Image.SCALE_SMOOTH);
       image = new ImageIcon(temp_imge);
       JLabel Image = new JLabel("",image,JLabel.CENTER);
       Image.setBounds(150,18, 610, 310);
       Image.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,15), 5));
            
             loginButton = new JButton ("Login");
            loginButton.setBackground(Color.WHITE);
            loginButton.setForeground(Color.DARK_GRAY);
          loginButton.setFont(new Font("Login",Font.BOLD,17));
         
          loginButton.addActionListener(new loginListener());
             
            RegisterButton = new JButton("Register"); 
            RegisterButton.setBackground(Color.WHITE);
            RegisterButton.setForeground(Color.DARK_GRAY);
            RegisterButton.setFont(new Font("Register",Font.BOLD,17));
            
            RegisterButton.addActionListener(new registerButton());

            
           panel.add(welcome);
           panel2.add(loginButton);
           panel3.add(RegisterButton);

            
            add(panel);
            add(panel2);
            add(panel3);
            add(Image);
            add(background);  
            setVisible(true);
            
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