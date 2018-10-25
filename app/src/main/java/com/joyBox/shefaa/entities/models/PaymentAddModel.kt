package com.joyBox.shefaa.entities.models

import com.joyBox.shefaa.entities.PaymentType

/**
 * Created by Adhamkh on 2018-10-20.
 */
class PaymentAddModel constructor(var title: String, var cost: String, var date: String,
                                  var desc: String, var paymentType: PaymentType) {

    var data = "{\n" +
            "  \"title\": \"صيانة اجهزة\",\n" +
            "  \"type\": \"expenses\",\n" +
            "  \"language\": \"und\",\n" +
            "  \"field_how_much\": {\"und\": [{\"value\": \"" + cost + "\"}]},\n" +
            "  \"field_paying_date\":{\"und\":\n" +
            "[{\"value\":{\"date\":\"" + date + "\"}}]},\n" +
            "  \"field_paying_note\": {\n" +
            "    \"und\": [{\"value\": \"" + desc + "\"}]},\n" +
            "  \"field_type_of_payments\": {\n" +
            "    \"und\": \"" + paymentType.name + "\"}\n" +
            "}\n"
}