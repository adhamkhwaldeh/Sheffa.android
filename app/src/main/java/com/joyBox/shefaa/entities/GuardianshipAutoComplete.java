package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adhamkh on 2018-11-04.
 */

public class GuardianshipAutoComplete {
    private String username;
    private String profile_users_pid;
    private String uid;

    @SerializedName("First Name")
    private String First_Name;

    @SerializedName("Last Name")
    private String Last_Name;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_users_pid() {
        return profile_users_pid;
    }

    public void setProfile_users_pid(String profile_users_pid) {
        this.profile_users_pid = profile_users_pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }
}
