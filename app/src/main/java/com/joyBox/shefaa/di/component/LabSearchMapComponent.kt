package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.LabSearchMapActivity
import com.joyBox.shefaa.di.module.LabModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [LabModule::class])
interface LabSearchMapComponent {
    fun inject(labSearchMapActivity: LabSearchMapActivity)
}