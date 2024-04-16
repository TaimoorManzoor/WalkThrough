package com.example.walkthrough.model;

public class RequestedOrderModel {
    int image;
    String name,address;

    public RequestedOrderModel() {
    }

    public RequestedOrderModel(int image, String name, String address) {
        this.image = image;
        this.name = name;
        this.address = address;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
