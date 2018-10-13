package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.DoctorPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Adhamkh on 2018-08-22.
 */
@Module
class DoctorModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getDoctorPresenter(): DoctorPresenter {
        return DoctorPresenter(provideContext())
    }

}