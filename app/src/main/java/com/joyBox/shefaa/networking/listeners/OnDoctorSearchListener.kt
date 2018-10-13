package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.Doctor

interface OnDoctorSearchListener {
    fun onDoctorLoading()

    fun onDoctorInternetConnection()

    fun onDoctorSuccessFully(doctorList: MutableList<Doctor> /*doctorAutoCompleteList: List<DoctorAutoComplete>*/)

    fun onDoctorNoData()
}