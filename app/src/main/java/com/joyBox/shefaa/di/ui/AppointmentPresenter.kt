package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.AvailableTime
import com.joyBox.shefaa.networking.listeners.OnAppointmentAvailableTimeListener
import com.joyBox.shefaa.networking.tasks.AppointmentAvailableTimeAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-09-24.
 */
class AppointmentPresenter constructor(val context: Context) : AppointmentContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: AppointmentContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun unSubscribe() {

    }

    override fun attachView(view: AppointmentContract.View) {
        this.view = view
    }

    override fun loadAvailableTime(doctorId: String, date: String) {
        AppointmentAvailableTimeAsync(doctorId,date,object :OnAppointmentAvailableTimeListener{

            override fun onAppointmentAvailableTimeLoading() {
                view.showProgress(true)
            }

            override fun onAppointmentAvailableTimeInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onAppointmentAvailableTimeSuccessFully(availableTime: AvailableTime) {
                view.showProgress(false)
                view.onAvailableTimeLoadedSuccessfully(availableTime)
            }

            override fun onAppointmentAvailableTimeNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }

        }).execute()

    }

    override fun addAppointments() {

    }
}