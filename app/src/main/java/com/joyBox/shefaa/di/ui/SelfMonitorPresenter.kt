package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.SelfMonitorEntity
import com.joyBox.shefaa.networking.listeners.OnSelfMonitorListener
import com.joyBox.shefaa.networking.tasks.SelfMonitorAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-10-05.
 */
class SelfMonitorPresenter constructor(val context: Context) : SelfMonitorContact.Presenter {
    private val subscriptions = CompositeDisposable()

    private lateinit var view: SelfMonitorContact.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: SelfMonitorContact.View) {
        this.view = view
    }

    override fun loadSelfMonitorList(url: String) {
        SelfMonitorAsync(url, object : OnSelfMonitorListener {
            override fun onSelfMonitorListResponseLoading() {
                view.showProgress(true)
            }

            override fun onSelfMonitorListResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onSelfMonitorListResponseSuccessFully(selfMonitorList: List<SelfMonitorEntity>) {
                view.showProgress(false)
                view.onSelfMonitorListLoaded(selfMonitorList = selfMonitorList)
            }

            override fun onSelfMonitorListResponseNoData() {
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun unSubscribe() {

    }
}