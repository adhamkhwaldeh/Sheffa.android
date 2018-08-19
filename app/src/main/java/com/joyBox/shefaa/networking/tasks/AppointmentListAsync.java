package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.AppointmentEntity;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentListResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-19.
 */

public class AppointmentListAsync extends AsyncTask<Void, Void, String> {

    public String patientId;
    OnAppointmentListResponseListener onAppointmentListResponseListener;

    public AppointmentListAsync(String patientId, OnAppointmentListResponseListener onAppointmentListResponseListener) {
        this.patientId = patientId;
        this.onAppointmentListResponseListener = onAppointmentListResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.AppointmentPatientListUrl + patientId,
                NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAppointmentListResponseListener.onAppointmentResponseLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            List<AppointmentEntity> list = Arrays.asList(new Gson().fromJson(s, AppointmentEntity.class));
            if (list.size() > 0)
                onAppointmentListResponseListener.onAppointmentResponseSuccessFuly(list);
            else
                onAppointmentListResponseListener.onAppointmentResponseNoData();
            return;
        } catch (Exception ex) {

        }
        onAppointmentListResponseListener.onAppointmentResponseInternetConnection();
    }
}
