package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DoctorProfile

/**
 * Created by Adhamkh on 2018-11-14.
 */
interface OnDoctorProfileUpdateListener {
    fun onDoctorProfileUpdateLoading()

    fun onDoctorProfileUpdateInternetConnection()

    fun onDoctorProfileUpdateSuccessFully()

    fun onDoctorProfileUpdateFail()
}