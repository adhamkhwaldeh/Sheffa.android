package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-27.
 */
interface OnAppointmentReserveListener {
    fun onAppointmentReserveLoading();

    fun onAppointmentReserveInternetConnection()

    fun onAppointmentReserveSuccessFully()

    fun onAppointmentReserveFailed()
}