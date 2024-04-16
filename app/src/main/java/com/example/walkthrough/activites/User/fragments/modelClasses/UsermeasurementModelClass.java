package com.example.walkthrough.activites.User.fragments.modelClasses;

public class UsermeasurementModelClass {
    String Shoulder,Stomach,FrontNeck,SleeveNeck,Waist,BodyWidth,FullLength,tailorid,userid;

    public UsermeasurementModelClass() {
    }

    public UsermeasurementModelClass(String shoulder, String stomach, String frontNeck, String sleeveNeck, String waist, String bodyWidth, String fullLength, String tailorid, String userid) {
        Shoulder = shoulder;
        Stomach = stomach;
        FrontNeck = frontNeck;
        SleeveNeck = sleeveNeck;
        Waist = waist;
        BodyWidth = bodyWidth;
        FullLength = fullLength;
        this.tailorid = tailorid;
        this.userid = userid;
    }

    public String getShoulder() {
        return Shoulder;
    }

    public void setShoulder(String shoulder) {
        Shoulder = shoulder;
    }

    public String getStomach() {
        return Stomach;
    }

    public void setStomach(String stomach) {
        Stomach = stomach;
    }

    public String getFrontNeck() {
        return FrontNeck;
    }

    public void setFrontNeck(String frontNeck) {
        FrontNeck = frontNeck;
    }

    public String getSleeveNeck() {
        return SleeveNeck;
    }

    public void setSleeveNeck(String sleeveNeck) {
        SleeveNeck = sleeveNeck;
    }

    public String getWaist() {
        return Waist;
    }

    public void setWaist(String waist) {
        Waist = waist;
    }

    public String getBodyWidth() {
        return BodyWidth;
    }

    public void setBodyWidth(String bodyWidth) {
        BodyWidth = bodyWidth;
    }

    public String getFullLength() {
        return FullLength;
    }

    public void setFullLength(String fullLength) {
        FullLength = fullLength;
    }

    public String getTailorid() {
        return tailorid;
    }

    public void setTailorid(String tailorid) {
        this.tailorid = tailorid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
