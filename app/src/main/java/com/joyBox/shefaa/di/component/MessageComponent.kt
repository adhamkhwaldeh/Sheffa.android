package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MessagesActivity
import com.joyBox.shefaa.activities.SignInActivity
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-16.
 */
@PerActivity
@Component(modules = [MessageModule::class])
public interface MessageComponent {
    fun inject(messagesActivity: MessagesActivity)
}