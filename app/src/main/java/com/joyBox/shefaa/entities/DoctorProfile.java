package com.joyBox.shefaa.entities;

import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-10-17.
 */

public class DoctorProfile {
    private String field_phone = "";
    private String field_cost = "";
    private MapAddress field_address_map = null;
    protected DoctorAppointmentDuration field_appointment_duration = null;
    private String field_home_doctor = "";
    private String field_doctor_specialization = "";
    private List<DoctorOpenHour> field_open_hours = new Vector<>();

    public String getField_phone() {
        return field_phone;
    }

    public void setField_phone(String field_phone) {
        this.field_phone = field_phone;
    }

    public String getField_cost() {
        return field_cost;
    }

    public void setField_cost(String field_cost) {
        this.field_cost = field_cost;
    }

    public MapAddress getField_address_map() {
        return field_address_map;
    }

    public void setField_address_map(MapAddress field_address_map) {
        this.field_address_map = field_address_map;
    }

    public DoctorAppointmentDuration getField_appointment_duration() {
        return field_appointment_duration;
    }

    public void setField_appointment_duration(DoctorAppointmentDuration field_appointment_duration) {
        this.field_appointment_duration = field_appointment_duration;
    }

    public String getField_home_doctor() {
        return field_home_doctor;
    }

    public void setField_home_doctor(String field_home_doctor) {
        this.field_home_doctor = field_home_doctor;
    }

    public String getField_doctor_specialization() {
        return field_doctor_specialization;
    }

    public void setField_doctor_specialization(String field_doctor_specialization) {
        this.field_doctor_specialization = field_doctor_specialization;
    }

    public List<DoctorOpenHour> getField_open_hours() {
        return field_open_hours;
    }

    public void setField_open_hours(List<DoctorOpenHour> field_open_hours) {
        this.field_open_hours = field_open_hours;
    }
}
