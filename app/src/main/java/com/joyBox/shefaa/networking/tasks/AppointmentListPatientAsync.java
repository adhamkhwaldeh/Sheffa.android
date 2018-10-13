package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.joyBox.shefaa.entities.AppointmentEntity;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentListResponseListener;

import java.util.List;


public class AppointmentListPatientAsync extends AsyncTask<Void, Void, String> {

    private String patientId;
    private OnAppointmentListResponseListener onAppointmentListResponseListener;

    public AppointmentListPatientAsync(String patientId, OnAppointmentListResponseListener onAppointmentListResponseListener) {
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
            List<AppointmentEntity> list = JsonParser.getAppointment(s);//Arrays.asList(new Gson().fromJson(s, AppointmentEntity[].class));
            if (list.size() > 0)
                onAppointmentListResponseListener.onAppointmentResponseSuccessFully(list);
            else
                onAppointmentListResponseListener.onAppointmentResponseNoData();
            return;
        } catch (Exception ex) {
            Log.v("","");
        }
        onAppointmentListResponseListener.onAppointmentResponseInternetConnection();
    }
}
