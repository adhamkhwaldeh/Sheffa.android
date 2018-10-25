package com.joyBox.shefaa.entities.models

import com.joyBox.shefaa.enums.AppointmentShiftType

/**
 * Created by Adhamkh on 2018-10-18.
 */
class AppointmentShiftModel constructor(val date: String, val minutes: String,
                                        val hours: String, appointmentShiftType: AppointmentShiftType,
                                        val startHour: String, val startMinute: String,
                                        val clinicCloseHour: String, val clinicCloseMinute: String) {

    var data = "{\n" +
            "  \"type\": \"shift_appointments\",\n" +
            "  \"language\": \"und\",\n" +
            "  \"field_appointment_day\": {\"und\":\n" +
            "[{\"value\":{\"date\":\"" + date + "\"}}]},\n" +
            "\n" +
            "  \"field_shifting_amount\": {  \"und\":  \"" + minutes + "\" },\n" +
            "  \"field_select_shifting_amount\": { \"und\": \"" + hours + "\" },\n" +
            "  \"field_shifting_to\": { \"und\": \"" + appointmentShiftType.type + "\"  },\n" +
            "  \n" +
            "\n" +
            "\n" +
            "\n" +
            "    \"field_shifting_time_starting\": {\n" +
            "    \"und\": [\n" +
            "      {\n" +
            "      \t\"hour\":\"" + startHour + "\",\n" +
            "      \t\"minute\":\"" + startMinute + "\"\n" +
            "      \t\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "\n" +
            "  \"field_closing_hour\": {\n" +
            "    \"und\": [\n" +
            "      {\n" +
            "      \t\"hour\":\"" + clinicCloseHour + "\",\n" +
            "      \t\"minute\":\"" + clinicCloseMinute + "\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n"
}