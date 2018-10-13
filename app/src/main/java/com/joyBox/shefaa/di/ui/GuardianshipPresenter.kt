package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.GuardianshipEntity
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.networking.listeners.OnGuardianshipListener
import com.joyBox.shefaa.networking.tasks.GuardianshipAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-10-05.
 */
class GuardianshipPresenter constructor(val context: Context) : GuardianshipContract.Presenter {
    private val subscriptions = CompositeDisposable()
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

    override fun unSubscribe() {

    }
}