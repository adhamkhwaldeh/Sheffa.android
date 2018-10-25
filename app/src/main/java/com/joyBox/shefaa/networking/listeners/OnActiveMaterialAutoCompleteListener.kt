package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.ActiveMaterialAutoComplete

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnActiveMaterialAutoCompleteListener {
    fun onActiveMaterialAutoCompleteLoading()

    fun onActiveMaterialAutoCompleteInternetConnection()

    fun onActiveMaterialAutoCompleteSuccessFully(alertMedicalList: List<ActiveMaterialAutoComplete>)

    fun onActiveMaterialAutoCompleteNoData()
}