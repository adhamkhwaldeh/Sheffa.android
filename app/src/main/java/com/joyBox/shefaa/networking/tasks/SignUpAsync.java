package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.models.SignUpPostModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.RegistrationConnections;
import com.joyBox.shefaa.networking.listeners.OnSignUpResponseListener;

/**
 * Created by Adhamkh on 2018-08-10.
 */

public class SignUpAsync extends AsyncTask<String, Void, String> {

    SignUpPostModel signUpPostModel;
    OnSignUpResponseListener onSignUpResponseListener;

    public SignUpAsync(SignUpPostModel signUpPostModel, OnSignUpResponseListener onSignUpResponseListener) {
        this.signUpPostModel = signUpPostModel;
        this.onSignUpResponseListener = onSignUpResponseListener;
    }

    @Override
    protected String doInBackground(String... params) {
        return RegistrationConnections.getJsonPostWithDataJson(NetworkingHelper.Mainurl + NetworkingHelper.SignUpURL,
                new Gson().toJson(signUpPostModel), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onSignUpResponseListener.SignUpLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onSignUpResponseListener.SignUpInternetConnection();
        } else {
            onSignUpResponseListener.SignUpSuccessFuly();
        }
    }
}
