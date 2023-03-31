package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTradeDataList {
    @SerializedName("rec_no")
    @Expose
    private String recNo;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("entity_name")
    @Expose
    private String entityName;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("open_pos")
    @Expose
    private String openPos;
    @SerializedName("close_pos")
    @Expose
    private String closePos;
    @SerializedName("lot_size")
    @Expose
    private String lotSize;
    @SerializedName("plgain")
    @Expose
    private String plgain;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getOpenPos() {
        return openPos;
    }

    public void setOpenPos(String openPos) {
        this.openPos = openPos;
    }

    public String getClosePos() {
        return closePos;
    }

    public void setClosePos(String closePos) {
        this.closePos = closePos;
    }

    public String getLotSize() {
        return lotSize;
    }

    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
    }

    public String getPlgain() {
        return plgain;
    }

    public void setPlgain(String plgain) {
        this.plgain = plgain;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
