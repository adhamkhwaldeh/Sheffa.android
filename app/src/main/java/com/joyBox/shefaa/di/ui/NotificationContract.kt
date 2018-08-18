package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.NotificationEntity

/**
 * Created by Adhamkh on 2018-08-16.
 */
class NotificationContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun registerToken(client: Client, token: String)
        fun loadNotifications()
    }

    interface View : BaseContract.View {
        fun onNotificationsLoaded(notifications: MutableList<NotificationEntity>)
        fun onRegisterTokenSuccessfuly()
        fun onRegisterTokenFail()
    }
}