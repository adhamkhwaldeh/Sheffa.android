package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.RegistrationConnections;
import com.joyBox.shefaa.networking.listeners.OnLogoutListener;

/**
 * Created by Adhamkh on 2018-10-13.
 */

public class LogoutAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnLogoutListener onLogoutListener;

    public LogoutAsync(String url, OnLogoutListener onLogoutListener) {
        this.url = url;
        this.onLogoutListener = onLogoutListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onLogoutListener.onLogoutLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return RegistrationConnections.postJsonLogout(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onLogoutListener.onLogoutInternetConnection();
            } else {
                if (s.contains("True")) {
                    onLogoutListener.onLogoutSuccessFully();
                } else {
                    onLogoutListener.onLogoutFail();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onLogoutListener.onLogoutInternetConnection();
    }

}
