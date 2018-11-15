package com.joyBox.shefaa.localJobs

import android.os.Handler
import android.os.Looper
import com.evernote.android.job.DailyJob
import com.google.gson.Gson

/**
 * Created by Adhamkh on 2018-05-30.
 */
class MainDailySyncJob : DailyJob() {

    companion object {
        val StationsSyncJob_Tag = "StationsSyncJob"
        val StationAlarm_Tag = "StationAlarm"
    }


    override fun onRunDailyJob(params: Params): DailyJobResult {
        val obj: String = params.extras[StationAlarm_Tag] as String
//        Handler(Looper.getMainLooper()).post {
//            val station = Gson().fromJson(obj, StationAlarm::class.java)
//            PlayerServiceUtil.play(PlayableStation(name = station.playableName, url = station.playableUrl))
//        }
        return DailyJobResult.SUCCESS
    }

}