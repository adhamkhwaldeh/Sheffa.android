package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.TermsAndConditionsEntity

/**
 * Created by Adhamkh on 2018-08-17.
 */
class TermsAndConditionsContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadTermsAndConditions(url: String)

    }

    interface View : BaseContract.View {
        fun onTermsAndConditionsLoaded(termsAndConditions: TermsAndConditionsEntity)
    }

}