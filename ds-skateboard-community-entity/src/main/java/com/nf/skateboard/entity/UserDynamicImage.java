package com.nf.skateboard.entity;

public class UserDynamicImage {

    private Integer dynamicImageId;     //用户发表动态的图片表ID,自增
    private Integer userImageId;        //用户动态的图片的ID
    private String dynamicImageUrl;     //用户动态的图片url

    public Integer getDynamicImageId() {
        return dynamicImageId;
    }

    public void setDynamicImageId(Integer dynamicImageId) {
        this.dynamicImageId = dynamicImageId;
    }

    public Integer getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(Integer userImageId) {
        this.userImageId = userImageId;
    }

    public String getDynamicImageUrl() {
        return dynamicImageUrl;
    }

    public void setDynamicImageUrl(String dynamicImageUrl) {
        this.dynamicImageUrl = dynamicImageUrl;
    }

    @Override
    public String toString() {
        return "UserDynamicImage{" +
                "dynamicImageId=" + dynamicImageId +
                ", userImageId=" + userImageId +
                ", dynamicImageUrl='" + dynamicImageUrl + '\'' +
                '}';
    }
}
