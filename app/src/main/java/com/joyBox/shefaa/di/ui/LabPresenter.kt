package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Lab
import com.joyBox.shefaa.networking.listeners.OnLabSearchListener
import com.joyBox.shefaa.networking.tasks.LabSearchAsync
import io.reactivex.disposables.CompositeDisposable


class LabPresenter constructor(val context: Context) : LabContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: LabContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: LabContract.View) {
        this.view = view
    }

    override fun unSubscribe() {

    }

    override fun loadLabList(url: String) {
        LabSearchAsync(url, object : OnLabSearchListener {
            override fun onLabLoading() {
                view.showProgress(true)
            }

            override fun onLabInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onLabSuccessFully(labList: MutableList<Lab>) {
                view.showProgress(false)
                view.onLabListLoaded(labList)
            }

            override fun onLabNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

}