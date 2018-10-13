package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.joyBox.shefaa.entities.AvailableTime;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAppointmentAvailableTimeListener;


public class AppointmentAvailableTimeAsync extends AsyncTask<Void, Void, String> {

    private String doctorId;
    private String date;
    private OnAppointmentAvailableTimeListener onAppointmentAvailableTimeListener;

    public AppointmentAvailableTimeAsync(String doctorId, String date, OnAppointmentAvailableTimeListener onAppointmentAvailableTimeListener) {
        this.doctorId = doctorId;
        this.date = date;
        this.onAppointmentAvailableTimeListener = onAppointmentAvailableTimeListener;
    }


    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.Appointment_Available_Time_Url + "?doctor_id=" + doctorId + "&date=" + date,
                NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAppointmentAvailableTimeListener.onAppointmentAvailableTimeLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            AvailableTime availableTime = JsonParser.getAvailableTime(s);//Arrays.asList(new Gson().fromJson(s, AppointmentEntity[].class));
            if (availableTime.getAvailableTimes().size() > 0)
                onAppointmentAvailableTimeListener.onAppointmentAvailableTimeSuccessFully(availableTime);
            else
                onAppointmentAvailableTimeListener.onAppointmentAvailableTimeNoData();
            return;
        } catch (Exception ex) {
            Log.v("", "");
        }
        onAppointmentAvailableTimeListener.onAppointmentAvailableTimeInternetConnection();
    }

}
