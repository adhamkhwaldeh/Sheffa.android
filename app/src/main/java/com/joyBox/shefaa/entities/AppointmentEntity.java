package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

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

    HomeAddress patientHomeAddress;

//    @SerializedName("Home appointment")
//    String Home_appointment="";
    String homeAppointment="";

    //    @SerializedName("")
//    Shifted:The new time:[]


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

    public String getDoctor_Name() {
        return Doctor_Name;
    }

    public void setDoctor_Name(String doctor_Name) {
        Doctor_Name = doctor_Name;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
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

    public HomeAddress getPatientHomeAddress() {
        return patientHomeAddress;
    }

    public void setPatientHomeAddress(HomeAddress patientHomeAddress) {
        this.patientHomeAddress = patientHomeAddress;
    }

    public String getHomeAppointment() {
        return homeAppointment;
    }

    public void setHomeAppointment(String homeAppointment) {
        this.homeAppointment = homeAppointment;
    }
}
