package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adhamkh on 2018-10-05.
 */

public class SelfMonitorEntity {

    private String Title = "";
    private String nid = "";
    private String Unit = "";
    private String Indicator = "";

    @SerializedName("Measurement time")
    private String measurement_time="";

    @SerializedName("Value of indicator")
    private String value_of_indicator="";


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getIndicator() {
        return Indicator;
    }

    public void setIndicator(String indicator) {
        Indicator = indicator;
    }

    public String getMeasurement_time() {
        return measurement_time;
    }

    public void setMeasurement_time(String measurement_time) {
        this.measurement_time = measurement_time;
    }

    public String getValue_of_indicator() {
        return value_of_indicator;
    }

    public void setValue_of_indicator(String value_of_indicator) {
        this.value_of_indicator = value_of_indicator;
    }
}
