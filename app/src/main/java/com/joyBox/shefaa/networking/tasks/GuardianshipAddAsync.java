package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GuardianshipConnections;
import com.joyBox.shefaa.networking.listeners.OnGuardianshipAddListener;

public class GuardianshipAddAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnGuardianshipAddListener onGuardianshipAddListener;

    public GuardianshipAddAsync(String url, OnGuardianshipAddListener onGuardianshipAddListener) {
        this.url = url;
        this.onGuardianshipAddListener = onGuardianshipAddListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onGuardianshipAddListener.onGuardianshipAddLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GuardianshipConnections.getJsonPost(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onGuardianshipAddListener.onGuardianshipAddInternetConnection();
            } else {
                onGuardianshipAddListener.onGuardianshipAddSuccessfully();
            }
            return;
        } catch (Exception ex) {

        }
        onGuardianshipAddListener.onGuardianshipAddInternetConnection();
    }

}
