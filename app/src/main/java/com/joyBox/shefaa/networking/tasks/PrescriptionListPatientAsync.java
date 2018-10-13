package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.joyBox.shefaa.entities.Prescription;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnPrescriptionListResponseListener;

import java.util.List;

public class PrescriptionListPatientAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnPrescriptionListResponseListener onPrescriptionListResponseListener;

    public PrescriptionListPatientAsync(String url, OnPrescriptionListResponseListener onPrescriptionListResponseListener) {
        this.url = url;
        this.onPrescriptionListResponseListener = onPrescriptionListResponseListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPrescriptionListResponseListener.onPrescriptionListResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            List<Prescription> prescriptionList = JsonParser.getPrescription(s);
            if (prescriptionList.size() > 0) {
                onPrescriptionListResponseListener.onPrescriptionListResponseSuccessFully(prescriptionList);
            } else {
                onPrescriptionListResponseListener.onPrescriptionListResponseNoData();
            }
            return;
        } catch (Exception ex) {
            Log.e("Parsing Error", ex.getMessage());
        }
        onPrescriptionListResponseListener.onPrescriptionListResponseInternetConnection();
    }
}
