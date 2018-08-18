package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.NotificationEntity;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.connections.NotificationConnections;
import com.joyBox.shefaa.networking.listeners.OnNotificationResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-18.
 */

public class OnNotificationAsync extends AsyncTask<Void, Void, String> {
    String url;

    OnNotificationResponseListener onNotificationResponseListener;

    public OnNotificationAsync(String url, OnNotificationResponseListener onNotificationResponseListener) {
        this.url = url;
        this.onNotificationResponseListener = onNotificationResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return NotificationConnections.getNotificationsList(url, NetworkingHelper.RequestTimeout);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onNotificationResponseListener.onNotificationResponseLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onNotificationResponseListener.onNotificationResponseInternetConnection();
        } else {
            try {
                List<NotificationEntity> NotificationEntityList = Arrays.asList(new Gson().fromJson(s, NotificationEntity[].class));
                if (NotificationEntityList.size() > 0)
                    onNotificationResponseListener.onNotificationResponseSuccessFuly(NotificationEntityList);
                else
                    onNotificationResponseListener.onNotificationResponseNoData();
                return;
            } catch (Exception ex) {

            }
            onNotificationResponseListener.onNotificationResponseInternetConnection();
        }
    }
}

