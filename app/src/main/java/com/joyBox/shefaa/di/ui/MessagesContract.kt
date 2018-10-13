package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.entities.MessageEntity

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessagesContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMessages(url: String)
        fun loadUnreadMessage(url: String)

        fun loadAutoCompleteUsers(url: String)

    }

    interface View : BaseContract.View {
        fun onMessagesLoaded(messageList: MutableList<MessageEntity>) {}
        fun onUnreadMessagesLoaded(count: String) {}

        fun onAutoCompleteUsersLoaded(messageList: MutableList<AutoCompleteUser>) {}

    }
}