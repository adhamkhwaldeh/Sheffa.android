package com.joyBox.shefaa.di.ui

/**
 * Created by Adhamkh on 2018-10-29.
 */
class ReminderContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun remind(url: String)

    }

    interface View : BaseContract.View {
        fun onRemindSuccessfully(stringList: MutableList<String>)

    }
}