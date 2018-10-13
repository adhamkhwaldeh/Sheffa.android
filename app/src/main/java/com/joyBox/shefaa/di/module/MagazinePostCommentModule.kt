package com.joyBox.shefaa.di.module

import android.app.Activity
import android.content.Context
import com.joyBox.shefaa.di.ui.MagazinePostCommentsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Adhamkh on 2018-08-21.
 */
@Module
class MagazinePostCommentModule constructor(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun getMagazinePostCommentsPresenter(): MagazinePostCommentsPresenter {
        return MagazinePostCommentsPresenter(provideContext())
    }

}