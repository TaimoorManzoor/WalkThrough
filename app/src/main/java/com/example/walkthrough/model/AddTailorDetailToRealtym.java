package com.example.walkthrough.model;

public class AddTailorDetailToRealtym {
    String userid,username,clothestype,imageurl,tailorprice,tailoraddresses,phone;

    public AddTailorDetailToRealtym() {
    }

    public AddTailorDetailToRealtym(String userid, String username, String clothestype, String imageurl, String tailorprice, String tailoraddresses, String phone) {
        this.userid = userid;
        this.username = username;
        this.clothestype = clothestype;
        this.imageurl = imageurl;
        this.tailorprice = tailorprice;
        this.tailoraddresses = tailoraddresses;
        this.phone = phone;
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

    public String getTailorprice() {
        return tailorprice;
    }

    public void setTailorprice(String tailorprice) {
        this.tailorprice = tailorprice;
    }

    public String getTailoraddresses() {
        return tailoraddresses;
    }

    public void setTailoraddresses(String tailoraddresses) {
        this.tailoraddresses = tailoraddresses;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
