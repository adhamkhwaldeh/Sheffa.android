package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.PatientProfile;
import com.joyBox.shefaa.enums.ProfileType;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnPatientProfileListener;

/**
 * Created by Adhamkh on 2018-09-29.
 */

public class PatientProfileAsync extends AsyncTask<Void, Void, String> {
    private String userId;
    private OnPatientProfileListener onPatientProfileListener;

    public PatientProfileAsync(String userId, OnPatientProfileListener onPatientProfileListener) {
        this.userId = userId;
        this.onPatientProfileListener = onPatientProfileListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPatientProfileListener.onPatientProfileoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MedicalProfile_Url + "?uid=" + userId +
                "&type=" + ProfileType.PATIENT.getType(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPatientProfileListener.onPatientProfileoading();
            } else {
                PatientProfile patientProfile = new Gson().fromJson(s, PatientProfile.class);
                if (patientProfile != null) {
                    onPatientProfileListener.onPatientProfileSuccessFully(patientProfile);
                } else {
                    onPatientProfileListener.onPatientProfileNoData();
                }
            }
            return;
        } catch (Exception ex) {
            Log.e("Parsing Error", ex.getMessage());
        }
        onPatientProfileListener.onPatientProfileInternetConnection();
    }

}


