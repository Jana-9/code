package project1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Order {

    private Person user;
    private ArrayList<OrderItem> items;
    private double totalPrice;

    public Order(Person user) {
        this.user = user;
        items = new ArrayList<>();
        totalPrice = 0;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addItem(Item item, int amount) {
        this.items.add(new OrderItem(item, amount));
        this.totalPrice += item.getPrice()*amount;
    }
    

    @Override
    public String toString() {
        String itemsInfo = "";
        for(OrderItem orderItem : items){
            itemsInfo += ","+orderItem.getItem().getId()+","+orderItem.getAmount();
        }
        return ((User)user).getId() +","+items.size()+itemsInfo;
    }

    
}
