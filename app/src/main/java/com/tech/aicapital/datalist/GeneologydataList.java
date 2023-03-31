package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneologydataList {
    @SerializedName("levelname")
    @Expose
    private String levelname;
    @SerializedName("userlist")
    @Expose
    private List<GeneoogyUserlist> userlist = null;
    private int amt=0;

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }
    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public List<GeneoogyUserlist> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<GeneoogyUserlist> userlist) {
        this.userlist = userlist;
    }


}
