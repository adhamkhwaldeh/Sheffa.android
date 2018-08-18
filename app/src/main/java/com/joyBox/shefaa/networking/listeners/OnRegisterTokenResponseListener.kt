package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-08-18.
 */
interface OnRegisterTokenResponseListener {
    fun onRegisterTokenResponseLoading()

    fun onRegisterTokenResponseInternetConnection()

    fun onRegisterTokenResponseSuccessFuly()

    fun onRegisterTokenResponseFail()
}