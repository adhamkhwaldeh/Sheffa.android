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
    @SerializedName("Tests needed")
    private List<String> Tests_needed = new Vector<>();
    private String Prescription = "";
    @SerializedName("Prescription key")
    private String Prescription_key = "";

    @SerializedName("Indicators to follow-up")
    private List<Medicine_and_potion> Indicators_to_follow_up = new Vector<>();

    @SerializedName("Medicine and potion")
    private List<Medicine_and_potion> Medicine_and_potion = new Vector<>();

    public String getPatient_Name() {
        return Patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }

    public String getAuthor_id() {
        return Author_id;
    }

    public void setAuthor_id(String author_id) {
        Author_id = author_id;
    }

    public String getPrec_date() {
        return prec_date;
    }

    public void setPrec_date(String prec_date) {
        this.prec_date = prec_date;
    }

    public List<String> getDiagnosis_of_disease() {
        return Diagnosis_of_disease;
    }

    public void setDiagnosis_of_disease(List<String> diagnosis_of_disease) {
        Diagnosis_of_disease = diagnosis_of_disease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPatient_ID() {
        return Patient_ID;
    }

    public void setPatient_ID(String patient_ID) {
        Patient_ID = patient_ID;
    }

    public List<String> getTests_needed() {
        return Tests_needed;
    }

    public void setTests_needed(List<String> tests_needed) {
        Tests_needed = tests_needed;
    }

    public String getPrescription() {
        return Prescription;
    }

    public void setPrescription(String prescription) {
        Prescription = prescription;
    }

    public String getPrescription_key() {
        return Prescription_key;
    }

    public void setPrescription_key(String prescription_key) {
        Prescription_key = prescription_key;
    }

    public List<com.joyBox.shefaa.entities.Medicine_and_potion> getIndicators_to_follow_up() {
        return Indicators_to_follow_up;
    }

    public void setIndicators_to_follow_up(List<com.joyBox.shefaa.entities.Medicine_and_potion> indicators_to_follow_up) {
        Indicators_to_follow_up = indicators_to_follow_up;
    }

    public List<com.joyBox.shefaa.entities.Medicine_and_potion> getMedicine_and_potion() {
        return Medicine_and_potion;
    }

    public void setMedicine_and_potion(List<com.joyBox.shefaa.entities.Medicine_and_potion> medicine_and_potion) {
        Medicine_and_potion = medicine_and_potion;
    }
}
