package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MedicalProfile;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMedicalProfileListener;
import com.joyBox.shefaa.networking.listeners.OnMedicalProfileUpdateListener;

/**
 * Created by Adhamkh on 2018-09-23.
 */

public class MedicalProfileUpdateAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnMedicalProfileUpdateListener onMedicalProfileUpdateListener;

    public MedicalProfileUpdateAsync(String url, OnMedicalProfileUpdateListener onMedicalProfileUpdateListener) {
        this.url = url;
        this.onMedicalProfileUpdateListener = onMedicalProfileUpdateListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMedicalProfileUpdateListener.onMedicalProfileUpdateLoading();
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
                onMedicalProfileUpdateListener.onMedicalProfileUpdateInternetConnection();
            } else {
                onMedicalProfileUpdateListener.onMedicalProfileUpdateSuccessFully();
//                MedicalProfile medicalProfile = new Gson().fromJson(s, MedicalProfile.class);
//                if (medicalProfile != null) {
//                    onMedicalProfileListener.onMedicalProfileSuccessFully(medicalProfile);
//                } else {
//                    onMedicalProfileListener.onMedicalProfileNoData();
//                }
            }
            return;
        } catch (Exception ex) {
            Log.e("Parsing Error", ex.getMessage());
        }
        onMedicalProfileUpdateListener.onMedicalProfileUpdateInternetConnection();
    }

}


