package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.PrescriptionFollowUp;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnPrescriptionFollowUpResponseListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class PrescriptionFollowUpAsync extends AsyncTask<Void, Void, String> {

    private String itemId;
    private OnPrescriptionFollowUpResponseListener onPrescriptionFollowUpResponseListener;

    public PrescriptionFollowUpAsync(String itemId,
                                     OnPrescriptionFollowUpResponseListener onPrescriptionFollowUpResponseListener) {
        this.itemId = itemId;
        this.onPrescriptionFollowUpResponseListener = onPrescriptionFollowUpResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPrescriptionFollowUpResponseListener.onPrescriptionFollowUpResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.PrescriptionFollowUpUrl + itemId, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPrescriptionFollowUpResponseListener.onPrescriptionFollowUpResponseInternetConnection();
            } else {
                List<PrescriptionFollowUp> followUpList = JsonParser.getAppointmentFollowUps(s);
                if (followUpList.size() > 0) {
                    onPrescriptionFollowUpResponseListener.onPrescriptionFollowUpResponseSuccessFuly(followUpList);
                } else {
                    onPrescriptionFollowUpResponseListener.onPrescriptionFollowUpResponseNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onPrescriptionFollowUpResponseListener.onPrescriptionFollowUpResponseInternetConnection();
    }
}
