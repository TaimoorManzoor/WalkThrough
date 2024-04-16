package com.example.walkthrough.model;

public class TailorCategoryModel
{
    String status;
    String name;
    String gender;
    int image;


    public TailorCategoryModel() {

    }

    public TailorCategoryModel(String status, String name, String gender, int image) {
        this.status = status;
        this.name = name;
        this.gender = gender;
        this.image = image;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
