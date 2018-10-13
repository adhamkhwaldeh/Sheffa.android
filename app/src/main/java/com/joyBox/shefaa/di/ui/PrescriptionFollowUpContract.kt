package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.PrescriptionFollowUp

/**
 * Created by Adhamkh on 2018-08-21.
 */
class PrescriptionFollowUpContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadPrescriptionFollowUp(itemId: String)
    }

    interface View : BaseContract.View {
        fun onPrescriptionFollowUpSuccessfully(prescriptionFollowUpList: List<PrescriptionFollowUp>)
    }
}