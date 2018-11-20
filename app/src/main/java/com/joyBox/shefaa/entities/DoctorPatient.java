package com.joyBox.shefaa.entities;


public class DoctorPatient {
    private String Picture = "";//"<img src=\"http://shefaaonline.net/sites/default/files/styles/thumbnail/public/pictures/picture-10-1505650760.jpg?itok=m8eXoiTG\" alt=\"\" />",
    private String requester_id = "";
    private String Uid = "";

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(String requester_id) {
        this.requester_id = requester_id;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
