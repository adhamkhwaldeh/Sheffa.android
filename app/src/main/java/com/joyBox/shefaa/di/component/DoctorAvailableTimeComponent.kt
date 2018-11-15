package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.DoctorAvailableTimeDialog
import dagger.Component

@PerActivity
@Component(modules = [AppointmentModule::class])
interface DoctorAvailableTimeComponent {
    fun inject(doctorAvailableTime: DoctorAvailableTimeDialog)
}