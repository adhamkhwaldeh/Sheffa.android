package com.joyBox.shefaa.localJobs

import android.content.Context
import android.util.Log
import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator
import com.evernote.android.job.JobManager

class MainJobCreator : JobCreator {

    companion object {
        public class AddReceiver : JobCreator.AddJobCreatorReceiver() {
            override fun addJobCreator(context: Context, manager: JobManager) {
                manager.addJobCreator(MainJobCreator())
                Log.v("", "")
            }
        }
    }

    override fun create(tag: String): Job? {
        when (tag) {
            MainDailySyncJob.StationsSyncJob_Tag -> {
                return MainDailySyncJob()
            }
        }
        return null
    }

}