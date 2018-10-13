package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.Pharmacy;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnPharmacySearchListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-12.
 */

public class PharmacySearchAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnPharmacySearchListener onPharmacySearchListener;

    public PharmacySearchAsync(String url, OnPharmacySearchListener onPharmacySearchListener) {
        this.url = url;
        this.onPharmacySearchListener = onPharmacySearchListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPharmacySearchListener.onPharmacyLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPharmacySearchListener.onPharmacyInternetConnection();
            } else {
                List<Pharmacy> PharmacyList = JsonParser.getPharmacies(s);
                if (PharmacyList.size() > 0) {
                    onPharmacySearchListener.onPharmacySuccessFully(PharmacyList);
                } else {
                    onPharmacySearchListener.onPharmacyNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onPharmacySearchListener.onPharmacyInternetConnection();
    }

}
