package com.example.walkthrough.activites.Clothes.ModelClass;

public class ClothesBookingModelClass {
    String sellerID,userID,username,productimage,productname,productprice;

    public ClothesBookingModelClass() {
    }

    public ClothesBookingModelClass(String sellerID, String userID, String username, String productimage, String productname, String productprice) {
        this.sellerID = sellerID;
        this.userID = userID;
        this.username = username;
        this.productimage = productimage;
        this.productname = productname;
        this.productprice = productprice;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
}
