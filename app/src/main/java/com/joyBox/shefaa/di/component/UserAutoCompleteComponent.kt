package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.autoComplete.UserAutoCompleteActivity
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [MessageModule::class])
interface UserAutoCompleteComponent {
    fun inject(userAutoComplete: UserAutoCompleteActivity)
}