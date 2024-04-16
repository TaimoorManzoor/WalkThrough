package com.example.walkthrough.activites.User.fragments.modelClasses;

public class RequestModelCLass {
    String UserSenderID,TailorReceiverID,RequestID,RequestStatus,TailorPrice;

    public RequestModelCLass() {
    }

    public RequestModelCLass(String userSenderID, String tailorReceiverID, String requestID, String requestStatus, String tailorPrice) {
        UserSenderID = userSenderID;
        TailorReceiverID = tailorReceiverID;
        RequestID = requestID;
        RequestStatus = requestStatus;
        TailorPrice = tailorPrice;
    }

    public String getUserSenderID() {
        return UserSenderID;
    }

    public void setUserSenderID(String userSenderID) {
        UserSenderID = userSenderID;
    }

    public String getTailorReceiverID() {
        return TailorReceiverID;
    }

    public void setTailorReceiverID(String tailorReceiverID) {
        TailorReceiverID = tailorReceiverID;
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
    }

    public String getRequestStatus() {
        return RequestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        RequestStatus = requestStatus;
    }

    public String getTailorPrice() {
        return TailorPrice;
    }

    public void setTailorPrice(String tailorPrice) {
        TailorPrice = tailorPrice;
    }
}
