package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.SelfMonitorEntity;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.SelfMonitorConnections;
import com.joyBox.shefaa.networking.listeners.OnPatientSelfMonitorListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-18.
 */

public class PatientSelfMonitorAsync extends AsyncTask<Void, Void, String> {

    private String patientId;
    private OnPatientSelfMonitorListener onPatientSelfMonitorListener;

    public PatientSelfMonitorAsync(String patientId,
                                   OnPatientSelfMonitorListener onPatientSelfMonitorListener) {
        this.patientId = patientId;
        this.onPatientSelfMonitorListener = onPatientSelfMonitorListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPatientSelfMonitorListener.onPatientSelfMonitorResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return SelfMonitorConnections.getJson(NetworkingHelper.SelfMonitorListUrl + "?uid=" + patientId, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPatientSelfMonitorListener.onPatientSelfMonitorResponseInternetConnection();
            } else {
                List<SelfMonitorEntity> selfMonitorEntityList = Arrays.asList(new Gson().fromJson(s, SelfMonitorEntity[].class));
                if (selfMonitorEntityList.size() > 0) {
                    onPatientSelfMonitorListener.onPatientSelfMonitorResponseSuccessFully(selfMonitorEntityList);
                } else {
                    onPatientSelfMonitorListener.onPatientSelfMonitorResponseNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onPatientSelfMonitorListener.onPatientSelfMonitorResponseInternetConnection();
    }
}
