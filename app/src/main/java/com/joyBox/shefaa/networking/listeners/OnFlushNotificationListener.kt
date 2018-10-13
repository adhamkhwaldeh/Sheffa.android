package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-13.
 */
interface OnFlushNotificationListener {
    fun onFlushNotificationLoading()

    fun onFlushNotificationInternetConnection()

    fun onFlushNotificationSuccessFully()

    fun onFlushNotificationFail()
}