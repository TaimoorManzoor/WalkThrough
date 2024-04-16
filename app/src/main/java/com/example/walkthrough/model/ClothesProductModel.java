package com.example.walkthrough.model;

public class ClothesProductModel {
    String selleruserID,sellerproductusername,productimage,des,category,color,Count;


    public ClothesProductModel() {
    }

    public ClothesProductModel(String selleruserID, String sellerproductusername, String productimage, String des, String category, String color, String count) {
        this.selleruserID = selleruserID;
        this.sellerproductusername = sellerproductusername;
        this.productimage = productimage;
        this.des = des;
        this.category = category;
        this.color = color;
        Count = count;
    }

    public String getSelleruserID() {
        return selleruserID;
    }

    public void setSelleruserID(String selleruserID) {
        this.selleruserID = selleruserID;
    }

    public String getSellerproductusername() {
        return sellerproductusername;
    }

    public void setSellerproductusername(String sellerproductusername) {
        this.sellerproductusername = sellerproductusername;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
}
