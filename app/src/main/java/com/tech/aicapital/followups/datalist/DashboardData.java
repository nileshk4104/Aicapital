package com.tech.aicapital.followups.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardData {
    @SerializedName("todaysPaidProfit")
    @Expose
    private String todaysPaidProfit;

    @SerializedName("todaysPendingprofit")
    @Expose
    private String todaysPendingprofit;

    public String getTodaysPaidProfit() {
        return todaysPaidProfit;
    }

    public void setTodaysPaidProfit(String todaysPaidProfit) {
        this.todaysPaidProfit = todaysPaidProfit;
    }

    public String getTodaysPendingprofit() {
        return todaysPendingprofit;
    }

    public void setTodaysPendingprofit(String todaysPendingprofit) {
        this.todaysPendingprofit = todaysPendingprofit;
    }

    @SerializedName("unverifiedDeposits")
    @Expose
    private String unverifiedDeposits;

    @SerializedName("verifiedDeposits")
    @Expose
    private String verifiedDeposits;

    public String getUnverifiedDeposits() {
        return unverifiedDeposits;
    }

    public void setUnverifiedDeposits(String unverifiedDeposits) {
        this.unverifiedDeposits = unverifiedDeposits;
    }

    public String getVerifiedDeposits() {
        return verifiedDeposits;
    }

    public void setVerifiedDeposits(String verifiedDeposits) {
        this.verifiedDeposits = verifiedDeposits;
    }

    @SerializedName("activeUser")
    @Expose
    private String activeUser;
    @SerializedName("inActiveUser")
    @Expose
    private String inActiveUser;
    @SerializedName("totalInvestmentCounts")
    @Expose
    private String totalInvestmentCounts;
    @SerializedName("totalInvestment")
    @Expose
    private String totalInvestment;
    @SerializedName("intrestDuePending")
    @Expose
    private String intrestDuePending;
    @SerializedName("intrestDuePaid")
    @Expose
    private String intrestDuePaid;

    public String getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }

    public String getInActiveUser() {
        return inActiveUser;
    }

    public void setInActiveUser(String inActiveUser) {
        this.inActiveUser = inActiveUser;
    }

    public String getTotalInvestmentCounts() {
        return totalInvestmentCounts;
    }

    public void setTotalInvestmentCounts(String totalInvestmentCounts) {
        this.totalInvestmentCounts = totalInvestmentCounts;
    }

    public String getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(String totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public String getIntrestDuePending() {
        return intrestDuePending;
    }

    public void setIntrestDuePending(String intrestDuePending) {
        this.intrestDuePending = intrestDuePending;
    }

    public String getIntrestDuePaid() {
        return intrestDuePaid;
    }

    public void setIntrestDuePaid(String intrestDuePaid) {
        this.intrestDuePaid = intrestDuePaid;
    }

}
