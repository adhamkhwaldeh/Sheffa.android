package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.GuardianshipAutoComplete
import com.joyBox.shefaa.entities.GuardianshipEntity
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.networking.listeners.OnGuardianshipAddListener
import com.joyBox.shefaa.networking.listeners.OnGuardianshipAutoCompleteListener
import com.joyBox.shefaa.networking.listeners.OnGuardianshipListener
import com.joyBox.shefaa.networking.tasks.GuardianshipAddAsync
import com.joyBox.shefaa.networking.tasks.GuardianshipAsync
import com.joyBox.shefaa.networking.tasks.GuardianshipAutoCompleteAsync


class GuardianshipPresenter constructor(val context: Context) : GuardianshipContract.Presenter {
    private lateinit var view: GuardianshipContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: GuardianshipContract.View) {
        this.view = view
    }

    override fun loadGuardianshipList(url: String) {
        GuardianshipAsync(NetworkingHelper.MyGuardiansListUrl, object : OnGuardianshipListener {
            override fun onGuardianshipResponseLoading() {
                view.showProgress(true)
            }

            override fun onGuardianshipResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onGuardianshipResponseSuccessFully(guardianshipEntityList: List<GuardianshipEntity>) {
                view.showProgress(false)
                view.onGuardianshipListLoaded(guardianshipList = guardianshipEntityList)
            }

            override fun onGuardianshipResponseNoData() {
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun loadAutoCompleteList() {
        GuardianshipAutoCompleteAsync(object : OnGuardianshipAutoCompleteListener {
            override fun onGuardianshipAutoCompleteLoading() {
                view.showProgress(true)
            }

            override fun onGuardianshipAutoCompleteInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onGuardianshipAutoCompleteNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }

            override fun onGuardianshipAutoCompleteSuccessFully(guardianshipAutoCompleteList: List<GuardianshipAutoComplete>) {
                view.showProgress(false)
                view.onGuardianshipAutoCompleteListLoaded(guardianshipAutoCompleteList.toMutableList())
            }
        }).execute()
    }

    override fun addGuardianship(url: String) {
        GuardianshipAddAsync(url, object : OnGuardianshipAddListener {
            override fun onGuardianshipAddLoading() {
                view.showProgress(true)
            }

            override fun onGuardianshipAddInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onGuardianshipAddSuccessfully() {
                view.showProgress(false)
                view.onGuardianshipAddedSuccessfully()
            }

            override fun onGuardianshipAddFailed() {
                view.showProgress(false)
                view.onGuardianshipAddedFail()
            }
        }).execute()

    }

    override fun unSubscribe() {

    }
}