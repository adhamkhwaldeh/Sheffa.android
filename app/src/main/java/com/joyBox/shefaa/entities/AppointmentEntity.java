package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adhamkh on 2018-08-19.
 */

public class AppointmentEntity {

    @SerializedName("nid")
    String nid = "";

    @SerializedName("Approved")
    String Approved = "0";

    @SerializedName("title")
    String title = "";

    @SerializedName("Doctor Name")
    String Doctor_Name = "";

    //    @SerializedName("Doctor ID")
    //    Doctor ID:[],

    @SerializedName("Patient Name")
    String Patient_Name = "";

    //    @SerializedName("Patient ID")
    //    Patient ID:[],

    @SerializedName("Appointment Date")
    String Appointment_Date = "";

    @SerializedName("urgent appointment")
    String urgent_appointment = "";

    @SerializedName("Appointment start time")
    String Appointment_start_time = "";

    @SerializedName("Urgent appointment cause")
    String Urgent_appointment_cause = "";

//    @SerializedName("Patient home address")
//    String Patient_home_address="";

//    @SerializedName("Home appointment")
//    String Home_appointment="";

//    @SerializedName("")
//    Shifted:The new time:[]
}
