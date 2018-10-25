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


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getRegistered_name() {
        return registered_name;
    }

    public void setRegistered_name(String registered_name) {
        this.registered_name = registered_name;
    }

    public String getU_uid() {
        return u_uid;
    }

    public void setU_uid(String u_uid) {
        this.u_uid = u_uid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature_format() {
        return signature_format;
    }

    public void setSignature_format(String signature_format) {
        this.signature_format = signature_format;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsNew() {
        return IsNew;
    }

    public void setIsNew(String isNew) {
        IsNew = isNew;
    }

    public MagazinePostCommentBody getComment_body() {
        return comment_body;
    }

    public void setComment_body(MagazinePostCommentBody comment_body) {
        this.comment_body = comment_body;
    }
}
