package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class DoctorPatientPrescription {
    private String Patient_Name = "";
    private String nid = "";
    private String Author_Name = "";
    private String Author_id = "";
    private String prec_date = "";
    private List<String> Diagnosis_of_disease = new Vector<>();

    private String title = "";
    @SerializedName("Patient ID")
    private String Patient_ID = "";
    @SerializedName("Tests_needed")
    private List<String> Tests_needed = new Vector<>();
    private String Prescription = "";
    @SerializedName("Prescription key")
    private String Prescription_key = "";

    @SerializedName("Indicators to follow-up")
    private List<Medicine_and_potion> Indicators_to_follow_up = new Vector<>();

    @SerializedName("Medicine and potion")
    private List<Medicine_and_potion> Medicine_and_potion = new Vector<>();

}
