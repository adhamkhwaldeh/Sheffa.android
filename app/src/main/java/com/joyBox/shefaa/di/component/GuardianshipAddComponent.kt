package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.GuardianshipAddActivity
import com.joyBox.shefaa.di.module.GuardianshipModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [GuardianshipModule::class])
interface GuardianshipAddComponent {
    fun inject(guardianshipAddActivity: GuardianshipAddActivity)
}