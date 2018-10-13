package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-12.
 */
interface OnChangePasswordListener {
    fun changePasswordLoading()
    fun changePasswordFail()
    fun changePasswordSuccessFully()
    fun changePasswordInternetConnection()
}