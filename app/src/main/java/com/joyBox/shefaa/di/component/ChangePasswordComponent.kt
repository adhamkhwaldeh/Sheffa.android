package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.ChangePasswordActivity
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [RegistrationModule::class])
public interface ChangePasswordComponent {
    fun inject(changePasswordActivity: ChangePasswordActivity)
}