package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-05.
 */
interface OnMagazinePostCommentAddListener {
    fun onMagazinePostCommentAddResponseLoading();

    fun onMagazinePostCommentAddResponseInternetConnection()

    fun onMagazinePostCommentAddResponseSuccessFully()

    fun onMagazinePostCommentAddResponseFail()
}