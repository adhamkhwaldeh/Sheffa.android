package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.Lab

/**
 * Created by Adhamkh on 2018-10-12.
 */
interface OnLabSearchListener {
    fun onLabLoading()

    fun onLabInternetConnection()

    fun onLabSuccessFully(labList: MutableList<Lab>)

    fun onLabNoData()

}
    