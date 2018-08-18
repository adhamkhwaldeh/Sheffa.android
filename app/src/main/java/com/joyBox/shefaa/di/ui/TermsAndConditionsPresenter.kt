package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.TermsAndConditionsEntity
import com.joyBox.shefaa.networking.listeners.OnTermsAndConditionResponseListener
import com.joyBox.shefaa.networking.tasks.TermsAndConditionsAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-17.
 */
class TermsAndConditionsPresenter constructor(val context: Context) : TermsAndConditionsContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: TermsAndConditionsContract.View

    init {

    }

    override fun subscribe() {

    }


    override fun attachView(view: TermsAndConditionsContract.View) {
        this.view = view
    }

    override fun loadTermsAndConditions(url: String) {

        TermsAndConditionsAsync(url, object : OnTermsAndConditionResponseListener {
            override fun onTermsAndConditionResponseLoading() {
                view.showProgress(true)
            }

            override fun onTermsAndConditionResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onTermsAndConditionResponseSuccessFuly(termsAndConditionsEntity: TermsAndConditionsEntity) {
                view.showProgress(false)
                view.onTermsAndConditionsLoaded(termsAndConditionsEntity)
            }

        }).execute();

    }
    override fun unSubscribe() {

    }


}