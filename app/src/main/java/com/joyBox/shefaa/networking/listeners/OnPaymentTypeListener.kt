package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.PaymentType

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnPaymentTypeListener {
    fun onPaymentTypeLoading()

    fun onPaymentTypeInternetConnection()

    fun onPaymentTypeSuccessFully(paymentTypeList: List<PaymentType>)

    fun onPaymentTypeNoData()
}