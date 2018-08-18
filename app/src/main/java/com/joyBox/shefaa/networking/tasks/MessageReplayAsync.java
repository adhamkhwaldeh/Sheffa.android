package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.MessageResult;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMessageReplayResponseListener;

/**
 * Created by Adhamkh on 2018-08-17.
 */

public class MessageReplayAsync extends AsyncTask<String, Void, String> {

    OnMessageReplayResponseListener onMessageReplayResponseListener;
    String url;

    public MessageReplayAsync(String url, OnMessageReplayResponseListener onMessageReplayResponseListener) {
        this.url = url;
        this.onMessageReplayResponseListener = onMessageReplayResponseListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMessageReplayResponseListener.onMessageReplayResponseLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMessageReplayResponseListener.onMessageReplayResponseInternetConnection();
            } else {
                MessageResult messageResult = JsonParser.getMessagesResult(s);
                if (messageResult.getSuccess()) {
                    onMessageReplayResponseListener.onMessageResponseSuccessFuly();
                } else {
                    onMessageReplayResponseListener.onMessageResponseFail(messageResult);
                }
                return;
            }
        } catch (Exception ex) {

        }
        onMessageReplayResponseListener.onMessageReplayResponseInternetConnection();
    }

}
