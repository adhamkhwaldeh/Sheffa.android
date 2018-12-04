package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.CityEntity

interface OnCityListListener {
    fun onCityListLoading()

    fun onCityListInternetConnection()

    fun onCityListSuccessFully(cityList: MutableList<CityEntity>)

    fun onCityListNoData()
}