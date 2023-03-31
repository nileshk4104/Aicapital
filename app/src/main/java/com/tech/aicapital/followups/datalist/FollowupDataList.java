package com.tech.aicapital.followups.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowupDataList {
    @SerializedName("rec_no")
    @Expose
    private String recNo;
    @SerializedName("cust_id")
    @Expose
    private String custId;
    @SerializedName("follow_date")
    @Expose
    private String followDate;
    @SerializedName("follow_up")
    @Expose
    private String followUp;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("follow_status")
    @Expose
    private String followStatus;
    @SerializedName("remind_date")
    @Expose
    private String remindDate;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getFollowDate() {
        return followDate;
    }

    public void setFollowDate(String followDate) {
        this.followDate = followDate;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public String getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate = remindDate;
    }
}
