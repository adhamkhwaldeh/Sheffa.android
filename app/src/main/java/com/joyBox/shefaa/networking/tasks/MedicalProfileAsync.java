package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MedicalProfile;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMedicalProfileListener;

public class MedicalProfileAsync extends AsyncTask<Void, Void, String> {

    private String userId;
    private String profileType;
    private OnMedicalProfileListener onMedicalProfileListener;

    public MedicalProfileAsync(String userId, String profileType, OnMedicalProfileListener onMedicalProfileListener) {
        this.userId = userId;
        this.profileType = profileType;
        this.onMedicalProfileListener = onMedicalProfileListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMedicalProfileListener.onMedicalProfileLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MedicalProfile_Url + "?uid=" + userId + "&type=" + profileType, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMedicalProfileListener.onMedicalProfileInternetConnection();
            } else {
                MedicalProfile medicalProfile = new Gson().fromJson(s, MedicalProfile.class);
                if (medicalProfile != null) {
                    onMedicalProfileListener.onMedicalProfileSuccessFully(medicalProfile);
                } else {
                    onMedicalProfileListener.onMedicalProfileNoData();
                }
            }
            return;
        } catch (Exception ex) {
            Log.e("Parsing Error", ex.getMessage());
        }
        onMedicalProfileListener.onMedicalProfileInternetConnection();
    }

}

