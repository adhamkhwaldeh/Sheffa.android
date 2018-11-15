package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.doctor.DoctorAppointmentDetailsActivity
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [AppointmentModule::class])
interface DoctorAppointmentDetailsComponent {
    fun inject(doctorAppointmentDetailsActivity: DoctorAppointmentDetailsActivity)
}