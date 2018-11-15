package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MeasurementType;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMeasurementTypeListener;

import java.util.Arrays;
import java.util.List;


public class MeasurementTypeAsync extends AsyncTask<Void, Void, String> {

    public OnMeasurementTypeListener onMeasurementTypeListener;

    public MeasurementTypeAsync(OnMeasurementTypeListener onMeasurementTypeListener) {
        this.onMeasurementTypeListener = onMeasurementTypeListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMeasurementTypeListener.onMeasurementTypeLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MeasurementType, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMeasurementTypeListener.onMeasurementTypeInternetConnection();
            } else {
                List<MeasurementType> measurementTypes = Arrays.asList(new Gson().fromJson(s, MeasurementType[].class));
                if (measurementTypes.size() > 0) {
                    onMeasurementTypeListener.onMeasurementTypeSuccessFully(measurementTypes);
                } else {
                    onMeasurementTypeListener.onMeasurementTypeNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onMeasurementTypeListener.onMeasurementTypeInternetConnection();
    }
}
