package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.TermsAndConditionsEntity

/**
 * Created by Adhamkh on 2018-08-17.
 */
interface OnTermsAndConditionResponseListener {
    fun onTermsAndConditionResponseLoading();

    fun onTermsAndConditionResponseInternetConnection()

    fun onTermsAndConditionResponseSuccessFuly(termsAndConditionsEntity: TermsAndConditionsEntity)

}