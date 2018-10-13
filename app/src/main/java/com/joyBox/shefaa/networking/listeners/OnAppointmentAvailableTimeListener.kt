package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.AvailableTime


interface OnAppointmentAvailableTimeListener {
    fun onAppointmentAvailableTimeLoading()

    fun onAppointmentAvailableTimeInternetConnection()

    fun onAppointmentAvailableTimeSuccessFully(availableTime: AvailableTime)

    fun onAppointmentAvailableTimeNoData()
}