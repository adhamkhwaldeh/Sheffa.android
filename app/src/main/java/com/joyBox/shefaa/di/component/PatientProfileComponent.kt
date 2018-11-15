package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.PatientProfileActivity
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [RegistrationModule::class])
interface PatientProfileComponent {
    fun inject(patientProfileActivity: PatientProfileActivity)
}