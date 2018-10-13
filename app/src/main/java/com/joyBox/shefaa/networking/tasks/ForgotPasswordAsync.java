package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Pair;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.RegistrationConnections;
import com.joyBox.shefaa.networking.listeners.OnForgotResponseListener;

import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-08-10.
 */

public class ForgotPasswordAsync extends AsyncTask<Void, Void, String> {

    String email;
    OnForgotResponseListener onForgotResponseListener;

    public ForgotPasswordAsync(String email, OnForgotResponseListener onForgotResponseListener) {
        this.email = email;
        this.onForgotResponseListener = onForgotResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        List<Pair<String, String>> lst = new Vector<Pair<String, String>>();
        lst.add(new Pair<String, String>("name", email));
        return RegistrationConnections.getForgotPassword(NetworkingHelper.Mainurl + NetworkingHelper.ForgotPasswordURL,
                lst, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onForgotResponseListener.forgotPasswordLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onForgotResponseListener.forgotPasswordInternetConnection();
        } else if (s.equalsIgnoreCase(NetworkingHelper.EmailNotFoundResponse)) {
            onForgotResponseListener.forgotPasswordFail();
        } else {
            onForgotResponseListener.forgotPasswordSuccessFuly();
        }
    }
}
