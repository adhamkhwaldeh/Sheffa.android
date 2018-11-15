package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorProfileUpdateListener;

/**
 * Created by Adhamkh on 2018-11-14.
 */

public class DoctorProfileUpdateAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnDoctorProfileUpdateListener onDoctorProfileUpdateListener;

    public DoctorProfileUpdateAsync(String url, OnDoctorProfileUpdateListener onDoctorProfileUpdateListener) {
        this.url = url;
        this.onDoctorProfileUpdateListener = onDoctorProfileUpdateListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorProfileUpdateListener.onDoctorProfileUpdateLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJsonPost(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorProfileUpdateListener.onDoctorProfileUpdateInternetConnection();
            } else {
                if (s.contains("success")) {
                    onDoctorProfileUpdateListener.onDoctorProfileUpdateSuccessFully();
                } else {
                    onDoctorProfileUpdateListener.onDoctorProfileUpdateFail();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onDoctorProfileUpdateListener.onDoctorProfileUpdateInternetConnection();
    }
}
