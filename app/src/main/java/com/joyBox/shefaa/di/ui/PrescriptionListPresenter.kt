package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.networking.listeners.OnPrescriptionListResponseListener
import com.joyBox.shefaa.networking.tasks.PrescriptionListPatientAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-20.
 */
class PrescriptionListPresenter constructor(val context: Context) : PresciptionListContract.Presenter {
    private val subscriptions = CompositeDisposable()

    private lateinit var view: PresciptionListContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: PresciptionListContract.View) {
        this.view = view
    }

    override fun loadPrescriptions(url: String) {
        PrescriptionListPatientAsync(url, object : OnPrescriptionListResponseListener {
            override fun onPrescriptionListResponseLoading() {
                view.showProgress(true)
            }

            override fun onPrescriptionListResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onPrescriptionListResponseSuccessFully(prescriptionList: List<Prescription>) {
                view.showProgress(false)
                view.onPrescriptionsLoadedSuccessfully(prescriptionList)
            }

            override fun onPrescriptionListResponseNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun unSubscribe() {

    }
}