package com.example.walkthrough.model;

public class ClotheModel {
    String name,gender,status;
    int image;


    public ClotheModel() {
    }

    public ClotheModel(String name, String gender, String status, int image) {
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.image = image;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
