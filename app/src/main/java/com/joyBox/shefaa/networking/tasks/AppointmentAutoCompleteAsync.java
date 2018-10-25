package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.AppointmentAutoComplete;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentAutoCompleteListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class AppointmentAutoCompleteAsync extends AsyncTask<Void, Void, String> {

    public String title;
    public OnAppointmentAutoCompleteListener onAppointmentAutoCompleteListener;

    public AppointmentAutoCompleteAsync(String title, OnAppointmentAutoCompleteListener onAppointmentAutoCompleteListener) {
        this.title = title;
        this.onAppointmentAutoCompleteListener = onAppointmentAutoCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAppointmentAutoCompleteListener.onAppointmentAutoCompleteLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.AppointmentAutoCompleteUrl + title, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onAppointmentAutoCompleteListener.onAppointmentAutoCompleteInternetConnection();
            } else {
                List<AppointmentAutoComplete> appointmentAutoCompleteList = JsonParser.getAppointmentAutoComplete(s);
                if (appointmentAutoCompleteList.size() > 0) {
                    onAppointmentAutoCompleteListener.onAppointmentAutoCompleteSuccessFully(appointmentAutoCompleteList);
                } else {
                    onAppointmentAutoCompleteListener.onAppointmentAutoCompleteNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onAppointmentAutoCompleteListener.onAppointmentAutoCompleteInternetConnection();
    }
}
