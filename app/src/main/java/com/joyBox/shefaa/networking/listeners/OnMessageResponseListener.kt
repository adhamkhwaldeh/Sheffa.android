package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MessageEntity

/**
 * Created by Adhamkh on 2018-08-16.
 */
interface OnMessageResponseListener {

    fun onMessageResponseLoading()

    fun onMessageResponseInternetConnection()

    fun onMessageResponseSuccessFully(messageList: MutableList<MessageEntity>)

    fun onMessageResponseNoData()
}