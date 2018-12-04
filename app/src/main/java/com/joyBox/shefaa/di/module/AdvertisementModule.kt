package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.AdvertisementPresenter
import dagger.Module
import dagger.Provides

@Module
class AdvertisementModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getAdvertisementPresenter(): AdvertisementPresenter {
        return AdvertisementPresenter(provideContext())
    }

}