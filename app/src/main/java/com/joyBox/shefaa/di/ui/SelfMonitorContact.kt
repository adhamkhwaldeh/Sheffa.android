package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.SelfMonitorEntity

/**
 * Created by Adhamkh on 2018-10-05.
 */
class SelfMonitorContact {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadSelfMonitorList(url: String)

    }

    interface View : BaseContract.View {
        fun onSelfMonitorListLoaded(selfMonitorList: List<SelfMonitorEntity>)
    }
}