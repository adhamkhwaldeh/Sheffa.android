package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.responses.UploadFileResponse


class AttachmentContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun uploadFile(filePath: String)
    }

    interface View : BaseContract.View {
        fun showAttachmentProgress(show: Boolean)
        fun showAttachmentEmptyView(visible: Boolean) {}
        fun showAttachmentLoadErrorMessage(visible: Boolean) {}


        fun uploadFileSuccessfully(uploadFileResponse: UploadFileResponse) {}
        fun uploadFileFailed() {}

        fun fileNotFound() {}
        fun notSupportExtension() {}

    }

}