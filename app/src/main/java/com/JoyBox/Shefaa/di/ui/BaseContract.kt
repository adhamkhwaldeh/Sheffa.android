package com.joyBox.shefaa.di.ui

import android.content.Context
import android.support.v4.app.FragmentActivity

/**
 * Created by Adhamkh on 2017-12-24.
 */
class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unSubscribe()
        fun attachView(view: T)
    }

    interface View {
        fun showProgress(show: Boolean)
        fun showEmptyView(visible: Boolean) {}
        fun showLoadErrorMessage(visible: Boolean) {}
    }
}