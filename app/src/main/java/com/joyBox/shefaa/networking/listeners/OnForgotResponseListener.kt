package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-08-10.
 */

public interface OnForgotResponseListener {
    fun ForgotPasswordLoading()
    fun ForgotPasswordFail()
    fun ForgotPasswordSuccessFuly()
    fun ForgotPasswordInternetConnection()
}