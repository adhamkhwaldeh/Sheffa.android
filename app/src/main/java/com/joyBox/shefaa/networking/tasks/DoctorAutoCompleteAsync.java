package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.DoctorAutoComplete;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorAutoCompleteResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-22.
 */

public class DoctorAutoCompleteAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnDoctorAutoCompleteResponseListener onDoctorAutoCompleteResponseListener;

    public DoctorAutoCompleteAsync(String url, OnDoctorAutoCompleteResponseListener onDoctorAutoCompleteResponseListener) {
        this.url = url;
        this.onDoctorAutoCompleteResponseListener = onDoctorAutoCompleteResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorAutoCompleteResponseListener.onDoctorAutoCompleteResponseLoading();
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
                onDoctorAutoCompleteResponseListener.onDoctorAutoCompleteResponseInternetConnection();
            } else {
                List<DoctorAutoComplete> doctorAutoCompleteList = Arrays.asList(new Gson().fromJson(s, DoctorAutoComplete[].class));
                if (doctorAutoCompleteList.size() > 0) {
                    onDoctorAutoCompleteResponseListener.onDoctorAutoCompleteResponseSuccessFully(doctorAutoCompleteList);
                } else {
                    onDoctorAutoCompleteResponseListener.onDoctorAutoCompleteResponseNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onDoctorAutoCompleteResponseListener.onDoctorAutoCompleteResponseInternetConnection();
    }
}
