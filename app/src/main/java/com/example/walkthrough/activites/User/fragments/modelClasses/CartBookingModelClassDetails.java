package com.example.walkthrough.activites.User.fragments.modelClasses;

public class CartBookingModelClassDetails {
    String userID,sellerID,productId,productname,productprice,productimage,reqstatus,prodcutcount;

    public CartBookingModelClassDetails() {
    }

    public CartBookingModelClassDetails(String userID, String sellerID, String productId, String productname, String productprice, String productimage, String reqstatus, String prodcutcount) {
        this.userID = userID;
        this.sellerID = sellerID;
        this.productId = productId;
        this.productname = productname;
        this.productprice = productprice;
        this.productimage = productimage;
        this.reqstatus = reqstatus;
        this.prodcutcount = prodcutcount;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }

    public String getProdcutcount() {
        return prodcutcount;
    }

    public void setProdcutcount(String prodcutcount) {
        this.prodcutcount = prodcutcount;
    }
}
