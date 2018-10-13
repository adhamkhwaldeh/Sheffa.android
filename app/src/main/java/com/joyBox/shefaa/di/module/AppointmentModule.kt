package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Adhamkh on 2018-09-25.
 */
@Module
class AppointmentModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getAppointmentPresenter(): AppointmentPresenter {
        return AppointmentPresenter(provideContext())
    }

}