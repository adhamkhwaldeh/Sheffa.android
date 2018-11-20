package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class TestResultEntity {

    private String nid = "";

    private String Results = "";

    //    @SerializedName("Test name")
    private String Test_name;

    //    @SerializedName("Lab name")
    private String Lab_name;

    //    @SerializedName("Patient name")
    private String Patient_name;

    //    @SerializedName("Doctor name")
    private String Doctor_name;

//    @SerializedName("Files details")
    private List<TestResultFilesDetails> filesDetails;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getResults() {
        return Results;
    }

    public void setResults(String results) {
        Results = results;
    }

    public String getTest_name() {
        return Test_name;
    }

    public void setTest_name(String test_name) {
        Test_name = test_name;
    }

    public String getLab_name() {
        return Lab_name;
    }

    public void setLab_name(String lab_name) {
        Lab_name = lab_name;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        Doctor_name = doctor_name;
    }

    public List<TestResultFilesDetails> getFilesDetails() {
        return filesDetails;
    }

    public void setFilesDetails(List<TestResultFilesDetails> filesDetails) {
        this.filesDetails = filesDetails;
    }
}
