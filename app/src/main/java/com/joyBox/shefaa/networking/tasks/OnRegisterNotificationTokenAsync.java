package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Pair;

import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.entities.RegisterNotificationResult;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.NotificationConnections;
import com.joyBox.shefaa.networking.listeners.OnRegisterTokenResponseListener;

import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-08-18.
 */

public class OnRegisterNotificationTokenAsync extends AsyncTask<Void, Void, String> {

    public Client client;
    public String token;
    public OnRegisterTokenResponseListener onRegisterTokenResponseListener;

    public OnRegisterNotificationTokenAsync(Client client, String token,
                                            OnRegisterTokenResponseListener onRegisterTokenResponseListener) {
        this.client = client;
        this.token = token;
        this.onRegisterTokenResponseListener = onRegisterTokenResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        List<Pair<String, String>> pairList = new Vector<>();
        pairList.add(new Pair<String, String>("token", token));
        pairList.add(new Pair<String, String>("type", "android"));
        return NotificationConnections.registerNotificationToken(NetworkingHelper.NotificationRegisterDeviceUrl + "?sess_name=" + client.getSessionName()
                        + "&sess_id=" + client.getSessid() + "&token=" + client.getToken()
                , pairList, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onRegisterTokenResponseListener.onRegisterTokenResponseInternetConnection();
            } else {
                RegisterNotificationResult result = JsonParser.getRegisterNotificationResult(s);
                if (result.getSuccess().equalsIgnoreCase("1")) {
                    onRegisterTokenResponseListener.onRegisterTokenResponseSuccessFuly();
                } else {
                    onRegisterTokenResponseListener.onRegisterTokenResponseFail();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onRegisterTokenResponseListener.onRegisterTokenResponseInternetConnection();
    }

}
