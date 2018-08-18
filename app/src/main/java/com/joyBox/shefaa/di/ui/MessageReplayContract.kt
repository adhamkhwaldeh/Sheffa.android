package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MessageResult

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessageReplayContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun submitReplay(url: String)
    }

    interface View : BaseContract.View {
        fun onMessagesLoadedSuccesfuly()
        fun onMessagesLoadedFail(messageResult: MessageResult)
    }
}