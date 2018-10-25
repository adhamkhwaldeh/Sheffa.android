package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.DiagnosiseAutoComplete

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnDiagnosiseAutoCompleteListener {
    fun onDiagnosiseAutoCompleteLoading()

    fun onDiagnosiseAutoCompleteInternetConnection()

    fun onDiagnosiseAutoCompleteSuccessFully(diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>)

    fun onDiagnosiseAutoCompleteNoData()
}