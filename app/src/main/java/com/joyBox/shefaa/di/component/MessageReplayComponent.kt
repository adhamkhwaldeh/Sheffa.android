package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MessageReplayActivity
import com.joyBox.shefaa.di.module.MessageReplayModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-17.
 */
@PerActivity
@Component(modules = [MessageReplayModule::class])
public interface MessageReplayComponent {
    fun inject(messagesReplayActivity: MessageReplayActivity)
}