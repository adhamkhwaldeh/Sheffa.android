package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete
import com.joyBox.shefaa.networking.listeners.OnDiagnosiseAutoCompleteListener
import com.joyBox.shefaa.networking.tasks.DiagnosiseAutoCompleteAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-10-24.
 */
class DiagnosisePresenter constructor(val context: Context) : DiagnosiseContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: DiagnosiseContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun unSubscribe() {

    }

    override fun attachView(view: DiagnosiseContract.View) {
        this.view = view
    }

    override fun loadDiagnosiseAutoComplete() {
        DiagnosiseAutoCompleteAsync(object : OnDiagnosiseAutoCompleteListener {
            override fun onDiagnosiseAutoCompleteLoading() {
                view.showDiagnosiseAutoCompleteProgress(true)
            }

            override fun onDiagnosiseAutoCompleteInternetConnection() {
                view.showDiagnosiseAutoCompleteProgress(false)
                view.showDiagnosiseAutoCompleteLoadErrorMessage(true)
            }

            override fun onDiagnosiseAutoCompleteNoData() {
                view.showDiagnosiseAutoCompleteProgress(false)
                view.showDiagnosiseAutoCompleteEmptyView(true)
            }
            override fun onDiagnosiseAutoCompleteSuccessFully(diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>) {
                view.onDiagnosiseAutoCompleteSuccessfully(diagnosiseAutoCompleteList)
            }

        }).execute()
    }

}