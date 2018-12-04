package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.CityEntity
import com.joyBox.shefaa.enums.CityEnum
import com.joyBox.shefaa.networking.listeners.OnCityListListener
import com.joyBox.shefaa.networking.tasks.CityAsync


class CityPresenter constructor(val context: Context) : CityContract.Presenter {
    private lateinit var view: CityContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: CityContract.View) {
        this.view = view
    }

    override fun loadCityList(cityEnum: CityEnum) {
        view.showProgress(true)
        CityAsync(cityEnum, object : OnCityListListener {

            override fun onCityListLoading() {
                view.showProgress(false)
            }

            override fun onCityListInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onCityListSuccessFully(cityList: MutableList<CityEntity>) {
                view.onCityListLoaded(cityList)
            }

            override fun onCityListNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }

        }).execute()
    }

    override fun unSubscribe() {

    }

}