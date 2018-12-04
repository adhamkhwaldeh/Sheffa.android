package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.models.PrescriptionAddModel

class PrescriptionContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun addPrescription(prescriptionAddModel: PrescriptionAddModel)
        fun loadPrescriptions(url: String)
    }

    interface View : BaseContract.View {
        fun onPrescriptionsLoadedSuccessfully(prescriptionList: List<Prescription>) {}

        fun onPrescriptionAddedSuccessfully() {}
        fun onAddPrescriptionFailed() {}

    }
}