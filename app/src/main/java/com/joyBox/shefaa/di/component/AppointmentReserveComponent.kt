package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.AppointmentReserveActivity
import com.joyBox.shefaa.di.module.AppointmentListPatientModule
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [AppointmentListPatientModule::class, DoctorModule::class])
public interface AppointmentReserveComponent {
    fun inject(appointmentReserveActivity: AppointmentReserveActivity)
}