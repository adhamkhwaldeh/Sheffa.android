package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.SelfMonitorEntity
import com.joyBox.shefaa.entities.models.SelfMonitorAddModel

/**
 * Created by Adhamkh on 2018-10-05.
 */
class SelfMonitorContact {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadSelfMonitorList(url: String)
        fun loadPatientSelfMonitor(patientId: String)
        fun addSelfMonitor(selfMonitorAddModel: SelfMonitorAddModel)
    }

    interface View : BaseContract.View {
        fun onSelfMonitorListLoaded(selfMonitorList: List<SelfMonitorEntity>) {}

        fun onPatientSelfMonitorListLoaded(selfMonitorList: List<SelfMonitorEntity>) {}

        fun onSelfMonitorAddedSuccessfully() {}

        fun onSelfMonitorAddedFailed() {}

    }

}