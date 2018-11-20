package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.autoComplete.AppointmentAutoCompleteActivity
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [AppointmentModule::class])
interface AppointmentAutoCompleteComponent {
    fun inject(appointmentAutoCompleteActivity: AppointmentAutoCompleteActivity)
}