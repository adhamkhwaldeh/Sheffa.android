package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MessageResult

class MessageReplayContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun submitReplay(url: String)
    }

    interface View : BaseContract.View {
        fun onMessagesLoadedSuccesfuly()
        fun onMessagesLoadedFail(messageResult: MessageResult)
    }
}