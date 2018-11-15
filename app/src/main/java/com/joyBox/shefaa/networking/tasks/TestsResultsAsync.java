package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.TestResultEntity;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnTestsResultResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class TestsResultsAsync extends AsyncTask<Void, Void, String> {

    private String patientId;
    private OnTestsResultResponseListener onTestsResultResponseListener;

    public TestsResultsAsync(String patientId, OnTestsResultResponseListener onTestsResultResponseListener) {
        this.patientId = patientId;
        this.onTestsResultResponseListener = onTestsResultResponseListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onTestsResultResponseListener.onTestsResultResponseLoading();
    }


    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.TestResultUrl + patientId, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onTestsResultResponseListener.onTestsResultResponseInternetConnection();
            } else {
                List<TestResultEntity> resultEntityList = JsonParser.getTestResults(s);
                // Arrays.asList(new Gson().fromJson(s, TestResultEntity[].class));
                if (resultEntityList.size() > 0)
                    onTestsResultResponseListener.onTestsResultResponseSuccessFully(resultEntityList);
                else
                    onTestsResultResponseListener.onTestsResultResponseNoData();
            }
            return;
        } catch (Exception ex) {
//            Log.e("Error Message", ex.getMessage());
        }
        onTestsResultResponseListener.onTestsResultResponseInternetConnection();
    }

}
