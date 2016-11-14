package com.ejb.uplus.bean;

/**
 * Created by John on 10/24/2016.
 */

public class RentedBus {
    private String snapshot;
    private String name;
    private String price;
    private String address;

    public RentedBus(String snapshot, String name, String price) {
        this.snapshot = snapshot;
        this.name = name;
        this.price = price;
    }

    public RentedBus(String snapshot, String name, String price, String address)
    {
        this.snapshot = snapshot;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public String getSnapshot() {

        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String description) {
        this.price = description;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
