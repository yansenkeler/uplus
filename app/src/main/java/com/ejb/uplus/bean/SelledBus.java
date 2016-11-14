package com.ejb.uplus.bean;

/**
 * Created by John on 10/24/2016.
 */

public class SelledBus {
    private String snapshot;
    private String name;
    private String description;
    private String price;

    public SelledBus(String snapshot, String name, String price) {
        this.snapshot = snapshot;
        this.name = name;
        this.price = price;
    }

    public SelledBus(String snapshot, String name, String description, String price)
    {
        this.snapshot = snapshot;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
