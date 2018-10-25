package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnPaymentAddListener {
    fun onPaymentAddLoading()

    fun onPaymentAddInternetConnection()

    fun onPaymentAddSuccessFully()

    fun onPaymentAddFail()
}