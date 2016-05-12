package com.example.asus.dt;

import org.json.JSONArray;

/**
 * Created by asus on 2016/5/11.
 */
public class OrderRefuelData {

    private String id;
    private String name;
    private String area;
    private String areaname;
    private String address;
    private String brandname;
    private String type;
    private String discount;
    private String exhaust;
    private String position;
    private String lon;
    private String lat;
    private String price;
    private String gastprice;
    private String fwlsmc;
    private String distance;

    public OrderRefuelData(String id, String name, String area, String areaname,
                           String address, String brandname, String type, String discount,
                           String exhaust, String position, String lon, String lat, String price,
                           String gastprice, String fwlsmc, String distance) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.areaname = areaname;
        this.address = address;
        this.brandname = brandname;
        this.type = type;
        this.discount = discount;
        this.exhaust = exhaust;
        this.position = position;
        this.lon = lon;
        this.lat = lat;
        this.price = price;
        this.gastprice = gastprice;
        this.fwlsmc = fwlsmc;
        this.distance = distance;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getExhaust() {
        return exhaust;
    }

    public void setExhaust(String exhaust) {
        this.exhaust = exhaust;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGastprice() {
        return gastprice;
    }

    public void setGastprice(String gastprice) {
        this.gastprice = gastprice;
    }

    public String getFwlsmc() {
        return fwlsmc;
    }

    public void setFwlsmc(String fwlsmc) {
        this.fwlsmc = fwlsmc;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "OrderRefuelData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", areaname='" + areaname + '\'' +
                ", address='" + address + '\'' +
                ", brandname='" + brandname + '\'' +
                ", type='" + type + '\'' +
                ", discount='" + discount + '\'' +
                ", exhaust='" + exhaust + '\'' +
                ", position='" + position + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", price='" + price + '\'' +
                ", gastprice='" + gastprice + '\'' +
                ", fwlsmc='" + fwlsmc + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
