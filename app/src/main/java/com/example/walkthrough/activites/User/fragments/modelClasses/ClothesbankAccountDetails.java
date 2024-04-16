package com.example.walkthrough.activites.User.fragments.modelClasses;

public class ClothesbankAccountDetails {
    String userID,username,userAddresses,useraccount;

    public ClothesbankAccountDetails() {
    }

    public ClothesbankAccountDetails(String userID, String username, String userAddresses, String useraccount) {
        this.userID = userID;
        this.username = username;
        this.userAddresses = userAddresses;
        this.useraccount = useraccount;
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

    public String getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(String userAddresses) {
        this.userAddresses = userAddresses;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }
}
