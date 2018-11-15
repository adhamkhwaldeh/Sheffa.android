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

//    @SerializedName("Patient home address")
    private MapAddress Patient_home_address;

    @SerializedName("Home appointment")
    private String Home_appointment = "";

//                    "Shifted: The new time":[]


    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getApproved() {
        return Approved;
    }

    public void setApproved(String approved) {
        Approved = approved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoctor_ID() {
        return Doctor_ID;
    }

    public void setDoctor_ID(String doctor_ID) {
        Doctor_ID = doctor_ID;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
    }

    public String getPatient_ID() {
        return Patient_ID;
    }

    public void setPatient_ID(String patient_ID) {
        Patient_ID = patient_ID;
    }

    public String getAppointment_Date() {
        return Appointment_Date;
    }

    public void setAppointment_Date(String appointment_Date) {
        Appointment_Date = appointment_Date;
    }

    public String getUrgent_appointment() {
        return urgent_appointment;
    }

    public void setUrgent_appointment(String urgent_appointment) {
        this.urgent_appointment = urgent_appointment;
    }

    public String getAppointment_start_time() {
        return Appointment_start_time;
    }

    public void setAppointment_start_time(String appointment_start_time) {
        Appointment_start_time = appointment_start_time;
    }

    public String getUrgent_appointment_cause() {
        return Urgent_appointment_cause;
    }

    public void setUrgent_appointment_cause(String urgent_appointment_cause) {
        Urgent_appointment_cause = urgent_appointment_cause;
    }

    public MapAddress getPatient_home_address() {
        return Patient_home_address;
    }

    public void setPatient_home_address(MapAddress patient_home_address) {
        Patient_home_address = patient_home_address;
    }

    public String getHome_appointment() {
        return Home_appointment;
    }

    public void setHome_appointment(String home_appointment) {
        Home_appointment = home_appointment;
    }
}
