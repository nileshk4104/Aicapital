package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/15/2018.
 */

public class BannerDataList
{
    @SerializedName("banner_id")
    @Expose
    private String bannerId;
    @SerializedName("nanner_name")
    @Expose
    private String nannerName;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("plan_price")
    @Expose
    private String plan_price;
    @SerializedName("plan_image")
    @Expose
    private String plan_image;
    @SerializedName("plan_desc")
    @Expose
    private String plan_desc;

    public String getPlan_price() {
        return plan_price;
    }

    public void setPlan_price(String plan_price) {
        this.plan_price = plan_price;
    }

    public String getPlan_image() {
        return plan_image;
    }

    public void setPlan_image(String plan_image) {
        this.plan_image = plan_image;
    }

    public String getPlan_desc() {
        return plan_desc;
    }

    public void setPlan_desc(String plan_desc) {
        this.plan_desc = plan_desc;
    }

    public BannerDataList(String bannerId, String nannerName, String bannerImage, String status)
    {
        this.bannerId = bannerId;
        this.nannerName = nannerName;
        this.bannerImage = bannerImage;
        this.status = status;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getNannerName() {
        return nannerName;
    }

    public void setNannerName(String nannerName) {
        this.nannerName = nannerName;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
