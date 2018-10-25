package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-18.
 */
interface OnAppointmentFlagListener {
    fun onAppointmentFlagResponseLoading()

    fun onAppointmentFlagResponseInternetConnection()

    fun onAppointmentFlagResponseSuccessFully()

    fun onAppointmentFlagResponseFail()
}