package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MainProfile

/**
 * Created by Adhamkh on 2018-09-29.
 */
interface OnMainProfileListener {
    fun onMainProfileLoading()

    fun onMainProfileInternetConnection()

    fun onMainProfileSuccessFully(mainProfile: MainProfile)

    fun onMainProfileNoData()
}