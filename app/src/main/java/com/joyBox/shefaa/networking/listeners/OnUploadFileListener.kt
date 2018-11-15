package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.responses.UploadFileResponse

/**
 * Created by Adhamkh on 2018-10-28.
 */
interface OnUploadFileListener {
    fun onUploadFileLoading()

    fun onUploadFileInternetConnection()

    fun onUploadFileSuccessFully(response: UploadFileResponse)

    fun onUploadFileFailed()
}