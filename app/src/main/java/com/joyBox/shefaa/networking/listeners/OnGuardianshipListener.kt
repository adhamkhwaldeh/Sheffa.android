package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.GuardianshipEntity

/**
 * Created by Adhamkh on 2018-10-05.
 */
interface OnGuardianshipListener {
    fun onGuardianshipResponseLoading()

    fun onGuardianshipResponseInternetConnection()

    fun onGuardianshipResponseSuccessFully(guardianshipEntityList: List<GuardianshipEntity>)

    fun onGuardianshipResponseNoData()
}