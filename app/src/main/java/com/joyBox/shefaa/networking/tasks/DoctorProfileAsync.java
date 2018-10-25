package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.DoctorProfile;
import com.joyBox.shefaa.enums.ProfileType;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorProfileListener;

/**
 * Created by Adhamkh on 2018-10-17.
 */

public class DoctorProfileAsync extends AsyncTask<Void, Void, String> {
    private String userId;
    private OnDoctorProfileListener onDoctorProfileListener;

    public DoctorProfileAsync(String userId, OnDoctorProfileListener onDoctorProfileListener) {
        this.userId = userId;
        this.onDoctorProfileListener = onDoctorProfileListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorProfileListener.onDoctorProfileLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MedicalProfile_Url + "?uid=" + userId +
                "&type=" + ProfileType.DOCTOR.getType(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorProfileListener.onDoctorProfileLoading();
            } else {
                DoctorProfile medicalProfile = new Gson().fromJson(s, DoctorProfile.class);
                if (medicalProfile != null) {
                    onDoctorProfileListener.onDoctorProfileSuccessFully(medicalProfile);
                } else {
                    onDoctorProfileListener.onDoctorProfileNoData();
                }
            }
            return;
        } catch (Exception ex) {
            Log.e("Parsing Error", ex.getMessage());
        }
        onDoctorProfileListener.onDoctorProfileInternetConnection();
    }

}

