package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.Lab;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnLabSearchListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-12.
 */

public class LabSearchAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnLabSearchListener onLabSearchListener;

    public LabSearchAsync(String url, OnLabSearchListener onLabSearchListener) {
        this.url = url;
        this.onLabSearchListener = onLabSearchListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onLabSearchListener.onLabLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onLabSearchListener.onLabInternetConnection();
            } else {
                List<Lab> labList = JsonParser.getLabs(s);
                if (labList.size() > 0) {
                    onLabSearchListener.onLabSuccessFully(labList);
                } else {
                    onLabSearchListener.onLabNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onLabSearchListener.onLabInternetConnection();
    }
}

