package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanDataList {
    @SerializedName("plan_id")
    @Expose
    private String planId;

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("spots")
    @Expose
    private String spots;

    public String getSpots() {
        return spots;
    }

    public void setSpots(String spots) {
        this.spots = spots;
    }

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    @SerializedName("plan_name")
    @Expose
    private String planName;
    @SerializedName("plan_image")
    @Expose
    private String planImage;
    @SerializedName("plan_amount")
    @Expose
    private String planAmount;
    @SerializedName("plan_cashback")
    @Expose
    private String planCashback;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("plan_desc")
    @Expose
    private String planDesc;
    @SerializedName("status")
    @Expose
    private String status;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanImage() {
        return planImage;
    }

    public void setPlanImage(String planImage) {
        this.planImage = planImage;
    }

    public String getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(String planAmount) {
        this.planAmount = planAmount;
    }

    public String getPlanCashback() {
        return planCashback;
    }

    public void setPlanCashback(String planCashback) {
        this.planCashback = planCashback;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
