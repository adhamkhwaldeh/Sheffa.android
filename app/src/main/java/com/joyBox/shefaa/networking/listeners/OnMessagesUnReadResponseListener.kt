package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-08-19.
 */
interface OnMessagesUnReadResponseListener {

    fun onMessageUnReadResponseInternetConnection()

    fun onMessageUnReadResponseSuccessFuly(count: String)

}