package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.AppointmentAutoComplete

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnAppointmentAutoCompleteListener {
    fun onAppointmentAutoCompleteLoading();

    fun onAppointmentAutoCompleteInternetConnection()

    fun onAppointmentAutoCompleteSuccessFully(appointmentAutoCompleteList: List<AppointmentAutoComplete>)

    fun onAppointmentAutoCompleteNoData()
}