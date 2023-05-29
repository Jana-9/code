package project1;

import Project1.Login;
import Project1.Register;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class MainScreen extends JFrame {

    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel welcome;
    private JButton loginButton;
    Register Register1;
    Register register;
    public static ArrayList<Person> allPersons;
    public static ArrayList<Food> allFoods;
    public static ArrayList<Resin> allResin;
    public static ArrayList<Dessert> allDessert;
    public static ArrayList<Order> allOrders;
    public static final String personFile = "persons.txt";
    private static final String itemsFile = "items.txt";
    private static final String orderFile = "orders.txt";
    public static Person currentPerson;

    public MainScreen() {

        setTitle("Main Screen ");
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
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(900, 600);
        panel.setBackground(new Color(0, 0, 0, 10));

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        panel2.setBounds(250, 400, 190, 155);
        panel2.setBackground(new Color(0, 0, 0, 0));

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(3, 1));
        panel3.setBounds(450, 400, 190, 155);
        panel3.setBackground(new Color(0, 0, 0, 0));

        welcome = new JLabel("--- Welcome To Home Made --- ");
        welcome.setBounds(230, 250, 600, 200);
        welcome.setFont(new Font("-- LOGIN -- ", Font.BOLD + Font.PLAIN, 30));
        welcome.setForeground(Color.darkGray);
        welcome.setBackground(new Color(0, 0, 0, 30));

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
        Image temp_imge = imge.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        image = new ImageIcon(temp_imge);
        JLabel Image = new JLabel("", image, JLabel.CENTER);
        Image.setBounds(150, 18, 610, 310);
        Image.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 15), 5));

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.DARK_GRAY);
        loginButton.setFont(new Font("Login", Font.BOLD, 17));

        loginButton.addActionListener(new loginListener());

        panel.add(welcome);
        panel2.add(loginButton);

        add(panel);
        add(panel2);
        add(panel3);
        add(Image);
        add(background);
        setVisible(true);

    }

    private void WindowListener(java.awt.event.WindowEvent evt) {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            writeDataToFiles();
            System.exit(0);
        }
    }

    private class loginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Login login = new Login();
            dispose();
        }
    }

    public static void writeDataToFiles() {
        try {
            FileWriter file = new FileWriter(personFile);
            for (Person p : allPersons) {
                if (p instanceof User) {
                    file.write("2," + p+"\n");
                } else {
                    file.write("1," + p + "\n");
                }
            }
            file.close();

            file = new FileWriter(itemsFile);
            for (Food f : allFoods) {
                file.write("1," + f + "\n");
            }
            for (Resin r : allResin) {
                file.write("2," + r + "\n");
            }
            for (Dessert d : allDessert) {
                file.write("3," + d + "\n");
            }
            file.close();

            file = new FileWriter(orderFile);
            for (Order o : allOrders) {
                file.write(o + "\n");
            }
            file.close();
            JOptionPane.showMessageDialog(null, "Data has been written successfully to file","Saving data to file",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro in Writting data to Files" + e, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static void readDataFromFile() {
        try {
            Scanner input = new Scanner(new File(personFile));
            String line;
            String info[];
            while (input.hasNext()) {
                line = input.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                info = line.split(",");
                // admin
                if (info[0].equals("1")) {
                    allPersons.add(new Admin(info[2], info[3], info[4], info[5], info[6]));
                    ((Admin) allPersons.get(allPersons.size() - 1)).setId(Integer.parseInt(info[1]));
                } // user
                else {
                    allPersons.add(new User(info[2], info[3], info[4], info[5], info[6]));
                    ((User) allPersons.get(allPersons.size() - 1)).setId(Integer.parseInt(info[1]));
                }
            }

            input = new Scanner(new File(itemsFile));
            while (input.hasNext()) {
                line = input.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                info = line.split(",");
                // food
                if (info[0].equals("1")) {
                    allFoods.add(new Food(info[2], getOwnerById(Integer.parseInt(info[3])), Double.parseDouble(info[4]), Integer.parseInt(info[5]), info[6]));
                    allFoods.get(allFoods.size() - 1).setId(Integer.parseInt(info[1]));
                } // Resin
                else if (info[0].equals("2")) {
                    allResin.add(new Resin(info[2], getOwnerById(Integer.parseInt(info[3])), Double.parseDouble(info[4]), Integer.parseInt(info[5]), info[6]));
                    allResin.get(allResin.size() - 1).setId(Integer.parseInt(info[1]));
                } // Desserts
                else {
                    allDessert.add(new Dessert(info[2], getOwnerById(Integer.parseInt(info[3])), Double.parseDouble(info[4]), Integer.parseInt(info[5]), info[6]));
                    allDessert.get(allDessert.size() - 1).setId(Integer.parseInt(info[1]));
                }
            }

            input = new Scanner(new File(orderFile));
            while (input.hasNext()) {
                line = input.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                info = line.split(",");
                Person user = getUserById(Integer.parseInt(info[0]));
                if(user == null)
                    continue;
                allOrders.add(new Order(user));
                int numItems = Integer.parseInt(info[1]);
                for(int i = 0 ; i < numItems; i++){
                    allOrders.get(allOrders.size()-1).addItem(getItemById(Integer.parseInt(info[2+(2*i)])), Integer.parseInt(info[3+(2*i)]));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro in Reading data from File" + ex, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private static Admin getOwnerById(int id) {
        for (Person p : allPersons) {
            if (p instanceof Admin && ((Admin) p).getId() == id) {
                return (Admin) p;
            }
        }
        return null;
    }

    private static User getUserById(int id) {
        for (Person p : allPersons) {
            if (p instanceof User && ((User) p).getId() == id) {
                return (User) p;
            }
        }
        return null;
    }

    private static Item getItemById(int id) {
        for (Item i : allFoods) {
            if (i.getId() == id) {
                return i;
            }
        }
        for (Item i : allResin) {
            if (i.getId() == id) {
                return i;
            }
        }
        for (Item i : allDessert) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        allPersons = new ArrayList<>();
        allFoods = new ArrayList<>();
        allDessert = new ArrayList<>();
        allResin = new ArrayList<>();
        allOrders = new ArrayList<>();
        readDataFromFile();
        new MainScreen();
//        new AdminMenu();
    }

}
