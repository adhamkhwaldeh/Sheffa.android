package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.ReminderConnections;
import com.joyBox.shefaa.networking.listeners.OnReminderListener;

import java.util.Arrays;
import java.util.List;


public class ReminderAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnReminderListener onReminderListener;

    public ReminderAsync(String url, OnReminderListener onReminderListener) {
        this.url = url;
        this.onReminderListener = onReminderListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onReminderListener.onReminderLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return ReminderConnections.getJsonPost(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onReminderListener.onReminderInternetConnection();
            } else {
                List<String> stringList = Arrays.asList(new Gson().fromJson(s, String[].class));
                if (stringList.size() > 0)
                    onReminderListener.onReminderSuccessfully(stringList);
                else {
                    onReminderListener.onReminderNoData();
                }
            }
        } catch (Exception ex) {
            onReminderListener.onReminderInternetConnection();
        }

    }
}
