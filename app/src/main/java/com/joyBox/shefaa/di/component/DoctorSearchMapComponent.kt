package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.DoctorSearchMapActivity
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [DoctorModule::class])
interface DoctorSearchMapComponent {
    fun inject(doctorSearchMapActivity: DoctorSearchMapActivity)
}