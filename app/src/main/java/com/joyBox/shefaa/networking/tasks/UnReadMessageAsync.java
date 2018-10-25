package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMessagesUnReadResponseListener;



public class UnReadMessageAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnMessagesUnReadResponseListener onMessagesUnReadResponseListener;

    public UnReadMessageAsync(String url, OnMessagesUnReadResponseListener onMessagesUnReadResponseListener) {
        this.url = url;
        this.onMessagesUnReadResponseListener = onMessagesUnReadResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (!s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                s = s.trim().replace("\"", "");
                if (s.equalsIgnoreCase("0"))
                    s = "";
                onMessagesUnReadResponseListener.onMessageUnReadResponseSuccessFuly(s);
                return;
            }
        } catch (Exception ex) {

        }
//        onMessagesUnReadResponseListener.onMessageUnReadResponseInternetConnection();
    }
}
