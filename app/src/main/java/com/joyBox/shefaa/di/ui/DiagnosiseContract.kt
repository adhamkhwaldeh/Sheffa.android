package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.DiagnosiseAutoComplete

/**
 * Created by Adhamkh on 2018-10-24.
 */
class DiagnosiseContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadDiagnosiseAutoComplete()

    }

    interface View : BaseContract.View {
        fun showDiagnosiseAutoCompleteProgress(show: Boolean)
        fun showDiagnosiseAutoCompleteEmptyView(visible: Boolean)
        fun showDiagnosiseAutoCompleteLoadErrorMessage(visible: Boolean)
        fun onDiagnosiseAutoCompleteSuccessfully(diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>)
    }
}