package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MessageResult

/**
 * Created by Adhamkh on 2018-08-17.
 */
interface OnMessageReplayResponseListener {

    fun onMessageReplayResponseLoading();

    fun onMessageReplayResponseInternetConnection()

    fun onMessageResponseSuccessFuly()

    fun onMessageResponseFail(messageResult: MessageResult)
}