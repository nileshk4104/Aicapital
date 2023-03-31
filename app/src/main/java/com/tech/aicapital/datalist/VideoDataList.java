package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoDataList {

    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("linkurl")
    @Expose
    private String linkurl;


    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("video_image")
    @Expose
    private String videoImage;

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    @SerializedName("video_name")
    @Expose
    private String videoName;
    @SerializedName("video_desc")
    @Expose
    private String videoDesc;
    @SerializedName("youtube_id")
    @Expose
    private String youtubeId;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLinkurl() {
        return linkurl;
    }
    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }


    public String getVideoId() {
        return videoId;
    }
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDesc() {
        return videoDesc;
    }
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getYoutubeId() {
        return youtubeId;
    }
    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
