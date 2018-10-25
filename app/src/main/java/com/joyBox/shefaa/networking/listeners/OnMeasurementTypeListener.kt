package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MeasurementType

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnMeasurementTypeListener {
    fun onMeasurementTypeLoading()

    fun onMeasurementTypeInternetConnection()

    fun onMeasurementTypeSuccessFully(measurementTypeList: List<MeasurementType>)

    fun onMeasurementTypeNoData()
}