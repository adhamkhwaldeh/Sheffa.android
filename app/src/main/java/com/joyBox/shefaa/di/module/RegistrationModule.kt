package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Adhamkh on 2018-08-10.
 */
@Module
class RegistrationModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getRegistrationPresenter(): RegistrationPresenter {
        return RegistrationPresenter(provideContext())
    }

}