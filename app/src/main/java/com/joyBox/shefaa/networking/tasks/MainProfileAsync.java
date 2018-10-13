package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MainProfile;
import com.joyBox.shefaa.enums.ProfileType;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMainProfileListener;

public class MainProfileAsync extends AsyncTask<Void, Void, String> {
    private String userId;
    private OnMainProfileListener onMainProfileListener;

    public MainProfileAsync(String userId, OnMainProfileListener onMainProfileListener) {
        this.userId = userId;
        this.onMainProfileListener = onMainProfileListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMainProfileListener.onMainProfileLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MedicalProfile_Url + "?uid=" + userId +
                "&type=" + ProfileType.MAIN.getType(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMainProfileListener.onMainProfileLoading();
            } else {
                MainProfile medicalProfile = new Gson().fromJson(s, MainProfile.class);
                if (medicalProfile != null) {
                    onMainProfileListener.onMainProfileSuccessFully(medicalProfile);
                } else {
                    onMainProfileListener.onMainProfileNoData();
                }
            }
            return;
        } catch (Exception ex) {
            Log.e("Parsing Error", ex.getMessage());
        }
        onMainProfileListener.onMainProfileInternetConnection();
    }

}

