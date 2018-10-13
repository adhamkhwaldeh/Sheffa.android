package com.joyBox.shefaa.networking.listeners

interface OnMainProfileUpdateListener {

    fun onMainProfileUpdateLoading()

    fun onMainProfileUpdateInternetConnection()

    fun onMainProfileUpdateSuccessFully()

    fun onMainProfileUpdateFail()

}