package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DoctorAutoComplete

/**
 * Created by Adhamkh on 2018-08-22.
 */
interface OnDoctorAutoCompleteResponseListener {
    fun onDoctorAutoCompleteResponseLoading();

    fun onDoctorAutoCompleteResponseInternetConnection()

    fun onDoctorAutoCompleteResponseSuccessFully(doctorAutoCompleteList: List<DoctorAutoComplete>)

    fun onDoctorAutoCompleteResponseNoData()
}