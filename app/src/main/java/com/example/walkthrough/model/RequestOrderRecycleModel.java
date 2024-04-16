package com.example.walkthrough.model;

public class RequestOrderRecycleModel {
    int images,imagecheck,imagecancel;
    String name,gender;


    public RequestOrderRecycleModel() {
    }

    public RequestOrderRecycleModel(int images, int imagecheck, int imagecancel, String name, String gender) {
        this.images = images;
        this.imagecheck = imagecheck;
        this.imagecancel = imagecancel;
        this.name = name;
        this.gender = gender;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getImagecheck() {
        return imagecheck;
    }

    public void setImagecheck(int imagecheck) {
        this.imagecheck = imagecheck;
    }

    public int getImagecancel() {
        return imagecancel;
    }

    public void setImagecancel(int imagecancel) {
        this.imagecancel = imagecancel;
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
}
