package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-12.
 */
interface OnChangeEmailListener {
    fun changeEmailLoading()
    fun changeEmailFail()
    fun changeEmailSuccessFully()
    fun changeEmailInternetConnection()
}