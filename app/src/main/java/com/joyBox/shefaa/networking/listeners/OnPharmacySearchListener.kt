package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.Pharmacy

/**
 * Created by Adhamkh on 2018-10-12.
 */
interface OnPharmacySearchListener {
    fun onPharmacyLoading()

    fun onPharmacyInternetConnection()

    fun onPharmacySuccessFully(pharmacyList: MutableList<Pharmacy> /*pharmacyAutoCompleteList: List<PharmacyAutoComplete>*/)

    fun onPharmacyNoData()
}