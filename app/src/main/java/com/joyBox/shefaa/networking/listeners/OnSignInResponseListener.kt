package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.Client

interface OnSignInResponseListener {
    fun SignInLoading()
    fun SignInFail()
    fun SignInSuccessFuly(client: Client)
    fun SignInInternetConnection()
}