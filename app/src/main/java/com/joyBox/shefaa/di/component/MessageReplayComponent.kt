package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MessageReplayActivity
import com.joyBox.shefaa.di.module.MessageReplayModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [MessageReplayModule::class])
interface MessageReplayComponent {
    fun inject(messagesReplayActivity: MessageReplayActivity)
}