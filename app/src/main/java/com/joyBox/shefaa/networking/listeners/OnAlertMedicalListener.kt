package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnAlertMedicalListener {
    fun onAlertMedicalLoading()

    fun onAlertMedicalInternetConnection()

    fun onAlertMedicalSuccessFully(alertMedicalList: List<String>)

    fun onAlertMedicalNoData()
}