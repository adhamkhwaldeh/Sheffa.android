package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.activities.patient.GuardianshipActivity
import com.joyBox.shefaa.entities.GuardianshipEntity

/**
 * Created by Adhamkh on 2018-10-05.
 */
class GuardianshipContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun loadGuardianshipList(url: String)

    }

    interface View : BaseContract.View {
        fun onGuardianshipListLoaded(guardianshipList: List<GuardianshipEntity>)

    }
}