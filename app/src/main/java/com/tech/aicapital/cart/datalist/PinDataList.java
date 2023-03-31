package com.tech.aicapital.cart.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PinDataList {

    @SerializedName("user_name")
    @Expose
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @SerializedName("plan_name")
    @Expose
    private String planName;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @SerializedName("pin_no")
    @Expose
    private String pinNo;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("assigned_to")
    @Expose
    private String assignedTo;
    @SerializedName("used_to")
    @Expose
    private String usedTo;
    @SerializedName("plan_id")
    @Expose
    private String planId;
    @SerializedName("screenshot")
    @Expose
    private String screenshot;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("operate_by")
    @Expose
    private String operateBy;

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getUsedTo() {
        return usedTo;
    }

    public void setUsedTo(String usedTo) {
        this.usedTo = usedTo;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy;
    }


}
