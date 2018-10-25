package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DoctorAppointment

/**
 * Created by Adhamkh on 2018-10-17.
 */
interface OnDoctorAppointmentsListener {
    fun onDoctorAppointmentsLoading()

    fun onDoctorAppointmentsInternetConnection()

    fun onDoctorAppointmentsSuccessFully(doctorAppointments: List<DoctorAppointment>)

    fun onDoctorAppointmentsNoData()
}