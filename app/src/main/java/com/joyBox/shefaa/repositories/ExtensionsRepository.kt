package com.joyBox.shefaa.repositories

/**
 * Created by Adhamkh on 2018-10-29.
 */
class ExtensionsRepository {

    companion object {
        val extensionsMap = hashMapOf<String, String>().apply {
            put("txt", "text/plain")
            put("doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
            put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
            put("xls", "application/vnd.ms-excel")
            put("xlsx", "application/vnd.ms-excel")
            put("jpg", "image/jpeg")
            put("jpeg", "image/jpeg")
            put("png", "image/png")
            put("zip", "application/zip")
            put("rar", "application/x-rar")
            put("ppt", "application/vnd.ms-powerpoint")
        }


    }
}