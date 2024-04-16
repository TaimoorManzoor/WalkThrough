package com.example.walkthrough.activites.User.fragments.modelClasses;

public class ClothesProduct {
    private String imageUrl;

    // Empty constructor needed for Firebase
    public ClothesProduct() {
    }

    public ClothesProduct(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}