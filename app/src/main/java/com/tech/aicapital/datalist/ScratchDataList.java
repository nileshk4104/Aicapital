package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScratchDataList {

    @SerializedName("scratch_id")
    @Expose
    private String scratchId;

    @SerializedName("scratch_image")
    @Expose
    private String scratchImage;

    public String getScratchImage() {
        return scratchImage;
    }

    public void setScratchImage(String scratchImage) {
        this.scratchImage = scratchImage;
    }

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("scratch_name")
    @Expose
    private String scratchName;
    @SerializedName("scratch_worth")
    @Expose
    private String scratchWorth;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("status")
    @Expose
    private String status;

    public String getScratchId() {
        return scratchId;
    }

    public void setScratchId(String scratchId) {
        this.scratchId = scratchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScratchName() {
        return scratchName;
    }

    public void setScratchName(String scratchName) {
        this.scratchName = scratchName;
    }

    public String getScratchWorth() {
        return scratchWorth;
    }

    public void setScratchWorth(String scratchWorth) {
        this.scratchWorth = scratchWorth;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
