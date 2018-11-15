package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.networking.listeners.OnReminderListener
import com.joyBox.shefaa.networking.tasks.ReminderAsync
import io.reactivex.disposables.CompositeDisposable


class ReminderPresenter constructor(val context: Context) : ReminderContract.Presenter {
    private val subscriptions = CompositeDisposable()

    private lateinit var view: ReminderContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: ReminderContract.View) {
        this.view = view
    }

    override fun remind(url: String) {
        ReminderAsync(url, object : OnReminderListener {
            override fun onReminderLoading() {
                view.showProgress(true)
            }

            override fun onReminderInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onReminderSuccessfully(stringList: MutableList<String>) {
                view.showProgress(false)
                view.onRemindSuccessfully(stringList)
            }

            override fun onReminderNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun unSubscribe() {

    }
}