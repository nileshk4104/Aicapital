package com.tech.aicapital.bots.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BotDataList {
    @SerializedName("rec_no")
    @Expose
    private String recNo;
    @SerializedName("bot_name")
    @Expose
    private String botName;
    @SerializedName("bot_desc")
    @Expose
    private String botDesc;
    @SerializedName("bot_min_deposit")
    @Expose
    private String botMinDeposit;
    @SerializedName("total_trade")
    @Expose
    private String totalTrade;
    @SerializedName("lot_per_trade")
    @Expose
    private String lotPerTrade;
    @SerializedName("profit")
    @Expose
    private String profit;
    @SerializedName("fees")
    @Expose
    private String fees;

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    @SerializedName("status")
    @Expose
    private String status;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotDesc() {
        return botDesc;
    }

    public void setBotDesc(String botDesc) {
        this.botDesc = botDesc;
    }

    public String getBotMinDeposit() {
        return botMinDeposit;
    }

    public void setBotMinDeposit(String botMinDeposit) {
        this.botMinDeposit = botMinDeposit;
    }

    public String getTotalTrade() {
        return totalTrade;
    }

    public void setTotalTrade(String totalTrade) {
        this.totalTrade = totalTrade;
    }

    public String getLotPerTrade() {
        return lotPerTrade;
    }

    public void setLotPerTrade(String lotPerTrade) {
        this.lotPerTrade = lotPerTrade;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
