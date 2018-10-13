package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.AppointmentEntity
import com.joyBox.shefaa.networking.listeners.OnAppointmentListResponseListener
import com.joyBox.shefaa.networking.tasks.AppointmentListPatientAsync
import io.reactivex.disposables.CompositeDisposable

class AppointmentListPatientPresenter constructor(val context: Context) : AppointmentListPatientContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: AppointmentListPatientContract.View
    init {

    }
    override fun subscribe() {
    }
    override fun attachView(view: AppointmentListPatientContract.View) {
        this.view = view
    }

    override fun loadAppointments(patientId: String) {
        AppointmentListPatientAsync(patientId,object :OnAppointmentListResponseListener{
            override fun onAppointmentResponseLoading() {
                view.showProgress(true)
            }

            override fun onAppointmentResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onAppointmentResponseSuccessFully(appointmentList: List<AppointmentEntity>) {
                view.showProgress(false)
                view.onAppointmentsLoadedSuccesfuly(appointmentList)
            }

            override fun onAppointmentResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun unSubscribe() {

    }

}