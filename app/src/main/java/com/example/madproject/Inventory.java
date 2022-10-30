package com.example.madproject;

public class Inventory {
    String ID;
    String name;
    String supplier;
    String quantity;
    String U_price;
    String description;

    public Inventory(String ID, String name, String supplier, String quantity, String u_price, String description) {
        this.ID = ID;
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        this.U_price = u_price;
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Inventory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getU_price() {
        return U_price;
    }

    public void setU_price(String u_price) {
        U_price = u_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
