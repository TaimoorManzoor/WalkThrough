package com.example.walkthrough.model;

public class ClothesOrderRecivedModel
{
    String clothes_name,clothes_category,clothes_date,clothes_price;
    int clothes_image;

    public ClothesOrderRecivedModel() {
    }

    public ClothesOrderRecivedModel(String clothes_name, String clothes_category, String clothes_date, String clothes_price, int clothes_image) {
        this.clothes_name = clothes_name;
        this.clothes_category = clothes_category;
        this.clothes_date = clothes_date;
        this.clothes_price = clothes_price;
        this.clothes_image = clothes_image;
    }

    public String getClothes_name() {
        return clothes_name;
    }

    public void setClothes_name(String clothes_name) {
        this.clothes_name = clothes_name;
    }

    public String getClothes_category() {
        return clothes_category;
    }

    public void setClothes_category(String clothes_category) {
        this.clothes_category = clothes_category;
    }

    public String getClothes_date() {
        return clothes_date;
    }

    public void setClothes_date(String clothes_date) {
        this.clothes_date = clothes_date;
    }

    public String getClothes_price() {
        return clothes_price;
    }

    public void setClothes_price(String clothes_price) {
        this.clothes_price = clothes_price;
    }

    public int getClothes_image() {
        return clothes_image;
    }

    public void setClothes_image(int clothes_image) {
        this.clothes_image = clothes_image;
    }
}
