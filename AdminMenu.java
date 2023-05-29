package project1;

import Project1.Login;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminMenu extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel addProductPanel;
    private JPanel editProductPanel;
    private JPanel viewOrdersPanel;
    private Font font = new Font("Arial", Font.PLAIN, 16);

    public AdminMenu() {
        setTitle("Admin Menu ");
        setResizable(false);
        setBounds(100, 100, 600, 600);
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
        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Create panels for each tab
        addProductPanel = new JPanel();
        editProductPanel = new JPanel();
        viewOrdersPanel = new JPanel();
        createProdunctPanel();
        createEditPanel();
        createViewOrdersPanel();
        tabbedPane.addTab("Add Product", addProductPanel);
        tabbedPane.addTab("Edit Product", editProductPanel);
        tabbedPane.addTab("View Orders", viewOrdersPanel);

        // Add tabbed pane to the frame
        add(tabbedPane);
        setVisible(true);
    }

    private void createProdunctPanel() {
        addProductPanel.setLayout(null);
        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setFont(font);
        itemNameLabel.setBounds(10, 10, 200, 30);
        JTextField itemNameField = new JTextField();
        itemNameField.setFont(font);
        itemNameField.setBounds(200, 10, 300, 30);
        JLabel itemTypeLabel = new JLabel("Item Type:");
        itemTypeLabel.setFont(font);
        itemTypeLabel.setBounds(10, 50, 200, 30);
        String[] itemTypes = {"Food", "Resin", "Dessert"};
        JComboBox<String> itemTypeComboBox = new JComboBox<>(itemTypes);
        itemTypeComboBox.setFont(font);
        itemTypeComboBox.setBounds(200, 50, 300, 30);
        JLabel itemPriceLabel = new JLabel("Item Price:");
        itemPriceLabel.setFont(font);
        itemPriceLabel.setBounds(10, 100, 200, 30);
        JTextField itemPriceField = new JTextField();
        itemPriceField.setFont(font);
        itemPriceField.setBounds(200, 100, 300, 30);
        JLabel availableAmountLabel = new JLabel("Available Amount:");
        availableAmountLabel.setFont(font);
        availableAmountLabel.setBounds(10, 150, 200, 30);
        JTextField availableAmountField = new JTextField();
        availableAmountField.setFont(font);
        availableAmountField.setBounds(200, 150, 300, 30);
        JLabel imageIconPathLabel = new JLabel("Item Image Icon Path:");
        imageIconPathLabel.setFont(font);
        imageIconPathLabel.setBounds(10, 200, 200, 30);
        JTextField imageIconPathField = new JTextField();
        imageIconPathField.setFont(font);
        imageIconPathField.setBounds(200, 200, 300, 30);

        addProductPanel.add(itemNameLabel);
        addProductPanel.add(itemNameField);
        addProductPanel.add(itemTypeLabel);
        addProductPanel.add(itemTypeComboBox);
        addProductPanel.add(itemPriceLabel);
        addProductPanel.add(itemPriceField);
        addProductPanel.add(availableAmountLabel);
        addProductPanel.add(availableAmountField);
        addProductPanel.add(imageIconPathLabel);
        addProductPanel.add(imageIconPathField);
        JButton addButton = new JButton("Add");
        addButton.setFont(font);
        addButton.setBounds(10, 300, 200, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (itemNameField.getText().trim().isEmpty() || itemPriceField.getText().trim().isEmpty()
                            || availableAmountField.getText().trim().isEmpty() || imageIconPathField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all the required fields.", "Adding new Item Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String name = itemNameField.getText().trim();
                    String itemType = (String) itemTypeComboBox.getSelectedItem();
                    Admin admin = (Admin) MainScreen.currentPerson;
                    double price = Double.parseDouble(itemPriceField.getText().trim());
                    if (price <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid item price must be greater than 0", "Adding new Item Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int avaliableAmount = Integer.parseInt(availableAmountField.getText().trim());
                    if (avaliableAmount <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid item Available Amount must be greater than 0", "Adding new Item Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String imageIconPath = imageIconPathField.getText().trim();
                    if (itemType.equals("Food")) {
                        MainScreen.allFoods.add(new Food(name, admin, price, avaliableAmount, imageIconPath));
                    } else if (itemType.equals("Dessert")) {
                        MainScreen.allDessert.add(new Dessert(name, admin, price, avaliableAmount, imageIconPath));
                    } else {
                        MainScreen.allResin.add(new Resin(name, admin, price, avaliableAmount, imageIconPath));
                    }
                    JOptionPane.showMessageDialog(null, "Item " + itemType + " " + name + " has been added successfully", "Adding new Item", JOptionPane.INFORMATION_MESSAGE);
                    itemNameField.setText("");
                    itemPriceField.setText("");
                    availableAmountField.setText("");
                    imageIconPathField.setText("");
                    itemTypeComboBox.setSelectedIndex(0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input Data " + ex.getMessage(), "Adding new Item Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Go Back");
        cancelButton.setFont(font);
        cancelButton.setBounds(300, 300, 200, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainScreen();
                dispose();
            }
        });

        addProductPanel.add(addButton);
        addProductPanel.add(cancelButton);

    }

    private void createEditPanel() {
        editProductPanel.setLayout(null);
        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setFont(font);
        itemNameLabel.setBounds(10, 20, 200, 30);
        ArrayList<Item> adminItems = getAllAdminItems();
        String[] itemNames = new String[adminItems.size()];
        for (int i = 0; i < adminItems.size(); i++) {
            itemNames[i] = adminItems.get(i).getName();
        }
        JComboBox<String> itemNameComboBox = new JComboBox<>(itemNames);
        itemNameComboBox.setFont(font);
        itemNameComboBox.setBounds(200, 20, 300, 30);
        JLabel itemPriceLabel = new JLabel("Item Price:");
        itemPriceLabel.setFont(font);
        itemPriceLabel.setBounds(10, 70, 200, 30);
        JTextField itemPriceField = new JTextField();
        itemPriceField.setFont(font);
        itemPriceField.setBounds(200, 70, 300, 30);
        JLabel availableAmountLabel = new JLabel("Available Amount:");
        availableAmountLabel.setFont(font);
        availableAmountLabel.setBounds(10, 120, 200, 30);
        JTextField availableAmountField = new JTextField();
        availableAmountField.setFont(font);
        availableAmountField.setBounds(200, 120, 300, 30);
        JLabel imageIconPathLabel = new JLabel("Item Image Icon Path:");
        imageIconPathLabel.setFont(font);
        imageIconPathLabel.setBounds(10, 170, 200, 30);
        JTextField imageIconPathField = new JTextField();
        imageIconPathField.setFont(font);
        imageIconPathField.setBounds(200, 170, 300, 30);
        if (adminItems.size() != 0) {
            itemPriceField.setText(adminItems.get(0).getPrice() + "");
            availableAmountField.setText(adminItems.get(0).getAvaliableAmount() + "");
            imageIconPathField.setText(adminItems.get(0).getImageIconPath());
        }
        itemNameComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (adminItems.size() != 0) {
                    itemPriceField.setText(adminItems.get(itemNameComboBox.getSelectedIndex()).getPrice() + "");
                    availableAmountField.setText(adminItems.get(itemNameComboBox.getSelectedIndex()).getAvaliableAmount() + "");
                    imageIconPathField.setText(adminItems.get(itemNameComboBox.getSelectedIndex()).getImageIconPath());
                }
            }
        });
        editProductPanel.add(itemNameLabel);
        editProductPanel.add(itemNameComboBox);
        editProductPanel.add(itemPriceLabel);
        editProductPanel.add(itemPriceField);
        editProductPanel.add(availableAmountLabel);
        editProductPanel.add(availableAmountField);
        editProductPanel.add(imageIconPathLabel);
        editProductPanel.add(imageIconPathField);
        JButton updateButton = new JButton("Update");
        updateButton.setFont(font);
        updateButton.setBounds(10, 320, 200, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (adminItems.size() == 0) {
                        JOptionPane.showMessageDialog(null, "You do not have any items in our system yet", "Updating an Item", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (itemPriceField.getText().trim().isEmpty()
                            || availableAmountField.getText().trim().isEmpty() || imageIconPathField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all the required fields.", "Updating an Item Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int index = itemNameComboBox.getSelectedIndex();
                    double price = Double.parseDouble(itemPriceField.getText().trim());
                    if (price <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid item price must be greater than 0", "Updating an Item Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int avaliableAmount = Integer.parseInt(availableAmountField.getText().trim());
                    if (avaliableAmount <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid item Available Amount must be greater than 0", "Updating an Item Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String imageIconPath = imageIconPathField.getText().trim();
                    adminItems.get(index).setAvaliableAmount(avaliableAmount);
                    adminItems.get(index).setPrice(price);
                    adminItems.get(index).setImageIconPath(imageIconPath);
                    JOptionPane.showMessageDialog(null, "Item " + itemNames[index] + " has been updated successfully", "Updating an Item", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input Data " + ex.getMessage(), "Updating an Item Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Go Back");
        cancelButton.setFont(font);
        cancelButton.setBounds(300, 320, 200, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainScreen();
                dispose();
            }
        });

        editProductPanel.add(updateButton);
        editProductPanel.add(cancelButton);

    }

    private void createViewOrdersPanel() {
        viewOrdersPanel.setLayout(null);
        JTextArea ordersTextArea = new JTextArea();
        ordersTextArea.setEditable(false);
        ordersTextArea.setFont(font);
        ordersTextArea.setText(getAdminOrders());
        JScrollPane scrollPane = new JScrollPane(ordersTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 10, 560, 400);
        viewOrdersPanel.add(scrollPane);
        JButton cancelButton = new JButton("Go Back");
        cancelButton.setFont(font);
        cancelButton.setBounds(130, 450, 200, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainScreen();
                dispose();
            }
        });
        viewOrdersPanel.add(cancelButton);
    }
    
    private String getAdminOrders(){
        String orderInfo = "";
        String userOrderInfo = "";
        double total = 0;
        double userTotal = 0;
        for(int i = 0 ; i < MainScreen.allOrders.size(); i++){
            ArrayList<OrderItem> items = MainScreen.allOrders.get(i).getItems();
            userOrderInfo = "";
            userTotal = 0;
            for(OrderItem orderItem : items){
                if(orderItem.getItem().getOwner().getId() == ((Admin)MainScreen.currentPerson).getId()){
                    userOrderInfo += orderItem.getItem().getName()+"\t"+orderItem.getAmount()+"\t"+orderItem.getItem().getPrice()*orderItem.getAmount()+"\n";
                    total += orderItem.getItem().getPrice()*orderItem.getAmount();
                    userTotal += orderItem.getItem().getPrice()*orderItem.getAmount();
                }
            }
            if(!userOrderInfo.isEmpty()){
                orderInfo += MainScreen.allOrders.get(i).getUser().getFullName()+" Has ordered\n";
                orderInfo += userOrderInfo;
                orderInfo += "Total price for "+MainScreen.allOrders.get(i).getUser().getFullName()+" "+userTotal+"\n\n";
            }
        }
        if(!orderInfo.isEmpty()){
            orderInfo += "Total order price: "+total+"\n";
        }
        if(orderInfo.isEmpty()){
            orderInfo = "You do not have any orders yet for your Items in our System";
        }
        return orderInfo;
    }

    private ArrayList<Item> getAllAdminItems() {
        ArrayList<Item> adminItems = new ArrayList<>();
        for (Item item : MainScreen.allFoods) {
            if (item.getOwner().getId() == ((Admin) MainScreen.currentPerson).getId()) {
                adminItems.add(item);
            }
        }

        for (Item item : MainScreen.allDessert) {
            if (item.getOwner().getId() == ((Admin) MainScreen.currentPerson).getId()) {
                adminItems.add(item);
            }
        }

        for (Item item : MainScreen.allResin) {
            if (item.getOwner().getId() == ((Admin) MainScreen.currentPerson).getId()) {
                adminItems.add(item);
            }
        }
        return adminItems;
    }
}
