package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorDataList {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jolo_code")
    @Expose
    private String joloCode;
    @SerializedName("op_image")
    @Expose
    private String opImage;
    @SerializedName("status")
    @Expose
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoloCode() {
        return joloCode;
    }

    public void setJoloCode(String joloCode) {
        this.joloCode = joloCode;
    }

    public String getOpImage() {
        return opImage;
    }

    public void setOpImage(String opImage) {
        this.opImage = opImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
