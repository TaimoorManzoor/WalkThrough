package com.example.walkthrough.activites.User.fragments.modelClasses;

public class FinalOrdermodelClass {
    String userID,sellerID,productID,orderStatus,productprice,productcount;

    public FinalOrdermodelClass() {
    }

    public FinalOrdermodelClass(String userID, String sellerID, String productID, String orderStatus, String productprice, String productcount) {
        this.userID = userID;
        this.sellerID = sellerID;
        this.productID = productID;
        this.orderStatus = orderStatus;
        this.productprice = productprice;
        this.productcount = productcount;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductcount() {
        return productcount;
    }

    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }
}
