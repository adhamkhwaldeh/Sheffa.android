package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.Doctor;
import com.joyBox.shefaa.entities.DoctorAutoComplete;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorAutoCompleteResponseListener;
import com.joyBox.shefaa.networking.listeners.OnDoctorSearchListener;

import java.util.Arrays;
import java.util.List;

public class DoctorSearchAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnDoctorSearchListener onDoctorSearchListener;

    public DoctorSearchAsync(String url, OnDoctorSearchListener onDoctorSearchListener) {
        this.url = url;
        this.onDoctorSearchListener = onDoctorSearchListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorSearchListener.onDoctorLoading();
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
                onDoctorSearchListener.onDoctorInternetConnection();
            } else {
                List<Doctor> doctorList = JsonParser.getDoctors(s);
                //Arrays.asList(new Gson().fromJson(s, DoctorAutoComplete[].class));
                if (doctorList.size() > 0) {
                    onDoctorSearchListener.onDoctorSuccessFully(doctorList);
                } else {
                    onDoctorSearchListener.onDoctorNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onDoctorSearchListener.onDoctorInternetConnection();
    }
}

