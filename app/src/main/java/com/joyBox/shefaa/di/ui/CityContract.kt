package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.CityEntity
import com.joyBox.shefaa.enums.CityEnum


class CityContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadCityList(cityEnum: CityEnum)
    }

    interface View : BaseContract.View {
        fun onCityListLoaded(cityList: MutableList<CityEntity>)
    }

}