package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.models.AppointmentFlagModel;
import com.joyBox.shefaa.enums.AppointmentFlagName;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.AppointmentsConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentFlagListener;

/**
 * Created by Adhamkh on 2018-10-18.
 */

public class DoctorAppointmentFlagAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnAppointmentFlagListener onAppointmentFlagListener;
    private AppointmentFlagName appointmentFlagName;

    private String appointmentId;
    private String userId;
    private String flag;

    public DoctorAppointmentFlagAsync(String url, AppointmentFlagName appointmentFlagName,
                                      String appointmentId, String userId, String flag
            , OnAppointmentFlagListener onAppointmentFlagListener) {
        this.url = url;
        this.onAppointmentFlagListener = onAppointmentFlagListener;
        this.appointmentFlagName = appointmentFlagName;
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAppointmentFlagListener.onAppointmentFlagResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        AppointmentFlagModel appointmentFlagActions = new AppointmentFlagModel(appointmentFlagName.getFlag()
                , flag, userId, appointmentId);
        String jSon = new Gson().toJson(appointmentFlagActions);
        return AppointmentsConnections.getJsonPostWithDataJson(url, jSon, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onAppointmentFlagListener.onAppointmentFlagResponseInternetConnection();
            } else if (s.toLowerCase().contains("true")) {
                onAppointmentFlagListener.onAppointmentFlagResponseSuccessFully();
            } else {
                onAppointmentFlagListener.onAppointmentFlagResponseFail();
            }
            return;
        } catch (Exception ex) {

        }
        onAppointmentFlagListener.onAppointmentFlagResponseInternetConnection();
    }
}
