package com.joyBox.shefaa.networking.listeners


interface OnMedicalProfileUpdateListener {
    fun onMedicalProfileUpdateLoading();

    fun onMedicalProfileUpdateInternetConnection()

    fun onMedicalProfileUpdateSuccessFully()

    fun onMedicalProfileUpdateNoData()
}