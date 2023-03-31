package com.tech.aicapital.followups.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerDataList
{

    @SerializedName("education")
    @Expose
    private String education;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("cname")
    @Expose
    private String cname;
    @SerializedName("sponser_id")
    @Expose
    private String sponserId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("education_id")
    @Expose
    private String educationId;
    @SerializedName("distict_id")
    @Expose
    private String distictId;
    @SerializedName("locality_id")
    @Expose
    private String localityId;
    @SerializedName("work")
    @Expose
    private String work;
    @SerializedName("m_income")
    @Expose
    private String mIncome;
    @SerializedName("reference_thr")
    @Expose
    private String referenceThr;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSponserId() {
        return sponserId;
    }

    public void setSponserId(String sponserId) {
        this.sponserId = sponserId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getDistictId() {
        return distictId;
    }

    public void setDistictId(String distictId) {
        this.distictId = distictId;
    }

    public String getLocalityId() {
        return localityId;
    }

    public void setLocalityId(String localityId) {
        this.localityId = localityId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getmIncome() {
        return mIncome;
    }

    public void setmIncome(String mIncome) {
        this.mIncome = mIncome;
    }

    public String getReferenceThr() {
        return referenceThr;
    }

    public void setReferenceThr(String referenceThr) {
        this.referenceThr = referenceThr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


}
