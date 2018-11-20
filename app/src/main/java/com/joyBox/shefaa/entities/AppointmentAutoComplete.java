package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class AppointmentAutoComplete {

    public String id = "";
    private String title = "";

    public AppointmentAutoComplete() {
    }

    public AppointmentAutoComplete(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
