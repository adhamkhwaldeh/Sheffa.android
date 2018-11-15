package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentReserveListener;

/**
 * Created by Adhamkh on 2018-10-27.
 */

public class AppointmentReserveAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnAppointmentReserveListener onAppointmentReserveListener;

    public AppointmentReserveAsync(String url, OnAppointmentReserveListener onAppointmentReserveListener) {
        this.url = url;
        this.onAppointmentReserveListener = onAppointmentReserveListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAppointmentReserveListener.onAppointmentReserveLoading();
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
                onAppointmentReserveListener.onAppointmentReserveInternetConnection();
                return;
            }
            if (s.contains("Successfully"))
                onAppointmentReserveListener.onAppointmentReserveSuccessFully();
            else
                onAppointmentReserveListener.onAppointmentReserveFailed();
            return;
        } catch (Exception ex) {
        }
        onAppointmentReserveListener.onAppointmentReserveInternetConnection();
    }
}
