package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MessageEntity

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessagesContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMessages(url: String)

    }

    interface View : BaseContract.View {
        fun onMessagesLoaded(messageList: MutableList<MessageEntity>)
    }
}