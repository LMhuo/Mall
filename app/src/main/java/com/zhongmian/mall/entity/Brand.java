package com.zhongmian.mall.entity;

/**
 * Created by L on 2016/3/1 0001.
 */
public class Brand implements Comparable {
    private String brandShowFirstName;
    private String enName;
    private String id;
    private String logo;
    private String name;
    private String type;


    public Brand() {
    }

    public Brand(String brandShowFirstName, String enName, String id, String logo, String name, String type) {
        this.brandShowFirstName = brandShowFirstName;
        this.enName = enName;
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.type = type;
    }

    public Brand(String brandShowFirstName,String enName,String type){
        this.brandShowFirstName = brandShowFirstName;
        this.enName = enName;
        this.type = type;

    }

    public String getBrandShowFirstName() {
        return brandShowFirstName;
    }

    public void setBrandShowFirstName(String brandShowFirstName) {
        this.brandShowFirstName = brandShowFirstName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandShowFirstName='" + brandShowFirstName + '\'' +
                ", enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object another) {
        Brand other = (Brand) another;
        return this.enName.compareTo(other.enName);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
