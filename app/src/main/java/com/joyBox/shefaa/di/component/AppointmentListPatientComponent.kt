package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.AppointmentListPatientActivity
import com.joyBox.shefaa.di.module.AppointmentListPatientModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [AppointmentListPatientModule::class])
interface AppointmentListPatientComponent {
    fun inject(appointmentListPatientActivity: AppointmentListPatientActivity)
}