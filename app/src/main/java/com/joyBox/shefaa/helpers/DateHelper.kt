package com.joyBox.shefaa.helpers

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Adhamkh on 2018-11-17.
 */
class DateHelper {
    companion object {

        fun getCurrentDate(): String {
            val c = Calendar.getInstance().getTime()
            println("Current time => " + c)

            val df = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val formattedDate = df.format(c)

            return formattedDate
        }
    }
}