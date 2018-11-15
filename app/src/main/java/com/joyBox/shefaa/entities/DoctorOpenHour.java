package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-10-17.
 */

public class DoctorOpenHour {
    private String day = "";
    private String starthours = "";
    private String endhours = "";

    public DoctorOpenHour() {
    }

    public DoctorOpenHour(String day, String starthours, String endhours) {
        this.day = day;
        this.starthours = starthours;
        this.endhours = endhours;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStarthours() {
        return starthours;
    }

    public void setStarthours(String starthours) {
        this.starthours = starthours;
    }

    public String getEndhours() {
        return endhours;
    }

    public void setEndhours(String endhours) {
        this.endhours = endhours;
    }
}
