package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.networking.listeners.OnDoctorAutoCompleteResponseListener
import com.joyBox.shefaa.networking.listeners.OnDoctorSearchListener
import com.joyBox.shefaa.networking.listeners.OnSpecialistAutoCompleteListener
import com.joyBox.shefaa.networking.tasks.DoctorAutoCompleteAsync
import com.joyBox.shefaa.networking.tasks.DoctorSearchAsync
import com.joyBox.shefaa.networking.tasks.SpecialiestAutoCompleteAsync
import io.reactivex.disposables.CompositeDisposable

class DoctorPresenter constructor(val context: Context) : DoctorContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: DoctorContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: DoctorContract.View) {
        this.view = view
    }

    override fun loadDoctorAutoComplete(url: String) {
        DoctorAutoCompleteAsync(url, object : OnDoctorAutoCompleteResponseListener {
            override fun onDoctorAutoCompleteResponseLoading() {
                view.showProgress(true)
            }

            override fun onDoctorAutoCompleteResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorAutoCompleteResponseSuccessFully(doctorAutoCompleteList: List<DoctorAutoComplete>) {
                view.showProgress(false)
                view.onDoctorAutoCompleteSuccessfully(doctorAutoCompleteList)
            }

            override fun onDoctorAutoCompleteResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun loadDoctorList(url: String) {
        DoctorSearchAsync(url, object : OnDoctorSearchListener {
            override fun onDoctorLoading() {
                view.showProgress(true)
            }

            override fun onDoctorInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorSuccessFully(doctorList: MutableList<Doctor>) {
                view.showProgress(false)
                view.onDoctorListLoadedSuccessfully(doctorList)
            }

            override fun onDoctorNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun loadSpecialistAutoComplete(url: String) {
        SpecialiestAutoCompleteAsync(url, object : OnSpecialistAutoCompleteListener {
            override fun onSpecialistAutoLoading() {
                view.showProgress(true)
            }

            override fun onSpecialistAutoInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onSpecialiestAutoSuccessFully(specialistList: MutableList<SpecialistAutoComplete>) {
                view.showProgress(false)
                view.onSpecialistAutoCompleteLoadedSuccessfully(specialistList)
            }

            override fun onSpecialiestAutoNoData() {
                view.showEmptyView(true)
            }
        })
    }

    override fun unSubscribe() {

    }

}