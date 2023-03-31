package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewResponse {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

}
