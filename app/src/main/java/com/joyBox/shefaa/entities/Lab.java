package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-10-25.
 */

public class Lab {
    private String profile_users_pid = "";
    private List<String> Phone = new Vector<>();
    private List<String> Fax = new Vector<>();
    private Rating Rate;
    private HomeAddress Address;

    private String Uid = "";
    private String Picture = "";
    private String name = "";

    @SerializedName("Laboratory Name")
    private String Laboratory_Name = "";

    @SerializedName("Lab open hours")
    private List<DoctorOpenHour> Lab_open_hours = new Vector<>();


    public String getProfile_users_pid() {
        return profile_users_pid;
    }

    public void setProfile_users_pid(String profile_users_pid) {
        this.profile_users_pid = profile_users_pid;
    }

    public List<String> getPhone() {
        return Phone;
    }

    public void setPhone(List<String> phone) {
        Phone = phone;
    }

    public List<String> getFax() {
        return Fax;
    }

    public void setFax(List<String> fax) {
        Fax = fax;
    }

    public Rating getRate() {
        return Rate;
    }

    public void setRate(Rating rate) {
        Rate = rate;
    }

    public HomeAddress getAddress() {
        return Address;
    }

    public void setAddress(HomeAddress address) {
        Address = address;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaboratory_Name() {
        return Laboratory_Name;
    }

    public void setLaboratory_Name(String laboratory_Name) {
        Laboratory_Name = laboratory_Name;
    }

    public List<DoctorOpenHour> getLab_open_hours() {
        return Lab_open_hours;
    }

    public void setLab_open_hours(List<DoctorOpenHour> lab_open_hours) {
        Lab_open_hours = lab_open_hours;
    }
}
