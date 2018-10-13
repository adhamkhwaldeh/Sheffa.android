package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.NotificationEntity
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.networking.listeners.OnFlushNotificationListener
import com.joyBox.shefaa.networking.listeners.OnNotificationResponseListener
import com.joyBox.shefaa.networking.listeners.OnRegisterTokenResponseListener
import com.joyBox.shefaa.networking.tasks.FlushNotifitcationAsync
import com.joyBox.shefaa.networking.tasks.NotificationAsync
import com.joyBox.shefaa.networking.tasks.RegisterNotificationTokenAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-16.
 */
class NotificationPresenter constructor(val context: Context) : NotificationContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: NotificationContract.View

    init {

    }

    override fun subscribe() {

    }

    override fun attachView(view: NotificationContract.View) {
        this.view = view
    }

    override fun loadNotifications() {
        NotificationAsync(NetworkingHelper.MyNotificationsUrl, object : OnNotificationResponseListener {
            override fun onNotificationResponseLoading() {
                view.showProgress(true)
            }

            override fun onNotificationResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onNotificationResponseSuccessFuly(notificationList: MutableList<NotificationEntity>) {
                view.showProgress(false)
                view.onNotificationsLoaded(notificationList)
            }

            override fun onNotificationResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun registerToken(client: Client, token: String) {
        RegisterNotificationTokenAsync(client, token, object : OnRegisterTokenResponseListener {
            override fun onRegisterTokenResponseLoading() {
//                view.showProgress(true)
            }

            override fun onRegisterTokenResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onRegisterTokenResponseSuccessFuly() {
                view.showProgress(false)
                view.onRegisterTokenSuccessfully()
            }

            override fun onRegisterTokenResponseFail() {
                view.showProgress(false)
                view.onRegisterTokenFail()
            }
        }).execute()
    }

    override fun flushToken(url: String) {
        FlushNotifitcationAsync(url, object : OnFlushNotificationListener {
            override fun onFlushNotificationLoading() {
                view.showProgress(true)
            }

            override fun onFlushNotificationInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onFlushNotificationSuccessFully() {
                view.onFlushTokenSuccessfully()
            }

            override fun onFlushNotificationFail() {
                view.onFlushTokenFail()
            }
        }).execute()
    }

    override fun unSubscribe() {

    }

}