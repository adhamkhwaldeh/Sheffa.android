package com.joyBox.shefaa.entities.models

class SelfMonitorAddModel constructor(val notes: String, val indicatorValue: String,
                                      val date: String, val time: String,
                                      val indicatorUnit: String, val indicatorType: String) {

    var data = "{\n" +
            "\"type\": \"self_monitor\",\n" +
            "\"language\": \"und\",\n" +
            "\"body\": {\"und\": [{\"value\": \"" + notes + "\"}]},\n" +
            "\"field_value_of_indicator\": {\n" +
            "    \"und\": [{\"value\": \"" + indicatorValue + "\"}]},\n" +
            "\"field_measurement_time\":{\"und\":\n" +
            "[{\"value\":{\"date\":\"" + date + "\", \"time\":\"" + time + "\"}}]},\n" +
            "  \"field_unit\":  {\"und\": [{\"value\": \"" + indicatorUnit + "\"}]},\n" +
            "  \"field_indicator\": {\"und\": \"" + indicatorType + "\"}\n" +
            "}\n"

}