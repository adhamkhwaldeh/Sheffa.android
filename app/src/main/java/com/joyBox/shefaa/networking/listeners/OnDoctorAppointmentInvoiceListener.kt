package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnDoctorAppointmentInvoiceListener {
    fun onAppointmentInvoiceLoading()

    fun onAppointmentInvoiceInternetConnection()

    fun onAppointmentInvoiceSuccessFully()

    fun onAppointmentInvoiceFail()
}