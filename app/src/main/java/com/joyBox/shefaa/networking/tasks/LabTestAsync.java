package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.LabTest;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnLabTestsListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class LabTestAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnLabTestsListener onLabTestsListener;

    public LabTestAsync(String url, OnLabTestsListener onLabTestsListener) {
        this.url = url;
        this.onLabTestsListener = onLabTestsListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onLabTestsListener.onLabTestsLoading();
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
                onLabTestsListener.onLabTestsInternetConnection();
            } else {
                List<LabTest> labTestList = JsonParser.getLabTest(s);
                if (labTestList.size() > 0)
                    onLabTestsListener.onLabTestsSuccessFully(labTestList);
                else
                    onLabTestsListener.onLabTestsNoData();
            }
            return;
        } catch (Exception ex) {
        }
        onLabTestsListener.onLabTestsInternetConnection();
    }

}
