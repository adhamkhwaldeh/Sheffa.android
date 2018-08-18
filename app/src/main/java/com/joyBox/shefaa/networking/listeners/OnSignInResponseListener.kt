package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.Client

/**
 * Created by Adhamkh on 2018-08-10.
 */
interface OnSignInResponseListener {
    fun SignInLoading()
    fun SignInFail()
    fun SignInSuccessFuly(client: Client)
    fun SignInInternetConnection()
}