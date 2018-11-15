package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.NotificationsActivity
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [NotificationModule::class])
interface NotificationComponent {
    fun inject(notificationsActivity: NotificationsActivity)
}