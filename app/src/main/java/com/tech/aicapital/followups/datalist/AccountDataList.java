package com.tech.aicapital.followups.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDataList {
    @SerializedName("rec_no")
    @Expose
    private String recNo;

    @SerializedName("cur_acc")
    @Expose
    private String curAcc;

    public String getCurAcc() {
        return curAcc;
    }

    public void setCurAcc(String curAcc) {
        this.curAcc = curAcc;
    }

    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("status")
    @Expose
    private String status;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
