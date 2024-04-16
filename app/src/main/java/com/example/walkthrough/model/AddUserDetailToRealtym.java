package com.example.walkthrough.model;

public class AddUserDetailToRealtym {
    String userID,username,phone,userImage;

    public AddUserDetailToRealtym() {
    }

    public AddUserDetailToRealtym(String userID, String username, String phone, String userImage) {
        this.userID = userID;
        this.username = username;
        this.phone = phone;
        this.userImage = userImage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
