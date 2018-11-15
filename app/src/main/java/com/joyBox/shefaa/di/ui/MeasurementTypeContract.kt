package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MeasurementType

class MeasurementTypeContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun loadMeasurementTypeList()
    }

    interface View : BaseContract.View {
        fun showMeasurementTypeProgress(show: Boolean)
        fun showMeasurementTypeEmptyView(visible: Boolean)
        fun showMeasurementTypeLoadErrorMessage(visible: Boolean)
        fun onMeasurementTypeListLoaded(measurementTypeList: MutableList<MeasurementType>)
    }
}