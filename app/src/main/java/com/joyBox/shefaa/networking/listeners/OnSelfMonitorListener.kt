package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.SelfMonitorEntity

/**
 * Created by Adhamkh on 2018-10-05.
 */
interface OnSelfMonitorListener {
    fun onSelfMonitorListResponseLoading()

    fun onSelfMonitorListResponseInternetConnection()

    fun onSelfMonitorListResponseSuccessFully(selfMonitorList: List<SelfMonitorEntity>)

    fun onSelfMonitorListResponseNoData()
}