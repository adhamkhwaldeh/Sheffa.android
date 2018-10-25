package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAlertMedicalListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class AlertMedicalAsync extends AsyncTask<Void, Void, String> {
    public List<String> activeMaterialList;
    public OnAlertMedicalListener onAlertMedicalListener;

    public AlertMedicalAsync(List<String> activeMaterialList, OnAlertMedicalListener onAlertMedicalListener) {
        this.activeMaterialList = activeMaterialList;
        this.onAlertMedicalListener = onAlertMedicalListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAlertMedicalListener.onAlertMedicalLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String url = NetworkingHelper.AlertAutoCompleteUrl;
        for (int i = 0; i < activeMaterialList.size(); i++) {
            if (i > 0)
                url += ",";
            url += activeMaterialList.get(i);
        }
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onAlertMedicalListener.onAlertMedicalInternetConnection();
            } else {
                List<String> alertMedicalList = Arrays.asList(s.split(","));
                if (alertMedicalList.size() > 0)
                    onAlertMedicalListener.onAlertMedicalSuccessFully(alertMedicalList);
                else
                    onAlertMedicalListener.onAlertMedicalNoData();
            }
            return;
        } catch (Exception ex) {

        }
        onAlertMedicalListener.onAlertMedicalInternetConnection();
    }

}
