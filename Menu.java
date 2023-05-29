package project1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import Project1.Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import static project1.MainScreen.writeDataToFiles;

public final class Menu extends JFrame {

    static private JButton backButton, orderButton;
    static private JTextField textField;
    static private GridBagConstraints gbc;
    private JTable table;
    DefaultTableModel dtm;

    private JSpinner[] numSpinner;
    static private JLabel[] foodLabel;
    static private JLabel[] foodImage;

    private JSpinner[] numSpinnerResin;
    static private JLabel[] resinLabel;
    static private JLabel[] resinImage;

    private JSpinner[] numSpinnerDesserts;
    static private JLabel[] dessertLabel;
    static private JLabel[] dessertImage;

    public Menu() {
        try {
            setTitle("Main Menu ");
            setResizable(false);
            setBounds(100, 100, 900, 600);
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
            getContentPane().setLayout(null);
            setLocationRelativeTo(null);
            JLabel lblFoodOrdered = new JLabel("-- Ordered --");
            lblFoodOrdered.setBounds(640, 11, 100, 16);
            lblFoodOrdered.setFont(new Font("Ordered", Font.BOLD, 17));
            getContentPane().add(lblFoodOrdered);

            backButton = new JButton();
            orderButton = new JButton();
            dtm = new DefaultTableModel(0, 0);
            final String header[] = new String[]{"Item", "Qty", "Price", "Spinner"};
            dtm.setColumnIdentifiers(header);
            dtm.addRow(header);
            table = new JTable();
            table.setModel(dtm);
            table.setBounds(560, 40, 1, 1); // int x, int y, int width, int height
//            table.setSize(245, 300); // width,height
            table.getColumnModel().getColumn(0).setPreferredWidth(80);
            table.getColumnModel().getColumn(1).setPreferredWidth(30);
            table.getColumnModel().getColumn(2).setPreferredWidth(30);
            table.getColumnModel().getColumn(3).setMinWidth(0); // hide spinner
            // column
            table.getColumnModel().getColumn(3).setMaxWidth(0); // hide spinner
            // column
            table.setShowGrid(false); // remove cell boarder
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(570, 40, 245, 300);
            getContentPane().add(scrollPane);
//            getContentPane().add(table);
            JLabel lblTotal = new JLabel("Total  : ");
            lblTotal.setBounds(595, 400, 46, 14);
            getContentPane().add(lblTotal);
            textField = new JTextField("0.0");
            textField.setBounds(650, 400, 86, 20);
            getContentPane().add(textField);
            textField.setColumns(10);
            orderButton = new JButton("Order");
            orderButton.setBackground(Color.WHITE);
            orderButton.setForeground(Color.DARK_GRAY);
            orderButton.setBounds(560, 450, 120, 40);
            getContentPane().add(orderButton);
            backButton = new JButton("Back");
            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.DARK_GRAY);
            backButton.setBounds(710, 450, 120, 40);
            getContentPane().add(backButton);

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            addIt(tabbedPane, "Foods");
            addIt1(tabbedPane, "Resin");
            addIt2(tabbedPane, "Desserts");
            tabbedPane.setBounds(80, 50, 450, 450);
            getContentPane().add(tabbedPane);
            //background
//
            ImageIcon background_image = new ImageIcon("4.jpg");
            Image img = background_image.getImage();
            Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            background_image = new ImageIcon(temp_img);
            JLabel background = new JLabel("", background_image, JLabel.CENTER);
            background.setBounds(0, 0, 900, 600);
            getContentPane().add(background);
            setVisible(true);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new MainScreen();
                    dispose();
                }
            });

            orderButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (table.getRowCount() == 1) {
                        JOptionPane.showMessageDialog(null, "You not ordered anything yet", "Order Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int result = JOptionPane.showConfirmDialog(null, "Confirm Order", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            MainScreen.allOrders.add(new Order(MainScreen.currentPerson));
                            for (int row = 1; row < table.getRowCount(); row++) {
                                MainScreen.allOrders.get(MainScreen.allOrders.size() - 1).addItem(getItemByName(dtm.getValueAt(row, 0).toString()), Integer.parseInt(dtm.getValueAt(row, 1).toString()));
                            }
                            JOptionPane.showMessageDialog(null, "Order has been created successfully", "Order successfully", JOptionPane.INFORMATION_MESSAGE);
                            new MainScreen();
                            dispose();
                        }

                    }
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro in initialize Menu Frame " + e, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private Item getItemByName(String name) {
        for (Food f : MainScreen.allFoods) {
            if (f.getName().equals(name)) {
                return f;
            }
        }

        for (Dessert d : MainScreen.allDessert) {
            if (d.getName().equals(name)) {
                return d;
            }
        }

        for (Resin r : MainScreen.allResin) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    void addIt(JTabbedPane tabbedPane, String text) throws IOException {
        JPanel panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints(); // getting constraints for the specified
        // component
        gbc.insets = new Insets(10, 0, 0, 0);
        foodImage = new JLabel[MainScreen.allFoods.size()];
        foodLabel = new JLabel[MainScreen.allFoods.size()];
        numSpinner = new JSpinner[MainScreen.allFoods.size()];

        for (int i = 0; i < MainScreen.allFoods.size(); i++) {
            try {
                SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
                numSpinner[i] = new JSpinner(spnummodel);
                numSpinner[i].addChangeListener(listener);
                foodImage[i] = new JLabel(MainScreen.allFoods.get(i).getImageIcon());
                foodLabel[i] = new JLabel(MainScreen.allFoods.get(i).getName());
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        gbc.gridx = 0; // gridx 0 represent the most left
        for (int i = 0; i < MainScreen.allFoods.size(); i++) {
            if (i % 3 == 0) {
                gbc.gridy += 2;
                gbc.gridx = 0;
            }
            panel.add(foodImage[i], gbc);
            gbc.gridy++; // gridy---> add one row,for foodLabel
            panel.add(foodLabel[i], gbc);
            gbc.gridy--; // remove the row
            gbc.gridx++; // move to next column
            panel.add(numSpinner[i], gbc);
            gbc.gridx++; // move to next column
            tabbedPane.addTab(text, panel);
        }
    }

    void addIt2(JTabbedPane tabbedPane, String text) throws IOException {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // component
        gbc.insets = new Insets(10, 0, 0, 0);

        dessertImage = new JLabel[MainScreen.allDessert.size()];
        dessertLabel = new JLabel[MainScreen.allDessert.size()];
        numSpinnerDesserts = new JSpinner[MainScreen.allDessert.size()];

        for (int i = 0; i < MainScreen.allDessert.size(); i++) {
            try {
                SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
                numSpinnerDesserts[i] = new JSpinner(spnummodel);
                numSpinnerDesserts[i].addChangeListener(listenerForDesserts);
                dessertImage[i] = new JLabel(MainScreen.allDessert.get(i).getImageIcon());
                dessertLabel[i] = new JLabel(MainScreen.allDessert.get(i).getName());
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        gbc.gridx = 0; // gridx 0 represent the most left
        for (int i = 0; i < MainScreen.allDessert.size(); i++) {
            if (i % 3 == 0) {
                gbc.gridy += 2;
                gbc.gridx = 0;
            }
            panel.add(dessertImage[i], gbc);
            gbc.gridy++; // gridy---> add one row,for foodLabel
            panel.add(dessertLabel[i], gbc);
            gbc.gridy--; // remove the row
            gbc.gridx++; // move to next column
            panel.add(numSpinnerDesserts[i], gbc);
            gbc.gridx++; // move to next column
            tabbedPane.addTab(text, panel);
        }
    }

    void addIt1(JTabbedPane tabbedPane, String text) throws IOException {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // component
        gbc.insets = new Insets(10, 0, 0, 0);

        resinImage = new JLabel[MainScreen.allResin.size()];
        resinLabel = new JLabel[MainScreen.allResin.size()];
        numSpinnerResin = new JSpinner[MainScreen.allResin.size()];

        for (int i = 0; i < MainScreen.allResin.size(); i++) {
            try {
                SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
                numSpinnerResin[i] = new JSpinner(spnummodel);
                numSpinnerResin[i].addChangeListener(listenerForResins);
                resinImage[i] = new JLabel(MainScreen.allResin.get(i).getImageIcon());
                resinLabel[i] = new JLabel(MainScreen.allResin.get(i).getName());
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        gbc.gridx = 0; // gridx 0 represent the most left
        for (int i = 0; i < MainScreen.allResin.size(); i++) {
            if (i % 3 == 0) {
                gbc.gridy += 2;
                gbc.gridx = 0;
            }
            panel.add(resinImage[i], gbc);
            gbc.gridy++; // gridy---> add one row,for foodLabel
            panel.add(resinLabel[i], gbc);
            gbc.gridy--; // remove the row
            gbc.gridx++; // move to next column
            panel.add(numSpinnerResin[i], gbc);
            gbc.gridx++; // move to next column
            tabbedPane.addTab(text, panel);
        }
    }

    ChangeListener listener = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            int index = -1;
            for (int i = 0; i < MainScreen.allFoods.size(); i++) {
                if (numSpinner[i] == (JSpinner) e.getSource()) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                return;
            }
            final int quantity = (int) numSpinner[index].getValue();
            if (!MainScreen.allFoods.get(index).canPurshase(quantity)) {
                JOptionPane.showMessageDialog(null, "Erro in Order there is no enuogh amout of " + MainScreen.allFoods.get(index).getName()+ " Food", "Purchases Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int rows = table.getRowCount();
            boolean exist = false;
            for (int row = 1; row < rows; row++) {
                if (dtm.getValueAt(row, 0).equals(MainScreen.allFoods.get(index).getName())) {
                    if (quantity != 0) {
                        dtm.setValueAt(quantity, row, 1); // obj, row, column
                        dtm.setValueAt(MainScreen.allFoods.get(index).getPrice() * quantity, row, 2);
                        exist = true;
                        break;
                    } else {
                        dtm.removeRow(row);
                        exist = true;
                        break;
                    }
                }
            }
            if (!exist && quantity > 0) {
                dtm.addRow(new Object[]{MainScreen.allFoods.get(index).getName(), quantity, MainScreen.allFoods.get(index).getPrice() * quantity, numSpinner[index]});
            }
            rows = table.getRowCount();
            double total = 0;
            for (int row = 1; row < rows; row++) {
                total += Double.parseDouble(dtm.getValueAt(row, 2).toString());
            }
            textField.setText(total + "");
        }
    };

    ChangeListener listenerForDesserts = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            int index = -1;
            for (int i = 0; i < MainScreen.allDessert.size(); i++) {
                if (numSpinnerDesserts[i] == (JSpinner) e.getSource()) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                return;
            }
            final int quantity = (int) numSpinnerDesserts[index].getValue();
            if (!MainScreen.allDessert.get(index).canPurshase(quantity)) {
                JOptionPane.showMessageDialog(null, "Erro in Order there is no enuogh amout of " + MainScreen.allDessert.get(index).getName() + " Dessert", "Purchases Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int rows = table.getRowCount();
            boolean exist = false;
            for (int row = 1; row < rows; row++) {
                if (dtm.getValueAt(row, 0).equals(MainScreen.allDessert.get(index).getName())) {
                    if (quantity != 0) {
                        dtm.setValueAt(quantity, row, 1); // obj, row, column
                        dtm.setValueAt(MainScreen.allDessert.get(index).getPrice() * quantity, row, 2);
                        exist = true;
                        break;
                    } else {
                        dtm.removeRow(row);
                        exist = true;
                        break;
                    }
                }
            }
            if (!exist && quantity > 0) {
                dtm.addRow(new Object[]{MainScreen.allDessert.get(index).getName(), quantity, MainScreen.allDessert.get(index).getPrice() * quantity, numSpinnerDesserts[index]});
            }
            rows = table.getRowCount();
            double total = 0;
            for (int row = 1; row < rows; row++) {
                total += Double.parseDouble(dtm.getValueAt(row, 2).toString());
            }
            textField.setText(total + "");
        }
    };

    ChangeListener listenerForResins = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            int index = -1;
            for (int i = 0; i < MainScreen.allResin.size(); i++) {
                if (numSpinnerResin[i] == (JSpinner) e.getSource()) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                return;
            }
            final int quantity = (int) numSpinnerResin[index].getValue();
            if (!MainScreen.allResin.get(index).canPurshase(quantity)) {
                JOptionPane.showMessageDialog(null, "Erro in Order there is no enuogh amout of " + MainScreen.allResin.get(index).getName() + " Resin", "Purchases Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int rows = table.getRowCount();
            boolean exist = false;
            for (int row = 1; row < rows; row++) {
                if (dtm.getValueAt(row, 0).equals(MainScreen.allResin.get(index).getName())) {
                    if (quantity != 0) {
                        dtm.setValueAt(quantity, row, 1); // obj, row, column
                        dtm.setValueAt(MainScreen.allResin.get(index).getPrice() * quantity, row, 2);
                        exist = true;
                        break;
                    } else {
                        dtm.removeRow(row);
                        exist = true;
                        break;
                    }
                }
            }
            if (!exist && quantity > 0) {
                dtm.addRow(new Object[]{MainScreen.allResin.get(index).getName(), quantity, MainScreen.allResin.get(index).getPrice() * quantity, numSpinnerResin[index]});
            }
            rows = table.getRowCount();
            double total = 0;
            for (int row = 1; row < rows; row++) {
                total += Double.parseDouble(dtm.getValueAt(row, 2).toString());
            }
            textField.setText(total + "");
        }
    };

}
