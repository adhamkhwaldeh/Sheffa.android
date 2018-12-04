package com.joyBox.shefaa.di.ui

import android.content.Context
import io.reactivex.disposables.CompositeDisposable

class AdvertisementPresenter constructor(val context: Context) : AdvertisementContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: AdvertisementContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: AdvertisementContract.View) {
        this.view = view
    }

    override fun unSubscribe() {

    }

}