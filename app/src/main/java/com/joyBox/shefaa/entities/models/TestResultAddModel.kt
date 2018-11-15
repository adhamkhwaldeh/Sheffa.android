package com.joyBox.shefaa.entities.models

class TestResultAddModel constructor(var title: String, var patientId: String, var doctorId: String, var fId: String) {

    var data: String = "{\n" +
            "  \"title\": \"" + title + "\",\n" +
            "  \"type\": \"test_results\",\n" +
            "  \"language\": \"und\",\n" +
            "\"field_res_patient_name\": {\"und\":\"" + patientId + "\"},\n" +
            "\"field_res_doctor_name\": {\"und\":\"" + doctorId + "\"},\n" +
            "  \"field_results\": {\n" +
            "    \"und\": [\n" +
            "      {\n" +
            "        \"fid\": \"" + fId + "\"     \n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"field_test_name\": {\n" +
            "    \"und\":  \"1577\"\n" +
            "\n" +
            "  }\n" +
            "}\n"
}