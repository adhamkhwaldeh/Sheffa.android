package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.Prescription

interface OnPrescriptionListResponseListener {
    fun onPrescriptionListResponseLoading()

    fun onPrescriptionListResponseInternetConnection()

    fun onPrescriptionListResponseSuccessFully(prescriptionList: List<Prescription>)

    fun onPrescriptionListResponseNoData()
}