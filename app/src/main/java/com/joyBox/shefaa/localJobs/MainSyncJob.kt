package com.joyBox.shefaa.localJobs

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.JoyBox.Shefaa.R
import com.evernote.android.job.Job
import com.joyBox.shefaa.activities.MainActivity
import java.util.*

class MainSyncJob : Job() {

    companion object {
        val PraySyncJob_Tag = "PraySyncJob"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRunJob(params: Params): Result {

//        val prayResourceId: Int = NotificationHelper.getPraynameRes(params.extras.getInt("prayStringId", PrayType.Fajr.int)!!)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(PraySyncJob_Tag, "Reminder title" /*context.resources.getString(prayResourceId)*/, NotificationManager.IMPORTANCE_LOW);
            channel.description = "Reminder" //context.resources.getString(R.string.TimeOfPray)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

//        val soundname = "azan1" // you can change it dynamically
//        var res_sound_id = context.resources.getIdentifier(soundname, "raw", context.packageName)
//        val u = Uri.parse("android.resource://" + context.packageName + "/" + res_sound_id)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context,
                0, Intent(context, MainActivity::class.java /*RingAlarmActivity::class.java*/), 0)
        val notification = NotificationCompat.Builder(context, MainSyncJob.PraySyncJob_Tag)
                .setContentTitle("Title"/*context.resources.getString(prayResourceId)*/)
                .setContentText("Content"/*context.resources.getString(R.string.TimeOfPray)*/)
                .setAutoCancel(true)
                .setChannelId(MainSyncJob.PraySyncJob_Tag)
//                .setSound(u)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_logo_web)
                .setShowWhen(true)
                .setColor(Color.GREEN)
                .setLocalOnly(true)
                .setOngoing(false).build()

//        notification.flags = notification.flags or Notification.FLAG_INSISTENT or
//                Notification.FLAG_AUTO_CANCEL or Notification.DEFAULT_SOUND or Notification.FLAG_NO_CLEAR

        NotificationManagerCompat.from(context).notify(Random().nextInt(), notification)
        return Result.SUCCESS

    }

}