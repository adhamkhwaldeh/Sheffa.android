package com.joyBox.shefaa.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pharmacy {

    @SerializedName("Pharmatics_Name")
    @Expose
    public String name;

    @SerializedName("profile_users_1_pid")
    @Expose
    public String Pid;

    @SerializedName("nothing")
    @Expose
    public String nothing;

    @SerializedName("Pharmacy_open_hours")
    @Expose
    public List<Clinic_open_hours> Pharmacy_open_hours;

    @SerializedName("Rate")
    @Expose
    public Rating rate;

    @SerializedName("City")
    @Expose
    public String City;

    @SerializedName("Country")
    @Expose
    public String Country;

    @SerializedName("Latitude")
    @Expose
    public String Latitude;

    @SerializedName("Longitude")
    @Expose
    public String Longitude;

    @SerializedName("Street")
    @Expose
    public String Street;

    @SerializedName("Pharmacy_name")
    @Expose
    public String Pharmacy_name;

    @SerializedName("Medicine_name")
    @Expose
    public String Medicine_name;

    @SerializedName("Distance / Proximity")
    @Expose
    public String Distance_Proximity;

    @SerializedName("Uid")
    @Expose
    public String Uid;


    @SerializedName("Picture")
    @Expose
    public String Picture;

    public String getPicture() {
        return Picture;
    }

    public String getPictureUrl() {
        String purl = "";
        try {
            purl = Picture.split("src")[1];
            purl = purl.split(" ")[0];
            purl = purl.substring(2, purl.length() - 1);
        } catch (Exception ex) {

        }
        return purl;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRate() {
        return rate;
    }

    public void setRate(Rating rate) {
        this.rate = rate;
    }

    public List<Clinic_open_hours> getPharmacy_open_hours() {
        return Pharmacy_open_hours;
    }

    public void setPharmacy_open_hours(List<Clinic_open_hours> pharmacy_open_hours) {
        Pharmacy_open_hours = pharmacy_open_hours;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getPharmacy_name() {
        return Pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        Pharmacy_name = pharmacy_name;
    }

    public String getMedicine_name() {
        return Medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        Medicine_name = medicine_name;
    }

    public String getDistance_Proximity() {
        return Distance_Proximity;
    }

    public void setDistance_Proximity(String distance_Proximity) {
        Distance_Proximity = distance_Proximity;
    }

    public String getFullAddress() {
        return this.Country + "," + this.City;
    }

    public float getRating() {
        Float res = 0.00f;
        if (rate != null) {
            Float cnt = Float.valueOf(rate.count);
            Float avg = Float.valueOf(rate.average);
            if (cnt > 0)
                res = avg / 20.00f;
        }
        return res;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getNothing() {
        return nothing;
    }

    public void setNothing(String nothing) {
        this.nothing = nothing;
    }
}
