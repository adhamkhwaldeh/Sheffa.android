package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Vector;


public class MagazinePost {
    private String nid = "";

    private String Likes = "";

    private List<String> Tags = new Vector<>();

    private String title = "";

    private String body = "";

    @SerializedName("image Name")
    private String image_Name = "";

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLikes() {
        return Likes;
    }

    public void setLikes(String likes) {
        Likes = likes;
    }

    public List<String> getTags() {
        return Tags;
    }

    public String getTagsString() {
        String res = "";
        if (Tags == null)
            return res;
        for (String tag : Tags) {
            res += tag + ";";
        }
        return res;
    }

    public void setTags(List<String> tags) {
        Tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_Name() {
        return image_Name;
    }

    public void setImage_Name(String image_Name) {
        this.image_Name = image_Name;
    }
}
