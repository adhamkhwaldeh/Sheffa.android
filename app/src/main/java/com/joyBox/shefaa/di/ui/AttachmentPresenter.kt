package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.converters.FileConverter
import com.joyBox.shefaa.entities.models.UploadFileModel
import com.joyBox.shefaa.entities.responses.UploadFileResponse
import com.joyBox.shefaa.networking.listeners.OnUploadFileListener
import com.joyBox.shefaa.networking.tasks.UploadFileAsync
import com.joyBox.shefaa.repositories.ExtensionsRepository
import java.io.File


class AttachmentPresenter constructor(val context: Context) : AttachmentContract.Presenter {
    private lateinit var view: AttachmentContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun unSubscribe() {

    }

    override fun attachView(view: AttachmentContract.View) {
        this.view = view
    }

    override fun uploadFile(filePath: String) {
        val file = File(filePath)
        if (file.exists()) {
            if (ExtensionsRepository.extensionsMap.containsKey(file.extension.toLowerCase())) {
                val uploadFileModel = UploadFileModel(filename = file.name,
                        filemime = file.extension, size = file.length().toString(),
                        data = FileConverter.file2Base24(file))
                UploadFileAsync(uploadFileModel, object : OnUploadFileListener {
                    override fun onUploadFileLoading() {
                        view.showAttachmentProgress(true)
                    }

                    override fun onUploadFileInternetConnection() {
                        view.showAttachmentProgress(false)
                        view.showAttachmentLoadErrorMessage(true)
                    }

                    override fun onUploadFileSuccessFully(response: UploadFileResponse) {
                        view.showAttachmentProgress(false)
                        view.uploadFileSuccessfully(response)
                    }

                    override fun onUploadFileFailed() {
                        view.showAttachmentProgress(false)
                        view.uploadFileFailed()
                    }
                }).execute()
            } else {
                view.notSupportExtension()
            }
        } else {
            view.fileNotFound()
        }
    }


}