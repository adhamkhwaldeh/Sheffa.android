package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class ReportExpense {
    private String nid = "";
    private String Notes = "";
    private String title = "";
    private String How_much = "";
    private String Paying_date = "";
    private String Type_of_payments = "";

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHow_much() {
        return How_much;
    }

    public void setHow_much(String how_much) {
        How_much = how_much;
    }

    public String getPaying_date() {
        return Paying_date;
    }

    public void setPaying_date(String paying_date) {
        Paying_date = paying_date;
    }

    public String getType_of_payments() {
        return Type_of_payments;
    }

    public void setType_of_payments(String type_of_payments) {
        Type_of_payments = type_of_payments;
    }
}
