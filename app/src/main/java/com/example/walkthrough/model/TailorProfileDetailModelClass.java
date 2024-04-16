package com.example.walkthrough.model;

public class TailorProfileDetailModelClass {
    String userid,username,imageurl,clothestype;

    public TailorProfileDetailModelClass() {
    }

    public TailorProfileDetailModelClass(String userid, String username, String imageurl, String clothestype) {
        this.userid = userid;
        this.username = username;
        this.imageurl = imageurl;
        this.clothestype = clothestype;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getClothestype() {
        return clothestype;
    }

    public void setClothestype(String clothestype) {
        this.clothestype = clothestype;
    }
}
