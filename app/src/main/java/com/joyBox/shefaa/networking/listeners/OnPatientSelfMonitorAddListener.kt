package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-31.
 */
interface OnPatientSelfMonitorAddListener {
    fun onPatientSelfMonitorAddLoading()

    fun onPatientSelfMonitorAddInternetConnection()

    fun onPatientSelfMonitorAddSuccessfully()

    fun onPatientSelfMonitorAddFailed()
}