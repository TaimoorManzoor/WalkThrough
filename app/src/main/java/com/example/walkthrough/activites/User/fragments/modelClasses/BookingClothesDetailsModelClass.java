package com.example.walkthrough.activites.User.fragments.modelClasses;

public class BookingClothesDetailsModelClass {
    String userID,sellerID,productID,Productimage,productname,productcounter;

    public BookingClothesDetailsModelClass() {
    }

    public BookingClothesDetailsModelClass(String userID, String sellerID, String productID, String productimage, String productname, String productcounter) {
        this.userID = userID;
        this.sellerID = sellerID;
        this.productID = productID;
        Productimage = productimage;
        this.productname = productname;
        this.productcounter = productcounter;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductimage() {
        return Productimage;
    }

    public void setProductimage(String productimage) {
        Productimage = productimage;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcounter() {
        return productcounter;
    }

    public void setProductcounter(String productcounter) {
        this.productcounter = productcounter;
    }
}
