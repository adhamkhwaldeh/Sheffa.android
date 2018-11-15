package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.MeasurementType
import com.joyBox.shefaa.networking.listeners.OnMeasurementTypeListener
import com.joyBox.shefaa.networking.tasks.MeasurementTypeAsync

class MeasurementTypePresenter constructor(val context: Context) : MeasurementTypeContract.Presenter {
    private lateinit var view: MeasurementTypeContract.View

    override fun subscribe() {
    }

    override fun attachView(view: MeasurementTypeContract.View) {
        this.view = view
    }

    override fun loadMeasurementTypeList() {
        MeasurementTypeAsync(object : OnMeasurementTypeListener {
            override fun onMeasurementTypeLoading() {
                view.showMeasurementTypeProgress(true)
            }

            override fun onMeasurementTypeInternetConnection() {
                view.showMeasurementTypeProgress(false)
                view.showMeasurementTypeLoadErrorMessage(true)

            }

            override fun onMeasurementTypeNoData() {
                view.showMeasurementTypeProgress(false)
                view.showMeasurementTypeEmptyView(true)
            }

            override fun onMeasurementTypeSuccessFully(measurementTypeList: List<MeasurementType>) {
                view.showMeasurementTypeProgress(false)
                view.onMeasurementTypeListLoaded(measurementTypeList = measurementTypeList.toMutableList())
            }

        }).execute()
    }

    override fun unSubscribe() {

    }

}