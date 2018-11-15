package com.joyBox.shefaa

import android.support.multidex.MultiDexApplication
import com.evernote.android.job.JobApi
import com.evernote.android.job.JobConfig
import com.evernote.android.job.JobManager
import com.joyBox.shefaa.localJobs.MainJobCreator

class App : MultiDexApplication() {

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        JobManager.create(this).addJobCreator(MainJobCreator())
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
//            JobManager.instance().config.isAllowSmallerIntervalsForMarshmallow = true
        JobConfig.setApiEnabled(JobApi.getDefault(this), false)

    }

}