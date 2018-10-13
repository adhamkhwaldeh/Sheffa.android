package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.AppointmentEntity
import com.joyBox.shefaa.entities.AvailableTime

/**
 * Created by Adhamkh on 2018-09-24.
 */
class AppointmentContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadAvailableTime(doctorId: String, date: String)
        fun addAppointments()
    }

    interface View : BaseContract.View {
        fun onAvailableTimeLoadedSuccessfully(availableTime: AvailableTime)
        fun onAppointmentAddSuccessfully()
    }
}