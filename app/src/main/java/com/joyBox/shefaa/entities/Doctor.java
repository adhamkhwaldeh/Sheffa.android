package com.joyBox.shefaa.entities;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Vector;

/**
 * Created by windows 8.1 on 2017-04-17.
 */

public class Doctor {

    @SerializedName("Doctor_id")
    @Expose
    public String doctor_id;

    @SerializedName("Doctor_specialization")
    @Expose
    public List<String> doctor_specialization;

    public String Picture;

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getPictureUrl() {
        String purl = "";
        try {
            purl = Picture.split("\"")[1];
        } catch (Exception ex) {
            Log.v("expicture", ex.getMessage());
        }
        return purl;
    }

    public String Costs;

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

    @SerializedName("Clinic_open_hours")
    @Expose
    public List<Clinic_open_hours> clinic_open_hourses;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("Location name")
    @Expose
    public String Location_name;

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setdoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public List<String> getDoctor_specialization() {
        if (doctor_specialization == null)
            doctor_specialization = new Vector<>();
        return doctor_specialization;
    }

    public void setDoctor_specialization(List<String> doctor_specialization) {
        this.doctor_specialization = doctor_specialization;
    }

    public String getCosts() {
        return Costs;
    }

    public void setCosts(String costs) {
        Costs = costs;
    }

    public Rating getRate() {
        return rate;
    }

    public void setRate(Rating rate) {
        this.rate = rate;
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

    public List<Clinic_open_hours> getClinic_open_hourses() {
        return clinic_open_hourses;
    }

    public void setClinic_open_hourses(List<Clinic_open_hours> clinic_open_hourses) {
        this.clinic_open_hourses = clinic_open_hourses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation_name() {
        return Location_name;
    }

    public void setLocation_name(String location_name) {
        Location_name = location_name;
    }

    public String getFullAddress() {
        return this.Country + "," + this.City;
    }

    public Float getRating() {
        Float res = 0.00f;
        if (rate != null) {
            Float cnt = Float.valueOf(rate.count);
            Float avg = Float.valueOf(rate.average);
            if (cnt > 0)
                res = avg / 20.00f;
        }
        return res;
    }

    public Float getRatingClient() {
        Float res = 0.00f;
        if (rate != null) {
            Float cnt = Float.valueOf(rate.count);
            Float avg = Float.valueOf(rate.getUser());
            if (cnt > 0)
                res = avg / 20.00f;
        }
        return res;
    }


    public String getdoctorSpecializtion() {
        String special = "";
        if (doctor_specialization == null)
            return special;
        boolean first = true;
        for (String s : doctor_specialization) {
            if (!first)
                special += ",";
            first = false;
            special += s;
        }
        return special;
    }
}

