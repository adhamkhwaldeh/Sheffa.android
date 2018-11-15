package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.models.SelfMonitorAddModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.SelfMonitorConnections;
import com.joyBox.shefaa.networking.listeners.OnPatientSelfMonitorAddListener;

/**
 * Created by Adhamkh on 2018-10-31.
 */

public class PatientSelfMonitorAddAsync extends AsyncTask<Void, Void, String> {

    private SelfMonitorAddModel selfMonitorAddModel;
    private OnPatientSelfMonitorAddListener onPatientSelfMonitorAddListener;

    public PatientSelfMonitorAddAsync(SelfMonitorAddModel selfMonitorAddModel,
                                      OnPatientSelfMonitorAddListener onPatientSelfMonitorAddListener) {
        this.selfMonitorAddModel = selfMonitorAddModel;
        this.onPatientSelfMonitorAddListener = onPatientSelfMonitorAddListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPatientSelfMonitorAddListener.onPatientSelfMonitorAddLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return SelfMonitorConnections.getJsonPostWithDataJson(NetworkingHelper.SelfMonitorAddUrl, selfMonitorAddModel.getData(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPatientSelfMonitorAddListener.onPatientSelfMonitorAddInternetConnection();
            } else {
                onPatientSelfMonitorAddListener.onPatientSelfMonitorAddSuccessfully();
            }
            return;
        } catch (Exception ex) {

        }
        onPatientSelfMonitorAddListener.onPatientSelfMonitorAddInternetConnection();
    }
}
