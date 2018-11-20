package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.*
import com.joyBox.shefaa.filtrations.DoctorFilter
import com.joyBox.shefaa.networking.listeners.*
import com.joyBox.shefaa.networking.tasks.*
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
        }).execute()
    }

    override fun loadDoctorPatients() {
        DoctorPatientsAsync(object : OnDoctorPatientListener {
            override fun onDoctorPatientLoading() {
                view.showProgress(true)
            }

            override fun onDoctorPatientInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorPatientSuccessFully(doctorPatient: List<DoctorPatient>) {
                view.showProgress(false)
                view.onMyPatientsLoaded(doctorPatient.toMutableList())
            }

            override fun onDoctorPatientNoData() {
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun loadDoctorPatientPrescription(patientId: String, doctorName: String) {
        DoctorPatientPrescriptionAsync(patientId, doctorName, object : OnDoctorPatientPrescriptionListener {
            override fun onDoctorPatientPrescriptionLoading() {
                view.showProgress(true)
            }

            override fun onDoctorPatientPrescriptionInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorPatientPrescriptionSuccessFully(doctorPatientPrescription: List<DoctorPatientPrescription>) {
                view.showProgress(false)
                view.onDoctorPatientPrescriptionLoaded(doctorPatientPrescription.toMutableList())
            }

            override fun onDoctorPatientPrescriptionNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun loadDoctorTestResult(doctorId: String) {
        TestsResultsAsync("doctor_id",doctorId, object : OnTestsResultResponseListener {


            override fun onTestsResultResponseLoading() {
                view.showProgress(true)
            }

            override fun onTestsResultResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onTestsResultResponseSuccessFully(testResultEntityList: MutableList<TestResultEntity>) {
                view.showProgress(false)
                view.onTestsResultsLoadedSuccessfully(testResultEntityList)
            }

            override fun onTestsResultResponseNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun unSubscribe() {

    }

}