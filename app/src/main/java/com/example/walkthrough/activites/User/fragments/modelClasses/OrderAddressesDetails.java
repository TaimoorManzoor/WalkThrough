package com.example.walkthrough.activites.User.fragments.modelClasses;

public class OrderAddressesDetails {
    String sellerID,accountname,accountnumber,orderAddresses;

    public OrderAddressesDetails() {
    }

    public OrderAddressesDetails(String sellerID, String accountname, String accountnumber, String orderAddresses) {
        this.sellerID = sellerID;
        this.accountname = accountname;
        this.accountnumber = accountnumber;
        this.orderAddresses = orderAddresses;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getOrderAddresses() {
        return orderAddresses;
    }

    public void setOrderAddresses(String orderAddresses) {
        this.orderAddresses = orderAddresses;
    }
}
