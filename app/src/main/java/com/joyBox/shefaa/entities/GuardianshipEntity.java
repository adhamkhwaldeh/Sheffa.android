package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adhamkh on 2018-10-05.
 */

public class GuardianshipEntity {

    private String Uid = "";

    @SerializedName("Guardian name")
    private String Guardian_name = "";


    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getGuardian_name() {
        return Guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        Guardian_name = guardian_name;
    }
}
