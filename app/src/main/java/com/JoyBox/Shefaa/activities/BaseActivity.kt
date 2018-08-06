package com.JoyBox.Shefaa.activities

import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate

abstract class BaseActivity : AppCompatActivity() {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


}