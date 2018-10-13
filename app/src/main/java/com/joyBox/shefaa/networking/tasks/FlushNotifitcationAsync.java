package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.NotificationConnections;
import com.joyBox.shefaa.networking.listeners.OnFlushNotificationListener;

/**
 * Created by Adhamkh on 2018-10-13.
 */

public class FlushNotifitcationAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnFlushNotificationListener onFlushNotificationListener;

    public FlushNotifitcationAsync(String url, OnFlushNotificationListener onFlushNotificationListener) {
        this.url = url;
        this.onFlushNotificationListener = onFlushNotificationListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onFlushNotificationListener.onFlushNotificationLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return NotificationConnections.flushNotification(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onFlushNotificationListener.onFlushNotificationInternetConnection();
            } else {
                if (s.contains("1")) {
                    onFlushNotificationListener.onFlushNotificationSuccessFully();
                } else {
                    onFlushNotificationListener.onFlushNotificationFail();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onFlushNotificationListener.onFlushNotificationInternetConnection();
    }

}
