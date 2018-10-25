package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.AppointmentEntity
import com.joyBox.shefaa.entities.AvailableTime
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel
import com.joyBox.shefaa.entities.models.AppointmentShiftModel
import com.joyBox.shefaa.entities.models.AppointmentUrgentModel
import com.joyBox.shefaa.enums.AppointmentFlagName

/**
 * Created by Adhamkh on 2018-09-24.
 */
class AppointmentContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadAvailableTime(doctorId: String, date: String)
        fun loadDoctorAppointments(url: String)
        fun shiftAppointment(url: String, appointmentShiftModel: AppointmentShiftModel)

        fun switchAppointmentState(url: String, appointmentFlagName: AppointmentFlagName, appointmentId: String,
                                   userId: String, flag: String)

        fun switchAppointmentUrgent(url: String, appointmentUrgentModel: AppointmentUrgentModel)

        fun deleteAppointment(appointmentId: String)

        fun addAppointmentInvoice(invoiceModel: AppointmentInvoiceModel)

        fun addAppointments()
    }

    interface View : BaseContract.View {
        fun onAvailableTimeLoadedSuccessfully(availableTime: AvailableTime) {}
        fun onDoctorAppointmentsLoaded(doctorAppointmentList: MutableList<DoctorAppointment>) {}

        fun onAppointmentAddSuccessfully() {}

        fun onAppointmentShiftedSuccessfully() {}
        fun onAppointmentShiftedFailed() {}

        fun onAppointmentSwitchedSuccessfully() {}
        fun onAppointmentSwitchedFailed() {}

        fun onAppointmentUrgentSwitchedSuccessfully() {}
        fun onAppointmentUrgentSwitchedFailed() {}

        fun onAppointmentDeletedSuccessfully() {}
        fun onAppointmentDeletedFailed() {}

        fun onAppointmentInvoiceAddedSuccessfully() {}
        fun onAppointmentInvoiceAddedFailed() {}

    }
}