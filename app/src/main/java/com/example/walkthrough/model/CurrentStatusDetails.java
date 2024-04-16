package com.example.walkthrough.model;

public class CurrentStatusDetails {
    String userID,username,userpassword,useremail,conpass,phonumber,currentstatus;

    public CurrentStatusDetails(String userID, String username, String userpassword, String useremail, String conpass, String phonumber, String currentstatus) {
        this.userID = userID;
        this.username = username;
        this.userpassword = userpassword;
        this.useremail = useremail;
        this.conpass = conpass;
        this.phonumber = phonumber;
        this.currentstatus = currentstatus;
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

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }

    public String getPhonumber() {
        return phonumber;
    }

    public void setPhonumber(String phonumber) {
        this.phonumber = phonumber;
    }

    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }
}
