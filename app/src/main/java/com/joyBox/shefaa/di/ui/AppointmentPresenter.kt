package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.AvailableTime
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel
import com.joyBox.shefaa.entities.models.AppointmentShiftModel
import com.joyBox.shefaa.entities.models.AppointmentUrgentModel
import com.joyBox.shefaa.enums.AppointmentFlagName
import com.joyBox.shefaa.networking.listeners.*
import com.joyBox.shefaa.networking.tasks.*
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
        AppointmentAvailableTimeAsync(doctorId, date, object : OnAppointmentAvailableTimeListener {

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

    override fun loadDoctorAppointments(url: String) {
        DoctorAppointmentsAsync(url, object : OnDoctorAppointmentsListener {
            override fun onDoctorAppointmentsLoading() {
                view.showProgress(true)
            }

            override fun onDoctorAppointmentsInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorAppointmentsSuccessFully(doctorAppointments: List<DoctorAppointment>) {
                view.showProgress(false)
                view.onDoctorAppointmentsLoaded(doctorAppointmentList = doctorAppointments.toMutableList())
            }

            override fun onDoctorAppointmentsNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun shiftAppointment(url: String, appointmentShiftModel: AppointmentShiftModel) {
        DoctorAppointmentShiftAsync(url, appointmentShiftModel, object : OnDoctorAppointmentShiftListener {
            override fun onAppointmentShiftResponseLoading() {
                view.showProgress(true)
            }

            override fun onAppointmentShiftResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onAppointmentShiftResponseSuccessFully() {
                view.showProgress(false)
                view.onAppointmentShiftedSuccessfully()
            }

            override fun onAppointmentShiftResponseFail() {
                view.showProgress(false)
                view.onAppointmentShiftedFailed()
            }
        }).execute()
    }

    override fun switchAppointmentState(url: String, appointmentFlagName: AppointmentFlagName,
                                        appointmentId: String, userId: String, flag: String) {
        DoctorAppointmentFlagAsync(url, appointmentFlagName, appointmentId, userId, flag,
                object : OnAppointmentFlagListener {
                    override fun onAppointmentFlagResponseLoading() {
                        view.showProgress(true)
                    }

                    override fun onAppointmentFlagResponseInternetConnection() {
                        view.showProgress(false)
                        view.showLoadErrorMessage(true)
                    }

                    override fun onAppointmentFlagResponseSuccessFully() {
                        view.showProgress(false)
                        view.onAppointmentSwitchedSuccessfully()
                    }

                    override fun onAppointmentFlagResponseFail() {
                        view.showProgress(false)
                        view.onAppointmentSwitchedFailed()
                    }
                }).execute()
    }

    override fun switchAppointmentUrgent(url: String, appointmentUrgentModel: AppointmentUrgentModel) {
        DoctorAppointmentUrgentAsync(url, appointmentUrgentModel, object : OnDoctorAppointmentUrgentListener {
            override fun onDoctorAppointmentUrgentResponseLoading() {
                view.showProgress(true)
            }

            override fun onDoctorAppointmentUrgentResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorAppointmentUrgentResponseSuccessFully() {
                view.showProgress(false)
                view.onAppointmentUrgentSwitchedSuccessfully()
            }

            override fun onDoctorAppointmentUrgentResponseFail() {
                view.showProgress(false)
                view.onAppointmentUrgentSwitchedFailed()
            }
        }).execute()
    }

    override fun deleteAppointment(appointmentId: String) {
        DoctorAppointmentDeleteAsync(appointmentId, object : OnDoctorAppointmentDeleteListener {
            override fun onDoctorAppointmentDeleteResponseLoading() {
                view.showProgress(true)
            }

            override fun onDoctorAppointmentDeleteResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorAppointmentDeleteResponseSuccessFully() {
                view.showProgress(false)
                view.onAppointmentDeletedSuccessfully()
            }

            override fun onDoctorAppointmentDeleteResponseFail() {
                view.showProgress(false)
                view.onAppointmentDeletedFailed()
            }
        }).execute()
    }

    override fun addAppointmentInvoice(invoiceModel: AppointmentInvoiceModel) {
        DoctorAppointmentInvoiceAsync(invoiceModel, object : OnDoctorAppointmentInvoiceListener {
            override fun onAppointmentInvoiceLoading() {
                view.showProgress(true)
            }

            override fun onAppointmentInvoiceInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onAppointmentInvoiceSuccessFully() {
                view.showProgress(false)
                view.onAppointmentInvoiceAddedSuccessfully()
            }

            override fun onAppointmentInvoiceFail() {
                view.showProgress(false)
                view.onAppointmentInvoiceAddedFailed()
            }
        }).execute()
    }

    override fun addAppointments() {

    }
}