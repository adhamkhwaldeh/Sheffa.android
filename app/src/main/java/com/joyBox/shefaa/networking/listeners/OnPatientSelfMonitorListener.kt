package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.SelfMonitorEntity

/**
 * Created by Adhamkh on 2018-10-18.
 */
interface OnPatientSelfMonitorListener {
    fun onPatientSelfMonitorResponseLoading()

    fun onPatientSelfMonitorResponseInternetConnection()

    fun onPatientSelfMonitorResponseSuccessFully(selfMonitorList: List<SelfMonitorEntity>)

    fun onPatientSelfMonitorResponseNoData()
}