package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Prescription

/**
 * Created by Adhamkh on 2018-08-20.
 */
class PrescriptionListContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadPrescriptions(url: String)
    }

    interface View : BaseContract.View {
        fun onPrescriptionsLoadedSuccessfully(prescriptionList: List<Prescription>)
    }
}