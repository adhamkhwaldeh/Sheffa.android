package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.doctor.DoctorAddPrescriptionActivity
import com.joyBox.shefaa.di.module.PrescriptionModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [PrescriptionModule::class])
interface DoctorAddPrescriptionComponent {
    fun inject(doctorAddPrescriptionActivity: DoctorAddPrescriptionActivity)
}