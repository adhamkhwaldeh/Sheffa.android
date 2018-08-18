package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.NotificationsActivity
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-18.
 */
@PerActivity
@Component(modules = [NotificationModule::class])
public interface NotificationComponent {
    fun inject(notificationsActivity: NotificationsActivity)
}