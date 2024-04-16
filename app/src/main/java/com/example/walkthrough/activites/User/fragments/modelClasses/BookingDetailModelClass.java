package com.example.walkthrough.activites.User.fragments.modelClasses;

public class BookingDetailModelClass {
    String TailorID,userID,reqID,username,userimage;

    public BookingDetailModelClass() {
    }

    public BookingDetailModelClass(String tailorID, String userID, String reqID, String username, String userimage) {
        TailorID = tailorID;
        this.userID = userID;
        this.reqID = reqID;
        this.username = username;
        this.userimage = userimage;
    }

    public String getTailorID() {
        return TailorID;
    }

    public void setTailorID(String tailorID) {
        TailorID = tailorID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }
}
