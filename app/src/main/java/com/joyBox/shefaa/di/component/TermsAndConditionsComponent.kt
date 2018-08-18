package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.TermsAndConditionsModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.TermsAndConditionsDialog
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-17.
 */
@PerActivity
@Component(modules = [TermsAndConditionsModule::class])
public interface TermsAndConditionsComponent {
    fun inject(termsAndConditionsDialog: TermsAndConditionsDialog)
}