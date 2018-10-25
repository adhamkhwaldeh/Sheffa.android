package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-18.
 */
interface OnDoctorAppointmentUrgentListener {
    fun onDoctorAppointmentUrgentResponseLoading()

    fun onDoctorAppointmentUrgentResponseInternetConnection()

    fun onDoctorAppointmentUrgentResponseSuccessFully()

    fun onDoctorAppointmentUrgentResponseFail()
}