package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.GuardianshipAutoComplete

/**
 * Created by Adhamkh on 2018-11-04.
 */
interface OnGuardianshipAutoCompleteListener {
    fun onGuardianshipAutoCompleteLoading()

    fun onGuardianshipAutoCompleteInternetConnection()

    fun onGuardianshipAutoCompleteSuccessFully(guardianshipAutoCompleteList: List<GuardianshipAutoComplete>)

    fun onGuardianshipAutoCompleteNoData()
}