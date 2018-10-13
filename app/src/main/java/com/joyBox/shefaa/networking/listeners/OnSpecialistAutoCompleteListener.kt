package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.SpecialistAutoComplete


interface OnSpecialistAutoCompleteListener {
    fun onSpecialistAutoLoading()

    fun onSpecialistAutoInternetConnection()

    fun onSpecialiestAutoSuccessFully(specialistList: MutableList<SpecialistAutoComplete>)

    fun onSpecialiestAutoNoData()
}