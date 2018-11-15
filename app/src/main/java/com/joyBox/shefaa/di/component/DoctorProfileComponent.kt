package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.doctor.DoctorProfileActivity
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.module.MedicalProfileModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [MedicalProfileModule::class, DoctorModule::class])
interface DoctorProfileComponent {
    fun inject(doctorProfileActivity: DoctorProfileActivity)
}
