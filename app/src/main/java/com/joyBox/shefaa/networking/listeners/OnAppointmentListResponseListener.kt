package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.AppointmentEntity

/**
 * Created by Adhamkh on 2018-08-19.
 */
interface OnAppointmentListResponseListener {
    fun onAppointmentResponseLoading();

    fun onAppointmentResponseInternetConnection()

    fun onAppointmentResponseSuccessFuly(appointmentList: List<AppointmentEntity>)

    fun onAppointmentResponseNoData()
}