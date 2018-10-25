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

}
