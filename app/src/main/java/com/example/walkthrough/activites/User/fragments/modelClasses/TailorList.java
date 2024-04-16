package com.example.walkthrough.activites.User.fragments.modelClasses;

import android.widget.TextView;

public class TailorList {
    private String tailorName, tailorGender, price,imageurl;


    public TailorList() {
    }

    public TailorList(String tailorName, String tailorGender, String price, String tailorImg) {
        this.tailorName = tailorName;
        this.tailorGender = tailorGender;
        this.price = price;
        this.imageurl = tailorImg;
    }

    public String getTailorName() {
        return tailorName;
    }

    public void setTailorName(String tailorName) {
        this.tailorName = tailorName;
    }

    public String getTailorGender() {
        return tailorGender;
    }

    public void setTailorGender(String tailorGender) {
        this.tailorGender = tailorGender;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
