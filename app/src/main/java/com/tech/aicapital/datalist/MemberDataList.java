package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberDataList {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("profile_img")
    @Expose
    private String profileImg;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("refer_user_id")
    @Expose
    private String referUserId;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("otp_code")
    @Expose
    private String otpCode;
    @SerializedName("otp_status")
    @Expose
    private String otpStatus;
    @SerializedName("pStatus")
    @Expose
    private String pStatus;
    @SerializedName("reStatus")
    @Expose
    private String reStatus;
    @SerializedName("join_date")
    @Expose
    private String joinDate;
    @SerializedName("avail_balance")
    @Expose
    private String availBalance;
    @SerializedName("bank_ifsc")
    @Expose
    private String bankIfsc;
    @SerializedName("bank_acc_no")
    @Expose
    private String bankAccNo;
    @SerializedName("bankstatus")
    @Expose
    private String bankstatus;
    @SerializedName("purchase_wallet")
    @Expose
    private String purchaseWallet;
    @SerializedName("aadhar")
    @Expose
    private String aadhar;
    @SerializedName("pan")
    @Expose
    private String pan;
    @SerializedName("trading")
    @Expose
    private String trading;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReferUserId() {
        return referUserId;
    }

    public void setReferUserId(String referUserId) {
        this.referUserId = referUserId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(String otpStatus) {
        this.otpStatus = otpStatus;
    }

    public String getPStatus() {
        return pStatus;
    }

    public void setPStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getReStatus() {
        return reStatus;
    }

    public void setReStatus(String reStatus) {
        this.reStatus = reStatus;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(String availBalance) {
        this.availBalance = availBalance;
    }

    public String getBankIfsc() {
        return bankIfsc;
    }

    public void setBankIfsc(String bankIfsc) {
        this.bankIfsc = bankIfsc;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getBankstatus() {
        return bankstatus;
    }

    public void setBankstatus(String bankstatus) {
        this.bankstatus = bankstatus;
    }

    public String getPurchaseWallet() {
        return purchaseWallet;
    }

    public void setPurchaseWallet(String purchaseWallet) {
        this.purchaseWallet = purchaseWallet;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getTrading() {
        return trading;
    }

    public void setTrading(String trading) {
        this.trading = trading;
    }
}
