package com.joyBox.shefaa.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adhamkh on 2017-07-28.
 */

public class Prescription {
    public String Patient_Name;
    public String nid;
    public String Author_Name;
    public String Author_id;
    public String prec_date;

    public List<String> Diagnosis_of_disease;

    public String title;

    @SerializedName("Patient ID")
    @Expose
    public String Patient_ID;// Patient ID

    @SerializedName("Tests needed")
    @Expose
    public List<String> testNeededs;

    @SerializedName("Prescription code")
    public String Prescription_code;


    @SerializedName("Prescription key")
    public String Prescription_key;

    @SerializedName("Indicators to follow-up")
    public List<Medicine_and_potion> Indicators_to_follow_up;

    @SerializedName("Medicine and potion")
    public List<Medicine_and_potion> Medicine_and_potion;


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

    public List<String> getTestNeededs() {
        return testNeededs;
    }

    public void setTestNeededs(List<String> testNeededs) {
        this.testNeededs = testNeededs;
    }

    public String getDiagnosisAsString() {
        StringBuilder result = new StringBuilder();
        if (getDiagnosis_of_disease() != null) {
            for (String diagnosis : Diagnosis_of_disease) {
                result.append(", ").append(diagnosis);
            }
        }
        return result.toString();
    }

}