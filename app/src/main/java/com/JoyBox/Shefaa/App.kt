package com.joyBox.shefaa

import android.support.multidex.MultiDexApplication

/**
 * Created by Adhamkh on 2018-08-03.
 */
class App : MultiDexApplication() {

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}