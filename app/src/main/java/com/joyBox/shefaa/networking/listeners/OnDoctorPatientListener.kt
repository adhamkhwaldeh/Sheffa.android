package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DoctorPatient

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnDoctorPatientListener {
    fun onDoctorPatientLoading()

    fun onDoctorPatientInternetConnection()

    fun onDoctorPatientSuccessFully(doctorPatient: List<DoctorPatient>)

    fun onDoctorPatientNoData()
}