package com.joyBox.shefaa.localJobs

import com.evernote.android.job.JobManager
import com.evernote.android.job.JobRequest
import com.evernote.android.job.util.support.PersistableBundleCompat
import java.util.*

/**
 * Created by Adhamkh on 2018-02-15.
 */
class MainJobManager {

    companion object {

        // val tm = pry.PrayDate!!.time - System.currentTimeMillis()
        fun addJob2Scheduler(time: Long) {
            val extras = PersistableBundleCompat()
            val mLastJobId = JobRequest.Builder(MainSyncJob.PraySyncJob_Tag)
                    .setBackoffCriteria(5_000L, JobRequest.BackoffPolicy.EXPONENTIAL)
                    .setExtras(extras)
                    .setExact(time)
                    .build()
                    .schedule()
        }

        fun clearAllPrayes() {
            JobManager.instance().cancelAllForTag(MainSyncJob.PraySyncJob_Tag)
        }

    }

}