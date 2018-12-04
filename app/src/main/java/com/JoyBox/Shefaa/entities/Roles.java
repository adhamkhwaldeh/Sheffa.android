package com.joyBox.shefaa.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roles {

    @SerializedName("2")
    @Expose
    private String IS_authenticated;

    @SerializedName("5")
    @Expose
    private String Is_administrator;

    @SerializedName("6")
    @Expose
    private String IS_Doctor;

    @SerializedName("7")
    @Expose
    private String IS_Patient;

    @SerializedName("8")
    @Expose
    private String IS_Pharmacy;

    @SerializedName("12")
    @Expose
    private String IS_Laboratory;

    @SerializedName("13")
    @Expose
    private String IS_support;

    public String getIS_authenticated() {
        return IS_authenticated;
    }

    public void setIS_authenticated(String IS_authenticated) {
        this.IS_authenticated = IS_authenticated;
    }

    public String getIs_administrator() {
        return Is_administrator;
    }

    public void setIs_administrator(String is_administrator) {
        Is_administrator = is_administrator;
    }

    public String getIS_Doctor() {
        return IS_Doctor;
    }

    public void setIS_Doctor(String IS_Doctor) {
        this.IS_Doctor = IS_Doctor;
    }

    public String getIS_Patient() {
        return IS_Patient;
    }

    public void setIS_Patient(String IS_Patient) {
        this.IS_Patient = IS_Patient;
    }

    public String getIS_Pharmacy() {
        return IS_Pharmacy;
    }

    public void setIS_Pharmacy(String IS_Pharmacy) {
        this.IS_Pharmacy = IS_Pharmacy;
    }

    public String getIS_Laboratory() {
        return IS_Laboratory;
    }

    public void setIS_Laboratory(String IS_Laboratory) {
        this.IS_Laboratory = IS_Laboratory;
    }

    public String getIS_support() {
        return IS_support;
    }

    public void setIS_support(String IS_support) {
        this.IS_support = IS_support;
    }
}