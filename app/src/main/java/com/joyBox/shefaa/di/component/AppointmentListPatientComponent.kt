package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.AppointmentListPatientActivity
import com.joyBox.shefaa.di.module.AppointmentListPatientModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-19.
 */
@PerActivity
@Component(modules = [AppointmentListPatientModule::class])
public interface AppointmentListPatientComponent {
    fun inject(appointmentListPatientActivity: AppointmentListPatientActivity)
}