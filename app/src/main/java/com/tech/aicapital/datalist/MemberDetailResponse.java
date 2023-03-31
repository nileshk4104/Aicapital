package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberDetailResponse {
    @SerializedName("fetch_login_data")
    @Expose
    private List<MemberDataList> fetchLoginData = null;

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    public List<MemberDataList> getFetchLoginData() {
        return fetchLoginData;
    }
    public void setFetchLoginData(List<MemberDataList> fetchLoginData) {
        this.fetchLoginData = fetchLoginData;
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
