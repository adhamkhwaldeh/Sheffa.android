package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MagazinePostComment {
    private String cid = "";
    private String pid = "";
    private String nid = "";
    private String uid = "";
    private String subject = "";
    private String hostname = "";
    private String created = "";
    private String changed = "";
    private String status = "";
    private String thread = "";
    private String name = "";
    private String mail = "";
    private String homepage = "";
    private String language = "";
    private String uuid = "";
    private String node_type = "";
    private String registered_name = "";
    private String u_uid = "";
    private String signature = "";
    private String signature_format = "";
    private String picture = "";
    @SerializedName("new")
    private String IsNew = "";

    private MagazinePostCommentBody comment_body;

}
