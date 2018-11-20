package com.joyBox.shefaa.entities.models

class IncomingAddModel constructor(var title: String, var desc: String,
                                   var appointmentTitle: String, var invoiceValue: String) {
    var data = "{\n" +
            "  \"title\": \"" + title + "\",\n" +
            "  \"type\": \"receipt\",\n" +
            "  \"language\": \"und\",\n" +
            "  \"body\": {\"und\": [{\"value\": \"" + desc + "\"}]},\n" +
            "  \"field_appointment_number\": \n" +
            "{\n" +
            "    \"und\": [{\"target_id\": \"" + appointmentTitle + "\"}]\n" +
            "  },\n  \"field_receipt_value\": {\"und\": [{\"value\": \"" + invoiceValue + "\"}]}\n" +
            "}\n"
}