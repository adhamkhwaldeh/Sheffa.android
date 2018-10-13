package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class PrescriptionFollowUp {
    String id = "";
    String Per = "";

    String Notes_Parsed = "";
    Indicator Indicator_tid = null;
    String Indicator_name = "";
    String How_many_times = "";
    String For_how_long = "";
    String For_how_long2 = "";


    public class Indicator {
        String tid = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPer() {
        return Per;
    }

    public void setPer(String per) {
        Per = per;
    }

    public String getNotes_Parsed() {
        return Notes_Parsed;
    }

    public void setNotes_Parsed(String notes_Parsed) {
        Notes_Parsed = notes_Parsed;
    }

    public Indicator getIndicator_tid() {
        return Indicator_tid;
    }

    public void setIndicator_tid(Indicator indicator_tid) {
        Indicator_tid = indicator_tid;
    }

    public String getIndicator_name() {
        return Indicator_name;
    }

    public void setIndicator_name(String indicator_name) {
        Indicator_name = indicator_name;
    }

    public String getHow_many_times() {
        return How_many_times;
    }

    public void setHow_many_times(String how_many_times) {
        How_many_times = how_many_times;
    }

    public String getFor_how_long() {
        return For_how_long;
    }

    public void setFor_how_long(String for_how_long) {
        For_how_long = for_how_long;
    }

    public String getFor_how_long2() {
        return For_how_long2;
    }

    public void setFor_how_long2(String for_how_long2) {
        For_how_long2 = for_how_long2;
    }
}
