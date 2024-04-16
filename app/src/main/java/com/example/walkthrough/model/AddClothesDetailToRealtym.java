package com.example.walkthrough.model;

public class AddClothesDetailToRealtym {
    String userid,username,clothestype,imageurl;

    public AddClothesDetailToRealtym() {
    }

    public AddClothesDetailToRealtym(String userid, String username, String clothestype, String imageurl) {
        this.userid = userid;
        this.username = username;
        this.clothestype = clothestype;
        this.imageurl = imageurl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClothestype() {
        return clothestype;
    }

    public void setClothestype(String clothestype) {
        this.clothestype = clothestype;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
