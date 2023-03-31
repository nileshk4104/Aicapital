package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionDatalist {
    @SerializedName("level")
    @Expose
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @SerializedName("rec_no")
    @Expose
    private String recNo;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pre_balance")
    @Expose
    private String preBalance;
    @SerializedName("credit_amt")
    @Expose
    private String creditAmt;
    @SerializedName("debit_amt")
    @Expose
    private String debitAmt;
    @SerializedName("avail_balance")
    @Expose
    private String availBalance;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("reference_user_id")
    @Expose
    private String referenceUserId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("type")
    @Expose
    private String type;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPreBalance() {
        return preBalance;
    }

    public void setPreBalance(String preBalance) {
        this.preBalance = preBalance;
    }

    public String getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(String debitAmt) {
        this.debitAmt = debitAmt;
    }

    public String getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(String availBalance) {
        this.availBalance = availBalance;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getReferenceUserId() {
        return referenceUserId;
    }

    public void setReferenceUserId(String referenceUserId) {
        this.referenceUserId = referenceUserId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
