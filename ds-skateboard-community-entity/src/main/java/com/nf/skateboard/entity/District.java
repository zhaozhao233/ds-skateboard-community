package com.nf.skateboard.entity;

//地址表
public class District {
    private Integer id;
    private Integer pid;
    private String district;
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", pid=" + pid +
                ", district='" + district + '\'' +
                ", level=" + level +
                '}';
    }
}
