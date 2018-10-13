package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.AppointmentAddActivity
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [AppointmentModule::class])
public interface AppointmentAddComponent {
    fun inject(appointmentAddActivity: AppointmentAddActivity)
}