package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.PatientProfile

/**
 * Created by Adhamkh on 2018-09-29.
 */
interface OnPatientProfileListener {
    fun onPatientProfileoading()

    fun onPatientProfileInternetConnection()

    fun onPatientProfileSuccessFully(patientProfile: PatientProfile)

    fun onPatientProfileNoData()
}