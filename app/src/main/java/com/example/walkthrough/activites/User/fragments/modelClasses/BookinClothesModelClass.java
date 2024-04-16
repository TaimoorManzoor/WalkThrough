package com.example.walkthrough.activites.User.fragments.modelClasses;

public class BookinClothesModelClass {
    String userId,username,sellerid,productimage,productname,productprice;

    public BookinClothesModelClass() {
    }

    public BookinClothesModelClass(String userId, String username, String sellerid, String productimage, String productname, String productprice) {
        this.userId = userId;
        this.username = username;
        this.sellerid = sellerid;
        this.productimage = productimage;
        this.productname = productname;
        this.productprice = productprice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
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
