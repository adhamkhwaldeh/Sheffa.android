package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.TermsAndConditionsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Adhamkh on 2018-08-17.
 */
@Module
class TermsAndConditionsModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getTermsAndConditionsPresenter(): TermsAndConditionsPresenter {
        return TermsAndConditionsPresenter(provideContext())
    }

}