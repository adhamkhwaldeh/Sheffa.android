package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MainActivity
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-19.
 */
@PerActivity
@Component(modules = [MessageModule::class])
public interface MainComponent {
    fun inject(mainActivity: MainActivity)
}