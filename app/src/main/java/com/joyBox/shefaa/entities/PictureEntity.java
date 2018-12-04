package com.joyBox.shefaa.entities;

import android.util.Log;

/**
 * Created by Adhamkh on 2018-09-22.
 */

public class PictureEntity {
    public String fid = "";
    public String uid = "";
    public String filename = "";
    public String uri = "";
    public String filemime = "";
    public String filesize = "";
    public String status = "";
    public String timestamp = "";
    public String uuid = "";
    public String url = "";

    public String getPictureUrl() {
        String purl = "";
        try {
            purl = filename.split("\"")[1];
        } catch (Exception ex) {
            Log.v("expicture", ex.getMessage());
        }
        return purl;
    }

}
