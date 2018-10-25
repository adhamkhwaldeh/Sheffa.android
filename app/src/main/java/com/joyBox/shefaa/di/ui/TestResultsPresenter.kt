package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.LabTest
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.networking.listeners.OnLabTestsListener
import com.joyBox.shefaa.networking.listeners.OnTestsResultResponseListener
import com.joyBox.shefaa.networking.tasks.LabTestAsync
import com.joyBox.shefaa.networking.tasks.TestsResultsAsync
import io.reactivex.disposables.CompositeDisposable


class TestResultsPresenter constructor(val context: Context) : TestsResultsContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: TestsResultsContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: TestsResultsContract.View) {
        this.view = view
    }

    override fun loadTestsResults(patientId: String) {
        TestsResultsAsync(patientId, object : OnTestsResultResponseListener {


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

    override fun loadAvailableTest(url: String) {
        LabTestAsync(url, object : OnLabTestsListener {
            override fun onLabTestsLoading() {
                view.showProgress(true)
            }

            override fun onLabTestsInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onLabTestsNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }

            override fun onLabTestsSuccessFully(labTestList: MutableList<LabTest>) {
                view.onAvailableTestsLoadedSuccessfully(labTestList)
            }

        }).execute()
    }

    override fun unSubscribe() {

    }

}