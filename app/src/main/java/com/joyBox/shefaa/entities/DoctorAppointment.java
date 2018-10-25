package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adhamkh on 2018-10-17.
 */

public class DoctorAppointment {

    private String nid = "";
    private String Approved = "";
    private String title = "";

    @SerializedName("Doctor ID")
    private String Doctor_ID = "";

    @SerializedName("Patient Name")
    private String Patient_Name = "";

    @SerializedName("Patient ID")
    private String Patient_ID = "";

    @SerializedName("Appointment Date")
    private String Appointment_Date = "";

    @SerializedName("urgent appointment")
    private String urgent_appointment = "";

    @SerializedName("Appointment start time")
    private String Appointment_start_time = "";

    @SerializedName("Urgent appointment cause")
    private String Urgent_appointment_cause = "";

    @SerializedName("Patient home address")
    private MapAddress Patient_home_address;

    @SerializedName("Home appointment")
    private String Home_appointment = "";

//                    "Shifted: The new time":[]

}
