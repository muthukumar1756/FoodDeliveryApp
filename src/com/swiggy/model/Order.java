package com.swiggy.model;

/**
 * <p>
 * Represents order entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class Order {

    private int id;
    private int userId;
    private String address;
    private float totalAmount;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }
}