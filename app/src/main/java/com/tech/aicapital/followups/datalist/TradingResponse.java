package com.tech.aicapital.followups.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TradingResponse {
    @SerializedName("data")
    @Expose
    private List<TradingDataList> data;
    @SerializedName("timestamp")
    @Expose
    private long timestamp;

    public List<TradingDataList> getData() {
        return data;
    }

    public void setData(List<TradingDataList> data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
