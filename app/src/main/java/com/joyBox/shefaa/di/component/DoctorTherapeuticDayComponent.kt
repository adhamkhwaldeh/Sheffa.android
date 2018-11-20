package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.doctor.DoctorTherapeuticDayActivity
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [DoctorModule::class, AppointmentModule::class])
interface DoctorTherapeuticDayComponent {
    fun inject(doctorTherapeuticDayActivity: DoctorTherapeuticDayActivity)
}