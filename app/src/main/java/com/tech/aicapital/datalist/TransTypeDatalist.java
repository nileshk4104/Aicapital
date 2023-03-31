package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransTypeDatalist {

    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("type_nname")
    @Expose
    private String typeNname;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeNname() {
        return typeNname;
    }

    public void setTypeNname(String typeNname) {
        this.typeNname = typeNname;
    }
}
