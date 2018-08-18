package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.MessageResult
import com.joyBox.shefaa.networking.listeners.OnMessageReplayResponseListener
import com.joyBox.shefaa.networking.tasks.MessageReplayAsync
import com.joyBox.shefaa.networking.tasks.MessagesAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessageReplayPresenter constructor(val context: Context) : MessageReplayContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: MessageReplayContract.View

    init {

    }

    override fun subscribe() {

    }


    override fun attachView(view: MessageReplayContract.View) {
        this.view = view
    }

    override fun submitReplay(url: String) {

        MessageReplayAsync(url, object : OnMessageReplayResponseListener {
            override fun onMessageReplayResponseLoading() {
                view.showProgress(true)
            }

            override fun onMessageReplayResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMessageResponseSuccessFuly() {
                view.showProgress(false)
                view.onMessagesLoadedSuccesfuly()
            }

            override fun onMessageResponseFail(messageResult: MessageResult) {
                view.showProgress(false)
                view.onMessagesLoadedFail(messageResult)
            }
        }).execute();

    }

    override fun unSubscribe() {

    }

}