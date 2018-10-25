package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.models.AppointmentUrgentModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.AppointmentsConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorAppointmentUrgentListener;

/**
 * Created by Adhamkh on 2018-10-18.
 */

public class DoctorAppointmentUrgentAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnDoctorAppointmentUrgentListener onDoctorAppointmentUrgentListener;
    private AppointmentUrgentModel appointmentUrgentModel;

    public DoctorAppointmentUrgentAsync(String url, AppointmentUrgentModel appointmentUrgentModel
            , OnDoctorAppointmentUrgentListener onDoctorAppointmentUrgentListener) {
        this.url = url;
        this.onDoctorAppointmentUrgentListener = onDoctorAppointmentUrgentListener;
        this.appointmentUrgentModel = appointmentUrgentModel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorAppointmentUrgentListener.onDoctorAppointmentUrgentResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String jSon = new Gson().toJson(appointmentUrgentModel);
        return AppointmentsConnections.getJsonPostWithDataJson(url, jSon, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorAppointmentUrgentListener.onDoctorAppointmentUrgentResponseInternetConnection();
            } else {
                onDoctorAppointmentUrgentListener.onDoctorAppointmentUrgentResponseSuccessFully();
            }
            return;
        } catch (Exception ex) {
        }
        onDoctorAppointmentUrgentListener.onDoctorAppointmentUrgentResponseInternetConnection();
    }
}
