package swiggy.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private  String name;
    private String phoneNumber;
    private String password;
    private String emailId;
    private  int id;
    private static int idCount;

    public List<Food> cart = new ArrayList<>();

    public User(String name, String phoneNumber,  String emailId, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailId = emailId;
        this.id = idCount++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Food> getCart() {
        return cart;
    }

    public void addFoodToCart(Food selectedFood) {
        cart.add(selectedFood);
    }

    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }
}
