package com.joyBox.shefaa.networking.listeners


interface OnTestResultAddListener {
    fun onTestResultAddLoading()

    fun onTestResultAddInternetConnection()

    fun onTestResultAddSuccessFully()

    fun onTestResultAddFail()
}