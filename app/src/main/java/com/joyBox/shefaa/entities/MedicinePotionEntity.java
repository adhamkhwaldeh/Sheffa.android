package com.joyBox.shefaa.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MedicinePotionEntity {

    private String id = "";

    private String Potion = "";

    private String Per = "";

    private String Notes_Parsed = "";

    @SerializedName("Medicine id")
    private String Medicine_id = "";

    @SerializedName("Medicine name")
    private String Medicine_name = "";

    //    @SerializedName("Active ingredient name")
    public List<String> Active_ingredient_name = null;

    //    @SerializedName("Alternative medicine")
    public List<String> Alternative_medicine;

    //    @SerializedName("How many times")
    private String How_many_times = "";

    //    @SerializedName("For how long")
    private String For_how_long = "1";

    //    @SerializedName("For how long2")
    private String For_how_long2 = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPotion() {
        return Potion;
    }

    public void setPotion(String potion) {
        Potion = potion;
    }

    public String getPer() {
        return Per;
    }

    public void setPer(String per) {
        Per = per;
    }

    public String getNotes_Parsed() {
        return Notes_Parsed;
    }

    public void setNotes_Parsed(String notes_Parsed) {
        Notes_Parsed = notes_Parsed;
    }

    public String getMedicine_id() {
        return Medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        Medicine_id = medicine_id;
    }

    public String getMedicine_name() {
        return Medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        Medicine_name = medicine_name;
    }

    public List<String> getActive_ingredient_name() {
        return Active_ingredient_name;
    }

    public String getActive_ingredient_name_String() {
        String res = "";
        if (Active_ingredient_name == null)
            return res;

        for (String itm : Active_ingredient_name) {
            res += itm;
            res += ";";
        }
        return res;
    }


    public void setActive_ingredient_name(List<String> active_ingredient_name) {
        Active_ingredient_name = active_ingredient_name;
    }

    public List<String> getAlternative_medicine() {
        return Alternative_medicine;
    }

    public String getAlternative_medicine_string() {
        String res = "";
        if (Alternative_medicine == null)
            return res;

        for (String itm : Alternative_medicine) {
            res += itm;
            res += ";";
        }
        return res;
    }


    public void setAlternative_medicine(List<String> alternative_medicine) {
        Alternative_medicine = alternative_medicine;
    }

    public String getHow_many_times() {
        return How_many_times;
    }

    public void setHow_many_times(String how_many_times) {
        How_many_times = how_many_times;
    }

    public String getFor_how_long() {
        return For_how_long;
    }

    public void setFor_how_long(String for_how_long) {
        For_how_long = for_how_long;
    }

    public String getFor_how_long2() {
        return For_how_long2;
    }

    public void setFor_how_long2(String for_how_long2) {
        For_how_long2 = for_how_long2;
    }
}
