package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubcatDataResponse {

    @SerializedName("data")
    @Expose
    private List<SubcatDataList> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("base_url")
    @Expose
    private String baseUrl;

    public List<SubcatDataList> getData() {
        return data;
    }

    public void setData(List<SubcatDataList> data) {
        this.data = data;
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }



}
