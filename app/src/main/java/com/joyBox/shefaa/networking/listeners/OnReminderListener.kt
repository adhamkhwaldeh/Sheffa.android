package com.joyBox.shefaa.networking.listeners

/**
 * Created by Adhamkh on 2018-10-29.
 */
interface OnReminderListener {
    fun onReminderLoading()

    fun onReminderInternetConnection()

    fun onReminderSuccessfully(stringList:MutableList<String>)

    fun onReminderNoData()
}