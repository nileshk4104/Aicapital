package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OperatorResponse {
    @SerializedName("city")
    @Expose
    private List<OperatorDataList> city = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<OperatorDataList> getCity() {
        return city;
    }

    public void setCity(List<OperatorDataList> city) {
        this.city = city;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
