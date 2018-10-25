package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.DoctorPatient;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.DoctorConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorPatientListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class DoctorPatientsAsync extends AsyncTask<Void, Void, String> {

    public OnDoctorPatientListener onDoctorPatientListener;

    public DoctorPatientsAsync(OnDoctorPatientListener onDoctorPatientListener) {
        this.onDoctorPatientListener = onDoctorPatientListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorPatientListener.onDoctorPatientLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return DoctorConnections.getJson(NetworkingHelper.Doctor_Patients_Url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorPatientListener.onDoctorPatientInternetConnection();
            } else {
                List<DoctorPatient> doctorPatientList = JsonParser.getDoctorPatient(s);
                if (doctorPatientList.size() > 0) {
                    onDoctorPatientListener.onDoctorPatientSuccessFully(doctorPatientList);
                } else {
                    onDoctorPatientListener.onDoctorPatientNoData();
                }
            }
            return;
        } catch (Exception ex) {
        }
        onDoctorPatientListener.onDoctorPatientInternetConnection();
    }
}
