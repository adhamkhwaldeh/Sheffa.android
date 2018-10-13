package com.joyBox.shefaa.networking.listeners


/**
 * Created by Adhamkh on 2018-10-13.
 */
interface OnLogoutListener {
    fun onLogoutLoading()

    fun onLogoutInternetConnection()

    fun onLogoutSuccessFully()

    fun onLogoutFail()
}