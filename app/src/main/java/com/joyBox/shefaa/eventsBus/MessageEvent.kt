package com.joyBox.shefaa.eventsBus

data class MessageEvent(val action: String,
                        val message: Any)