package com.joyBox.shefaa.entities

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessageEntity {
    var mid: String = ""
    var subject: String = ""
    var body: String = ""
    var timestamp: String = ""
    var is_new: String = ""
    var thread_id: String = ""
    var author: MessageAuthor = MessageAuthor()

    fun getDate(): String {
        try {
            val dt = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(timestamp)
            return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(dt)
        } catch (ex: Exception) {

        }
        return timestamp
    }


}