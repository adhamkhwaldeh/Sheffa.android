package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.TermsAndConditionsEntity;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.TermsAndConditionsConnections;
import com.joyBox.shefaa.networking.listeners.OnTermsAndConditionResponseListener;

/**
 * Created by Adhamkh on 2018-08-17.
 */

public class TermsAndConditionsAsync extends AsyncTask<Void, Void, String> {
    String url;
    OnTermsAndConditionResponseListener onTermsAndConditionResponseListener;

    public TermsAndConditionsAsync(String url, OnTermsAndConditionResponseListener onTermsAndConditionResponseListener) {
        this.url = url;
        this.onTermsAndConditionResponseListener = onTermsAndConditionResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return TermsAndConditionsConnections.getJsonPost(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onTermsAndConditionResponseListener.onTermsAndConditionResponseInternetConnection();
        } else {
            try {
                TermsAndConditionsEntity entity = JsonParser.getTermsAndConditionsEntity(s);
                onTermsAndConditionResponseListener.onTermsAndConditionResponseSuccessFuly(entity);
                return;
            } catch (Exception ex) {

            }
        }
        onTermsAndConditionResponseListener.onTermsAndConditionResponseInternetConnection();
    }

}
