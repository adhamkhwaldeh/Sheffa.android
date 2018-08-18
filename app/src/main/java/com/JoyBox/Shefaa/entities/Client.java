package com.joyBox.shefaa.entities;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by windows 8.1 on 2017-04-16.
 */

public class Client {

    @SerializedName("sessid")
    @Expose
    private String sessid;
    @SerializedName("session_name")
    @Expose
    private String sessionName;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

}
