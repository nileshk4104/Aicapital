package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuDataList {
    @SerializedName("menu_id")
    @Expose
    private String menuId;

    @SerializedName("menu_name")
    @Expose
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @SerializedName("submenu_details")
    @Expose
    private List<SubmenuDetailList> submenuDetails = null;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<SubmenuDetailList> getSubmenuDetails() {
        return submenuDetails;
    }

    public void setSubmenuDetails(List<SubmenuDetailList> submenuDetails) {
        this.submenuDetails = submenuDetails;
    }
}
