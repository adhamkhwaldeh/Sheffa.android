package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.TermsAndConditionsModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.TermsAndConditionsDialog
import dagger.Component


@PerActivity
@Component(modules = [TermsAndConditionsModule::class])
interface TermsAndConditionsComponent {
    fun inject(termsAndConditionsDialog: TermsAndConditionsDialog)
}