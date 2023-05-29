
package project1;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Item {
    private String name;
    private Admin owner;
    private double price;
    private int avaliableAmount;
    private ImageIcon imageIcon;
    private String imageIconPath;
    private int id;
    private static int ID = 1;


    public Item(String name, Admin owner, double price, int avaliableAmount, String imageIconPath) {
        this.name = name;
        this.owner = owner;
        this.price = price;
        this.avaliableAmount = avaliableAmount;
        this.imageIconPath = imageIconPath;
        try {
            Image image = ImageIO.read(this.getClass().getResource("/"+this.imageIconPath));
            Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
            this.imageIcon = new ImageIcon(imageScaled);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not read Item Image Icon with name "+this.imageIconPath, "Invalid item Image Icon path", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getOwner() {
        return owner;
    }

    public void setOwner(Admin owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvaliableAmount() {
        return avaliableAmount;
    }

    public void setAvaliableAmount(int avaliableAmount) {
        this.avaliableAmount = avaliableAmount;
    }
    
    public boolean canPurshase(int amount){
        return amount <= this.avaliableAmount;
    }
    
    public void purshase(int amount){
        this.avaliableAmount -= amount;
    }
    
    public void cancelPurshase(int amount){
        this.avaliableAmount += amount;
    }
    
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getImageIconPath() {
        return imageIconPath;
    }

    public void setImageIconPath(String imageIconPath) {
        this.imageIconPath = imageIconPath;
        try {
            Image image = ImageIO.read(this.getClass().getResource("/"+this.imageIconPath));
            Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
            this.imageIcon = new ImageIcon(imageScaled);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not read Item Image Icon with name "+this.imageIconPath, "Invalid item Image Icon path", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    

    @Override
    public String toString() {
        return this.id + ","+this.name+","+this.owner.getId()+","+this.price+","+this.avaliableAmount+","+imageIconPath;
    }
    
    
}
