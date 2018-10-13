package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MessageEntity;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMessageResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-16.
 */

public class MessagesAsync extends AsyncTask<Void, Void, String> {
    String url;

    OnMessageResponseListener onMessageResponseListener;

    public MessagesAsync(String url, OnMessageResponseListener onMessageResponseListener) {
        this.url = url;
        this.onMessageResponseListener = onMessageResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMessageResponseListener.onMessageResponseLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onMessageResponseListener.onMessageResponseInternetConnection();
        } else {
            try {
                List<MessageEntity> messageEntityList = Arrays.asList(new Gson().fromJson(s, MessageEntity[].class));

                if (messageEntityList.size() > 0)
                    onMessageResponseListener.onMessageResponseSuccessFully(messageEntityList);
                else
                    onMessageResponseListener.onMessageResponseNoData();
                return;
            } catch (Exception ex) {

            }
            onMessageResponseListener.onMessageResponseInternetConnection();
        }
    }
}
