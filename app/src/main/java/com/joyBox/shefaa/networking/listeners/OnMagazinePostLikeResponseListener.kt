package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-05.
 */
interface OnMagazinePostLikeResponseListener {
    fun onMagazinePostLikeResponseLoading()

    fun onMagazinePostLikeResponseInternetConnection()

    fun onMagazinePostLikeResponseSuccessFully()

    fun onMagazinePostLikeResponseFail()
}