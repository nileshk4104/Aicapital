package com.tech.aicapital.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressData {

    @SerializedName("rec_no")
    @Expose
    private String recNo;

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("longi")
    @Expose
    private Double longi;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("flat_no")
    @Expose
    private String flatNo;
    @SerializedName("building_name")
    @Expose
    private String buildingName;
    @SerializedName("address_one")
    @Expose
    private String addressOne;
    @SerializedName("address_two")
    @Expose
    private String addressTwo;
    @SerializedName("pin_code")
    @Expose
    private String pinCode;
    @SerializedName("user_name")
    @Expose
    private String userName;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
