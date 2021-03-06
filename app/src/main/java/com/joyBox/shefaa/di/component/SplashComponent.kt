package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.SplashActivity
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [RegistrationModule::class, NotificationModule::class])
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}