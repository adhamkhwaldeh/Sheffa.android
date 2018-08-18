package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-08-10.
 */
interface OnSignUpResponseListener {
    fun SignUpLoading()
    fun SignUpFail()
    fun SignUpSuccessFuly(/*client: Client*/)
    fun SignUpInternetConnection()
}