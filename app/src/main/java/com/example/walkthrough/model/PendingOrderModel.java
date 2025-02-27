package com.example.walkthrough.model;

public class PendingOrderModel {
    int image;
    String name;
    String detail;

    public PendingOrderModel() {
    }

    public PendingOrderModel(int image, String name, String detail) {
        this.image = image;
        this.name = name;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
