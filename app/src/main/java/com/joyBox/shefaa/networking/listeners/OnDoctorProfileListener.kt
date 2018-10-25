package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DoctorProfile

/**
 * Created by Adhamkh on 2018-10-17.
 */
interface OnDoctorProfileListener {
    fun onDoctorProfileLoading()

    fun onDoctorProfileInternetConnection()

    fun onDoctorProfileSuccessFully(doctorProfile: DoctorProfile)

    fun onDoctorProfileNoData()
}