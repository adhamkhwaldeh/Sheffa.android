package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.entities.AvailableTime
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel
import com.joyBox.shefaa.entities.models.AppointmentShiftModel
import com.joyBox.shefaa.entities.models.AppointmentUrgentModel
import com.joyBox.shefaa.enums.AppointmentFlagName


class AppointmentContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadAvailableTime(doctorId: String, date: String)
        fun loadDoctorAppointments(url: String)
        fun shiftAppointment(url: String, appointmentShiftModel: AppointmentShiftModel)

        fun switchAppointmentState(url: String, appointmentFlagName: AppointmentFlagName, appointmentId: String,
                                   userId: String, flag: String)

        fun switchAppointmentUrgent(url: String, appointmentUrgentModel: AppointmentUrgentModel)

        fun deleteAppointment(appointmentId: String)

        fun addAppointment(url: String)

        fun loadAutoCompleteAppointments(title: String)
    }

    interface View : BaseContract.View {
        fun onAvailableTimeLoadedSuccessfully(availableTime: AvailableTime) {}
        fun onDoctorAppointmentsLoaded(doctorAppointmentList: MutableList<DoctorAppointment>) {}

        fun onAppointmentAddSuccessfully() {}
        fun onAppointmentAddFail() {}

        fun onAppointmentShiftedSuccessfully() {}
        fun onAppointmentShiftedFailed() {}

        fun onAppointmentSwitchedSuccessfully() {}
        fun onAppointmentSwitchedFailed() {}

        fun onAppointmentUrgentSwitchedSuccessfully() {}
        fun onAppointmentUrgentSwitchedFailed() {}

        fun onAppointmentDeletedSuccessfully() {}
        fun onAppointmentDeletedFailed() {}

        fun onAppointmentAutoCompletLoaded(appointmentAutoCompleteList: MutableList<AppointmentAutoComplete>) {}


    }
}