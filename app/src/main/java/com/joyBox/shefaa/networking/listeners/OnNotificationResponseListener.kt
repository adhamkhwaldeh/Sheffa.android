package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.NotificationEntity

/**
 * Created by Adhamkh on 2018-08-18.
 */
interface OnNotificationResponseListener {
    fun onNotificationResponseLoading();

    fun onNotificationResponseInternetConnection()

    fun onNotificationResponseSuccessFuly(notificationList: MutableList<NotificationEntity>)

    fun onNotificationResponseNoData()
}