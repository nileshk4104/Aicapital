package com.tech.aicapital.cart.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartDataList {
    @SerializedName("rec_no")
    @Expose
    private String recNo;
    @SerializedName("cart_no")
    @Expose
    private String cartNo;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_mrp")
    @Expose
    private String productMrp;
    @SerializedName("product_unit")
    @Expose
    private String productUnit;
    @SerializedName("product_unit_value")
    @Expose
    private String productUnitValue;
    @SerializedName("product_qty")
    @Expose
    private String productQty;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;


    public CartDataList(String cartNo, String productId, String productMrp, String productUnit, String productUnitValue,
                        String productQty,String discount, String userId,String productName,String productImage) {
        this.recNo = recNo;
        this.cartNo = cartNo;
        this.productId = productId;
        this.productMrp = productMrp;
        this.productUnit = productUnit;
        this.productUnitValue = productUnitValue;
        this.productQty = productQty;
        this.discount = discount;
        this.userId = userId;
        this.productName = productName;
        this.productImage = productImage;
    }

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getCartNo() {
        return cartNo;
    }

    public void setCartNo(String cartNo) {
        this.cartNo = cartNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductUnitValue() {
        return productUnitValue;
    }

    public void setProductUnitValue(String productUnitValue) {
        this.productUnitValue = productUnitValue;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
