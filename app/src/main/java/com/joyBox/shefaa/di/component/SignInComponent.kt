package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.SignInActivity
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-10.
 */
@PerActivity
@Component(modules = [RegistrationModule::class, NotificationModule::class])
public interface SignInComponent {
    fun inject(signInActivity: SignInActivity)
}