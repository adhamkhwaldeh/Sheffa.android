package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.networking.listeners.OnPharmacySearchListener
import com.joyBox.shefaa.networking.tasks.PharmacySearchAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-10-09.
 */
class PharmacyPresenter constructor(val context: Context) : PharmacyContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: PharmacyContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: PharmacyContract.View) {
        this.view = view
    }

    override fun unSubscribe() {

    }

    override fun loadPharmacyList(url: String) {
        PharmacySearchAsync(url, object : OnPharmacySearchListener {
            override fun onPharmacyLoading() {
                view.showProgress(true)
            }

            override fun onPharmacyInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onPharmacySuccessFully(pharmacyList: MutableList<Pharmacy>) {
                view.showEmptyView(false)
                view.onPharmacyListLoadedSuccessfully(pharmacyList)
            }

            override fun onPharmacyNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }


}