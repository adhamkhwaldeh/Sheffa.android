package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.models.AppointmentShiftModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.AppointmentsConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentShiftListener;
import com.joyBox.shefaa.networking.listeners.OnDoctorAppointmentShiftListener;

/**
 * Created by Adhamkh on 2018-10-18.
 */

public class DoctorAppointmentShiftAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnDoctorAppointmentShiftListener onAppointmentShiftListener;
    private AppointmentShiftModel appointmentShiftModel;

    public DoctorAppointmentShiftAsync(String url, AppointmentShiftModel appointmentShiftModel,
                                       OnDoctorAppointmentShiftListener onAppointmentShiftListener) {
        this.url = url;
        this.onAppointmentShiftListener = onAppointmentShiftListener;
        this.appointmentShiftModel = appointmentShiftModel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAppointmentShiftListener.onAppointmentShiftResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String jSon = new Gson().toJson(appointmentShiftModel);
        return AppointmentsConnections.getJsonPostWithDataJson(url, jSon, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onAppointmentShiftListener.onAppointmentShiftResponseInternetConnection();
            } else {
                onAppointmentShiftListener.onAppointmentShiftResponseSuccessFully();
            }
            return;
        } catch (Exception ex) {
        }
        onAppointmentShiftListener.onAppointmentShiftResponseInternetConnection();
    }


}
