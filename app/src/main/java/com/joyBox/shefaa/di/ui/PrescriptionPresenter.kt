package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.models.PrescriptionAddModel
import com.joyBox.shefaa.networking.listeners.OnPrescriptionListResponseListener
import com.joyBox.shefaa.networking.tasks.PrescriptionListPatientAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-20.
 */
class PrescriptionPresenter constructor(val context: Context) : PrescriptionContract.Presenter {
    private val subscriptions = CompositeDisposable()

    private lateinit var view: PrescriptionContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: PrescriptionContract.View) {
        this.view = view
    }

    override fun addPrescription(prescriptionAddModel: PrescriptionAddModel) {

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