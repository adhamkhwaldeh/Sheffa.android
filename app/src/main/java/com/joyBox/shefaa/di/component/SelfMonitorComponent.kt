package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.SelfMonitorActivity
import com.joyBox.shefaa.di.module.SelfMonitorModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-10-05.
 */

@PerActivity
@Component(modules = [SelfMonitorModule::class])
public interface SelfMonitorComponent {
    fun inject(selfMonitorActivity: SelfMonitorActivity)
}