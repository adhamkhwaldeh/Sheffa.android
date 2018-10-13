package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.GuardianshipPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Adhamkh on 2018-10-05.
 */
@Module
class GuardianshipModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getGuardianshipPresenter(): GuardianshipPresenter {
        return GuardianshipPresenter(provideContext())
    }

}