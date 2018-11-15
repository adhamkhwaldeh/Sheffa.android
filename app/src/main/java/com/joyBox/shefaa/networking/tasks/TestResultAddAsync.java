package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.models.TestResultAddModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.TestResultConnections;
import com.joyBox.shefaa.networking.listeners.OnTestResultAddListener;

/**
 * Created by Adhamkh on 2018-10-29.
 */

public class TestResultAddAsync extends AsyncTask<Void, Void, String> {

    private TestResultAddModel testResultAddModel;
    private OnTestResultAddListener onTestResultAddListener;

    public TestResultAddAsync(TestResultAddModel testResultAddModel,
                              OnTestResultAddListener onTestResultAddListener) {
        this.testResultAddModel = testResultAddModel;
        this.onTestResultAddListener = onTestResultAddListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onTestResultAddListener.onTestResultAddLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return TestResultConnections.getJsonPostWithDataJson(NetworkingHelper.TestResultAddUrl,
                testResultAddModel.getData(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse))
                onTestResultAddListener.onTestResultAddInternetConnection();
            else {
                onTestResultAddListener.onTestResultAddSuccessFully();
            }
            return;
        } catch (Exception ex) {

        }
        onTestResultAddListener.onTestResultAddInternetConnection();
    }

}
