package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.DoctorAppointment;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorAppointmentsListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-17.
 */

public class DoctorAppointmentsAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnDoctorAppointmentsListener onDoctorAppointmentsListener;

    public DoctorAppointmentsAsync(String url, OnDoctorAppointmentsListener onDoctorAppointmentsListener) {
        this.url = url;
        this.onDoctorAppointmentsListener = onDoctorAppointmentsListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorAppointmentsListener.onDoctorAppointmentsLoading();
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
                onDoctorAppointmentsListener.onDoctorAppointmentsInternetConnection();
            } else {
                List<DoctorAppointment> doctorAppointmentList = JsonParser.getDoctorAppointments(s);
                if (doctorAppointmentList.size() > 0) {
                    onDoctorAppointmentsListener.onDoctorAppointmentsSuccessFully(doctorAppointmentList);
                } else {
                    onDoctorAppointmentsListener.onDoctorAppointmentsNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onDoctorAppointmentsListener.onDoctorAppointmentsInternetConnection();
    }
}

