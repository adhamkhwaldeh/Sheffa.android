package com.joyBox.shefaa.entities.models

/**
 * Created by Adhamkh on 2018-10-20.
 */
class AppointmentInvoiceModel constructor(var title: String, var desc: String,
                                          var appointmentId: String, var invoiceValue: String) {
    var data = "{\n" +
            "  \"title\": \"" + title + "\",\n" +
            "  \"type\": \"receipt\",\n" +
            "  \"language\": \"und\",\n" +
            "  \"body\": {\"und\": [{\"value\": \"" + desc + "\"}]},\n" +
            "  \"field_appointment_number\": \n" +
            "{\n" +
            "    \"und\": [{\"target_id\": \"" + appointmentId + "\"}]\n" +
            "  },\n" +
            "  \"field_receipt_value\": {\"und\": [{\"value\": \"" + invoiceValue + "\"}]}\n" +
            "}\n"
}