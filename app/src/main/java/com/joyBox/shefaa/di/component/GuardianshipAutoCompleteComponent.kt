package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.autoComplete.GuardianshipAutoCompleteActivity
import com.joyBox.shefaa.di.module.GuardianshipModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [GuardianshipModule::class])
interface GuardianshipAutoCompleteComponent {
    fun inject(guardianshipAutoCompleteActivity: GuardianshipAutoCompleteActivity)
}