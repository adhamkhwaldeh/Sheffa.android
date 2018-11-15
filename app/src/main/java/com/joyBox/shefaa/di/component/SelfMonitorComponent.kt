package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.SelfMonitorActivity
import com.joyBox.shefaa.di.module.SelfMonitorModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [SelfMonitorModule::class])
interface SelfMonitorComponent {
    fun inject(selfMonitorActivity: SelfMonitorActivity)
}