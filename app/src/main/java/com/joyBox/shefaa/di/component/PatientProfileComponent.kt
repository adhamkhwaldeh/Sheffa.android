package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.PatientProfileActivity
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-09-30.
 */
@PerActivity
@Component(modules = [RegistrationModule::class])
public interface PatientProfileComponent {
    fun inject(patientProfileActivity: PatientProfileActivity)
}