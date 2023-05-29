package project1;

public class User extends Person {
    private int id;
    private static int ID = 1;

    public User(String firstName, String lastName, String address, String email, String password) {
        super(firstName, lastName, address, email, password);
        this.id = ID++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + "," + super.toString();
    }

}
