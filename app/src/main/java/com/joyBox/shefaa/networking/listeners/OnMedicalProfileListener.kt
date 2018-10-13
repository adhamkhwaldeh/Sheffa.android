package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MedicalProfile

interface OnMedicalProfileListener {
    fun onMedicalProfileLoading();

    fun onMedicalProfileInternetConnection()

    fun onMedicalProfileSuccessFully(medicalProfile: MedicalProfile)

    fun onMedicalProfileNoData()
}