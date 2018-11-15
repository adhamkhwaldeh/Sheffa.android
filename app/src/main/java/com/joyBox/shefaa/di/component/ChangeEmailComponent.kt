package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.ChangeEmailActivity
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [RegistrationModule::class])
interface ChangeEmailComponent {
    fun inject(chaneEmailActivity: ChangeEmailActivity)
}