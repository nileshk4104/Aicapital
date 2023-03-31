package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDataList {

    @SerializedName("isSubcat")
    @Expose
    private String isSubcat;

    public String getIsSubcat() {
        return isSubcat;
    }

    public void setIsSubcat(String isSubcat) {
        this.isSubcat = isSubcat;
    }

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
