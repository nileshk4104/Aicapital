package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDataList {
    @SerializedName("isQuotable")
    @Expose
    private String isQuotable;

    public String getIsQuotable() {
        return isQuotable;
    }

    public void setIsQuotable(String isQuotable) {
        this.isQuotable = isQuotable;
    }

    @SerializedName("stock")
    @Expose
    private String stock;

    @SerializedName("rec_no")
    @Expose
    private String recNo;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("brand_id")
    @Expose
    private String brandId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("shop_name")
    @Expose
    private String shop_name;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("subcategory_id")
    @Expose
    private String subcategoryId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_mrp")
    @Expose
    private String productMrp;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("per_unit")
    @Expose
    private String perUnit;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("status")
    @Expose
    private String status;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    private String quantity="0";

//    public ProductDataList(String productId, String brandId, String categoryId, String subcategoryId, String productName,
//                           String productImage, String productMrp, String discount, String perUnit, String unit, String status,String quantity) {
//        this.productId = productId;
//        this.brandId = brandId;
//        this.categoryId = categoryId;
//        this.subcategoryId = subcategoryId;
//        this.productName = productName;
//        this.productImage = productImage;
//        this.productMrp = productMrp;
//        this.discount = discount;
//        this.perUnit = perUnit;
//        this.unit = unit;
//        this.status = status;
//        this.quantity = quantity;
//    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(String perUnit) {
        this.perUnit = perUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
