package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.SelfMonitorEntity;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.SelfMonitorConnections;
import com.joyBox.shefaa.networking.listeners.OnSelfMonitorListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-05.
 */

public class SelfMonitorAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnSelfMonitorListener onSelfMonitorListener;

    public SelfMonitorAsync(String url, OnSelfMonitorListener onSelfMonitorListener) {
        this.url = url;
        this.onSelfMonitorListener = onSelfMonitorListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onSelfMonitorListener.onSelfMonitorListResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return SelfMonitorConnections.getJson(NetworkingHelper.SelfMonitorListUrl, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onSelfMonitorListener.onSelfMonitorListResponseInternetConnection();
            } else {
                List<SelfMonitorEntity> selfMonitorEntityList = Arrays.asList(new Gson().fromJson(s, SelfMonitorEntity[].class));
                if (selfMonitorEntityList.size() > 0) {
                    onSelfMonitorListener.onSelfMonitorListResponseSuccessFully(selfMonitorEntityList);
                } else {
                    onSelfMonitorListener.onSelfMonitorListResponseNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onSelfMonitorListener.onSelfMonitorListResponseInternetConnection();
    }
}
