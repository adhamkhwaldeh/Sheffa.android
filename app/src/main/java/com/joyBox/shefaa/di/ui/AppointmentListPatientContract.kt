package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.AppointmentEntity

class AppointmentListPatientContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadAppointments(patientId: String)
    }

    interface View : BaseContract.View {
        fun onAppointmentsLoadedSuccesfuly(appointmentList:List<AppointmentEntity>)
    }

}