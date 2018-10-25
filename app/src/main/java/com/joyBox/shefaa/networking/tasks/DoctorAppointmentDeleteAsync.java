package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.AppointmentsConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorAppointmentDeleteListener;

/**
 * Created by Adhamkh on 2018-10-18.
 */

public class DoctorAppointmentDeleteAsync extends AsyncTask<Void, Void, String> {

    private String nid;
    private OnDoctorAppointmentDeleteListener onDoctorAppointmentDeleteListener;

    public DoctorAppointmentDeleteAsync(String nid,
                                        OnDoctorAppointmentDeleteListener onDoctorAppointmentDeleteListener) {
        this.nid = nid;
        this.onDoctorAppointmentDeleteListener = onDoctorAppointmentDeleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorAppointmentDeleteListener.onDoctorAppointmentDeleteResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return AppointmentsConnections.getJsonDelete(NetworkingHelper.DoctorAppointmentDeleteUrl + "/" + nid
                , NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorAppointmentDeleteListener.onDoctorAppointmentDeleteResponseInternetConnection();
            } else {
                onDoctorAppointmentDeleteListener.onDoctorAppointmentDeleteResponseSuccessFully();
            }
            return;
        } catch (Exception ex) {

        }
        onDoctorAppointmentDeleteListener.onDoctorAppointmentDeleteResponseInternetConnection();
    }


}
