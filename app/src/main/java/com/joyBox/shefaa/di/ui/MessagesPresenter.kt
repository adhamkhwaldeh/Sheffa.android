package com.joyBox.shefaa.di.ui

import android.content.Context
import android.view.View
import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.entities.MessageEntity
import com.joyBox.shefaa.networking.listeners.OnAutoCompleteUsersListener
import com.joyBox.shefaa.networking.listeners.OnMessageResponseListener
import com.joyBox.shefaa.networking.listeners.OnMessagesUnReadResponseListener
import com.joyBox.shefaa.networking.tasks.AutoCompleteUsersAsync
import com.joyBox.shefaa.networking.tasks.MessagesAsync
import com.joyBox.shefaa.networking.tasks.UnReadMessageAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessagesPresenter constructor(val context: Context) : MessagesContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: MessagesContract.View

    init {

    }

    override fun subscribe() {

    }

    override fun attachView(view: MessagesContract.View) {
        this.view = view
    }

    override fun loadMessages(url: String) {
        MessagesAsync(url, object : OnMessageResponseListener {
            override fun onMessageResponseLoading() {
                view.showProgress(true)
            }

            override fun onMessageResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onMessageResponseSuccessFully(messageList: MutableList<MessageEntity>) {
                view.showProgress(false)
                view.onMessagesLoaded(messageList)
            }

            override fun onMessageResponseNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun loadUnreadMessage(url: String) {
        UnReadMessageAsync(url, object : OnMessagesUnReadResponseListener {
            override fun onMessageUnReadResponseInternetConnection() {

            }

            override fun onMessageUnReadResponseSuccessFuly(count: String) {
                view.onUnreadMessagesLoaded(count)
            }
        }).execute()
    }


    override fun loadAutoCompleteUsers(url: String) {
        AutoCompleteUsersAsync(url, object : OnAutoCompleteUsersListener {
            override fun autoCompleteUsersLoading() {
                view.showProgress(true)
            }

            override fun autoCompleteUsersNoData() {
                view.showEmptyView(true)
            }

            override fun autoCompleteUsersSuccessFully(autoCompleteUserList: MutableList<AutoCompleteUser>) {
                view.showProgress(false)
                view.onAutoCompleteUsersLoaded(autoCompleteUserList)
            }

            override fun autoCompleteUsersInternetConnection() {
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun unSubscribe() {

    }

}