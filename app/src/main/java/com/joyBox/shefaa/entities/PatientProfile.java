package com.joyBox.shefaa.entities;

/**
 * Created by Adhamkh on 2018-09-29.
 */

public class PatientProfile {

    private String field_emergency_contact = "";
    private String field_weight = "";
    private String field_height = "";
    private String field_diagnosis_of_disease = "";
    private String field_do_you_smoke = "";
    private String field_do_you_have_latex_or_any_allergies = "";
    private String field_women_only_pregnant = "";
    private String pid = "";

    public String getField_emergency_contact() {
        return field_emergency_contact;
    }

    public void setField_emergency_contact(String field_emergency_contact) {
        this.field_emergency_contact = field_emergency_contact;
    }

    public String getField_weight() {
        return field_weight;
    }

    public void setField_weight(String field_weight) {
        this.field_weight = field_weight;
    }

    public String getField_height() {
        return field_height;
    }

    public void setField_height(String field_height) {
        this.field_height = field_height;
    }

    public String getField_diagnosis_of_disease() {
        return field_diagnosis_of_disease;
    }

    public void setField_diagnosis_of_disease(String field_diagnosis_of_disease) {
        this.field_diagnosis_of_disease = field_diagnosis_of_disease;
    }

    public String getField_do_you_smoke() {
        return field_do_you_smoke;
    }

    public void setField_do_you_smoke(String field_do_you_smoke) {
        this.field_do_you_smoke = field_do_you_smoke;
    }

    public String getField_do_you_have_latex_or_any_allergies() {
        return field_do_you_have_latex_or_any_allergies;
    }

    public void setField_do_you_have_latex_or_any_allergies(String field_do_you_have_latex_or_any_allergies) {
        this.field_do_you_have_latex_or_any_allergies = field_do_you_have_latex_or_any_allergies;
    }

    public String getField_women_only_pregnant() {
        return field_women_only_pregnant;
    }

    public void setField_women_only_pregnant(String field_women_only_pregnant) {
        this.field_women_only_pregnant = field_women_only_pregnant;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
