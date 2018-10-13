package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Lab

/**
 * Created by Adhamkh on 2018-10-12.
 */
class LabContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun loadLabList(url: String)
    }

    interface View : BaseContract.View {
        fun onLabListLoaded(labList: MutableList<Lab>)
    }

}