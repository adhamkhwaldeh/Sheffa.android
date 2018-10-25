package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-18.
 */
interface OnDoctorAppointmentShiftListener {
    fun onAppointmentShiftResponseLoading()

    fun onAppointmentShiftResponseInternetConnection()

    fun onAppointmentShiftResponseSuccessFully()

    fun onAppointmentShiftResponseFail()
}