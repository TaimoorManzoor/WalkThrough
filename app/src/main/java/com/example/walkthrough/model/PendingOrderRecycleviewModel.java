package com.example.walkthrough.model;

public class PendingOrderRecycleviewModel
{
    int image;
    String name,price,suitecategory,date;

    public PendingOrderRecycleviewModel() {
    }

    public PendingOrderRecycleviewModel(int image, String name, String price, String suitecategory, String date) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.suitecategory = suitecategory;
        this.date = date;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSuitecategory() {
        return suitecategory;
    }

    public void setSuitecategory(String suitecategory) {
        this.suitecategory = suitecategory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
