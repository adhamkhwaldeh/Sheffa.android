package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-08-22.
 */

public class DoctorAutoComplete {
    private String Doctor_id="";
    private String profile_users_1_pid="";
    private String nothing="";
    private String name="";

    public String getDoctor_id() {
        return Doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        Doctor_id = doctor_id;
    }

    public String getProfile_users_1_pid() {
        return profile_users_1_pid;
    }

    public void setProfile_users_1_pid(String profile_users_1_pid) {
        this.profile_users_1_pid = profile_users_1_pid;
    }

    public String getNothing() {
        return nothing;
    }

    public void setNothing(String nothing) {
        this.nothing = nothing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
