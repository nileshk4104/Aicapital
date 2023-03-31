package com.tech.aicapital.followups.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDashboardData {

    @SerializedName("totalDeposit")
    @Expose
    private String totalDeposit;
    @SerializedName("totalWithdraw")
    @Expose
    private String totalWithdraw;
    @SerializedName("todaysProfit")
    @Expose
    private String todaysProfit;
    @SerializedName("totalProfit")
    @Expose
    private String totalProfit;
    @SerializedName("totalAverage")
    @Expose
    private String totalAverage;
    @SerializedName("totalMonthly")
    @Expose
    private String totalMonthly;

    public String getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(String totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public String getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(String totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public String getTodaysProfit() {
        return todaysProfit;
    }

    public void setTodaysProfit(String todaysProfit) {
        this.todaysProfit = todaysProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(String totalAverage) {
        this.totalAverage = totalAverage;
    }

    public String getTotalMonthly() {
        return totalMonthly;
    }

    public void setTotalMonthly(String totalMonthly) {
        this.totalMonthly = totalMonthly;
    }

}
