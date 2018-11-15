package com.joyBox.shefaa.entities.models

class UploadFileModel {
    var filename: String = ""//": "test.docx",
    var filemime: String = ""//": "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    var size: String = ""//":"12578",
    var filepath: String = ""//":"public://test.docx",
    var file: String = ""//": "BASE64 ENCODED DATA"

    constructor(filename: String, filemime: String, size: String, data: String) {
        this.filename = filename
        this.filemime = filemime
        this.size = size

        this.filepath = "public://" + filename
        this.file = data
    }

}