package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.DashBoardActivity
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [RegistrationModule::class, NotificationModule::class])
interface DashBoardComponent {
    fun inject(dashBoardActivity: DashBoardActivity)
}