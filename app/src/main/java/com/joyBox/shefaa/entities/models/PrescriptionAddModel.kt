package com.joyBox.shefaa.entities.models

/**
 * Created by Adhamkh on 2018-10-20.
 */
class PrescriptionAddModel(var desc: String, var specialist: String) {

    var data: String

    init {
        data = "{\n" +
                "  \"type\": \"prescription\",\n" +
                "  \"body\": {\n" +
                "    \"und\":\"" + desc + "\"\n" +
                "  },\n" +
                "  \"field_diagnosis_of_disease\": {\n" +
                "    \"und\": \"" + specialist + "\"\n" +
                "  },\n" +
                "  \"field_medicine_and_potion\": {\n" +
                "\t\"und\": \n" +
                "\t[\n"

        for (i in 0..4) {
            data +=
                    "\t   {\n" +
                    "\t   \"field_perscription_medicine\": {\"und\":  [{\"target_id\": \"setamol (4)\"}]},\n" +
                    "\t\t\"field_potion\": {\"und\":  \"3 mg\"},\n" +
                    "\t\t\"field_notes\": {\"und\": \"test\"},\n" +
                    "\t\t\"field_how_many_times\": {\"und\":  \"3\"},\n" +
                    "\t\t\"field_per\": {\"und\": \"1\"},\n" +
                    "\t\t\"field_for_how_long\": {\"und\": \"3\"},\n" +
                    "\t\t\"field_for_how_long2\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_active_ingredient_name\": {\"und\":  \"Paracetamol\"},\n" +
                    "\t\t\"field_suggest_alternative_medici\": {\"und\":  \"0\"},\n" +
                    "\t\t\"field_alternative_medicine\": {\"und\":  \"setamol (4)\"}\n" +
                    "      },\n" +
                    "      {\n" +
                    "\t    \"field_perscription_medicine\": {\"und\":  [{\"target_id\": \"setamol (4)\"}]},\n" +
                    "\t\t\"field_potion\": {\"und\":  \"3 mg\"},\n" +
                    "\t\t\"field_notes\": {\"und\": \"test\"},\n" +
                    "\t\t\"field_how_many_times\": {\"und\":  \"3\"},\n" +
                    "\t\t\"field_per\": {\"und\": \"1\"},\n" +
                    "\t\t\"field_for_how_long\": {\"und\": \"3\"},\n" +
                    "\t\t\"field_for_how_long2\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_active_ingredient_name\": {\"und\":  \"Paracetamol\"},\n" +
                    "\t\t\"field_suggest_alternative_medici\": {\"und\":  \"0\"},\n" +
                    "\t\t\"field_alternative_medicine\": {\"und\":  \"setamol (4)\"}\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"field_patient_name\": {\"und\":\"2\"},\n" +
                    "  \"field_tests_needed\": {\"und\": \"161\"},\n" +
                    "  \"field_indicators_to_follow_up\": {\n" +
                    "\t\"und\": \n" +
                    "\t[ \n" +
                    "\t\t{\n" +
                    "        \"field_how_many_times\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_indicator\": {\"und\":  \"Pressure\"},\n" +
                    "\t\t\"field_per\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_notes\": { \"und\": \"test\"},\n" +
                    "\t\t\"field_for_how_long\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_for_how_long2\": {\"und\": \"30\"}\n" +
                    "       },\n" +
                    "       {\n" +
                    "        \"field_how_many_times\": {\"und\":  \"2\"},\n" +
                    "\t\t\"field_indicator\": {\"und\":  \"Diabetes\"},\n" +
                    "\t\t\"field_per\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_notes\": { \"und\": \"test\"},\n" +
                    "\t\t\"field_for_how_long\": {\"und\":  \"1\"},\n" +
                    "\t\t\"field_for_how_long2\": {\"und\": \"30\"}\n" +
                    "       }\n"
        }

        data += "    ]\n" +
                "  }\n" +
                "}"

    }


}