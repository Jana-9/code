
package project1;

public class OrderItem {
    private Item item;
    private int amount;

    public OrderItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void increaseAmount(int amount){
        this.amount = amount;
    }
    
}
