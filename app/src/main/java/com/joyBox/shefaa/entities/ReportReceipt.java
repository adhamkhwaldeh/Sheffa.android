package com.joyBox.shefaa.entities;


import com.google.gson.annotations.SerializedName;

public class ReportReceipt {
    public String nid = "";
    public String node_field_data_field_appointment_number_nid = "";

    @SerializedName("Receipt title")
    public String Receipt_title = "";

    @SerializedName("Patient Name")
    public String Patient_Name = "";
    @SerializedName("Appointment start time")
    public String Appointment_start_time = "";

    //Shifted: The new time
    public String Shifted_The_new_time;//": [],
    public String Receipt_value = "";
    public String Receipt_type = "";//": [],
    public String Receipt_Date = "";//": "2018-01-09 13:10:00"
    public String Notes = "";//": [],

}
