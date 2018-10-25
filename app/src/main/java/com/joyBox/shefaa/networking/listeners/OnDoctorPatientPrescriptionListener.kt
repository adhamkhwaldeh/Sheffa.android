package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DoctorPatientPrescription

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnDoctorPatientPrescriptionListener {
    fun onDoctorPatientPrescriptionLoading()

    fun onDoctorPatientPrescriptionInternetConnection()

    fun onDoctorPatientPrescriptionSuccessFully(doctorPatientPrescription: List<DoctorPatientPrescription>)

    fun onDoctorPatientPrescriptionNoData()
}