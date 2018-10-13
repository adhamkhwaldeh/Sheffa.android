package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.PrescriptionFollowUp

/**
 * Created by Adhamkh on 2018-08-21.
 */
interface OnPrescriptionFollowUpResponseListener {
    fun onPrescriptionFollowUpResponseLoading();

    fun onPrescriptionFollowUpResponseInternetConnection()

    fun onPrescriptionFollowUpResponseSuccessFuly(prescriptionFollowUpList: List<PrescriptionFollowUp>)

    fun onPrescriptionFollowUpResponseNoData()
}