package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-09-29.
 */

public class MainProfile {

    private String newest_email = "";
    private String initial_email = "";
    private String field_first_name = "";
    private String field_last_name = "";
    private String field_gender = "";
    private String pid = "";

    public String getNewest_email() {
        return newest_email;
    }

    public void setNewest_email(String newest_email) {
        this.newest_email = newest_email;
    }

    public String getInitial_email() {
        return initial_email;
    }

    public void setInitial_email(String initial_email) {
        this.initial_email = initial_email;
    }

    public String getField_first_name() {
        return field_first_name;
    }

    public void setField_first_name(String field_first_name) {
        this.field_first_name = field_first_name;
    }

    public String getField_last_name() {
        return field_last_name;
    }

    public void setField_last_name(String field_last_name) {
        this.field_last_name = field_last_name;
    }

    public String getField_gender() {
        return field_gender;
    }

    public void setField_gender(String field_gender) {
        this.field_gender = field_gender;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
