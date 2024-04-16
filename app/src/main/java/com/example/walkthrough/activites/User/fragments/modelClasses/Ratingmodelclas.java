package com.example.walkthrough.activites.User.fragments.modelClasses;

public class Ratingmodelclas {
    String userID,ratingnumber;

    public Ratingmodelclas() {
    }

    public Ratingmodelclas(String userID, String ratingnumber) {
        this.userID = userID;
        this.ratingnumber = ratingnumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRatingnumber() {
        return ratingnumber;
    }

    public void setRatingnumber(String ratingnumber) {
        this.ratingnumber = ratingnumber;
    }
}
