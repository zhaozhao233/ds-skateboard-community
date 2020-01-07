package com.nf.skateboard.entity;

//用户地址表
public class UserAddr {

    private Integer userId;     //登陆表中的ID
    private Integer userAddrId; //地址ID（自增）
    private Integer province;   //地区表中省份ID
    private Integer city;       //地区表中城市ID
    private Integer district;   //地区表中区ID
    private String address;     //具体的门牌号

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(Integer userAddrId) {
        this.userAddrId = userAddrId;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserAddr{" +
                "userId=" + userId +
                ", userAddrId=" + userAddrId +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", address='" + address + '\'' +
                '}';
    }
}
