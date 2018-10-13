package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.networking.listeners.OnPrescriptionFollowUpResponseListener
import com.joyBox.shefaa.networking.tasks.PrescriptionFollowUpAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-21.
 */
class PrescriptionFollowUpPresenter constructor(val context: Context) : PrescriptionFollowUpContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: PrescriptionFollowUpContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: PrescriptionFollowUpContract.View) {
        this.view = view
    }

    override fun loadPrescriptionFollowUp(itemId: String) {

        PrescriptionFollowUpAsync(itemId, object : OnPrescriptionFollowUpResponseListener {
            override fun onPrescriptionFollowUpResponseLoading() {
                view.showProgress(true)
            }

            override fun onPrescriptionFollowUpResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onPrescriptionFollowUpResponseSuccessFuly(prescriptionFollowUpList: List<PrescriptionFollowUp>) {
                view.showProgress(false)
                view.onPrescriptionFollowUpSuccessfully(prescriptionFollowUpList)
            }

            override fun onPrescriptionFollowUpResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun unSubscribe() {

    }

}