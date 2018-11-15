package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.AttachmentPresenter
import dagger.Module
import dagger.Provides

@Module
class AttachmentModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getAttachmentPresenter(): AttachmentPresenter {
        return AttachmentPresenter(provideContext())
    }

}