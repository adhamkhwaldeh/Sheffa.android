package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.SelfMonitorAddActivity
import com.joyBox.shefaa.di.module.MeasurementTypeModule
import com.joyBox.shefaa.di.module.SelfMonitorModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [SelfMonitorModule::class, MeasurementTypeModule::class])
interface SelfMonitorAddComponent {
    fun inject(selfMonitorAddActivity: SelfMonitorAddActivity)
}