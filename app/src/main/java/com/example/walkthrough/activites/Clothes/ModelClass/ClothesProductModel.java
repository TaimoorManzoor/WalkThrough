package com.example.walkthrough.activites.Clothes.ModelClass;

public class ClothesProductModel {
    private String productImg;
    private String productName;
    private String category;
    private  String productPrice;
    private String productCount;

    public ClothesProductModel(String productImg, String productName, String category, String productPrice, String productCount) {
        this.productImg = productImg;
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public ClothesProductModel() {
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }
}
