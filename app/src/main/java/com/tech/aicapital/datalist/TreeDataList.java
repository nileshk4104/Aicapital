package com.tech.aicapital.datalist;

public class TreeDataList {
    private String id;
    private String name;
    private String image;
    private String count;

    public TreeDataList(String id, String name, String image, String count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
