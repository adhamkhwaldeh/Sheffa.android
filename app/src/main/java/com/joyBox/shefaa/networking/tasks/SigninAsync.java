package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Pair;

import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.entities.models.LoginModel;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.RegistrationConnections;
import com.joyBox.shefaa.networking.listeners.OnSignInResponseListener;

import java.util.List;
import java.util.Vector;

public class SigninAsync extends AsyncTask<String, Void, String> {

    public LoginModel loginModel;
    public OnSignInResponseListener onSignInResponseListener;

    public SigninAsync(LoginModel loginModel, OnSignInResponseListener onSignInResponseListener) {
        this.loginModel = loginModel;
        this.onSignInResponseListener = onSignInResponseListener;
    }

    @Override
    protected String doInBackground(String... params) {
        List<Pair<String, String>> lst = new Vector<Pair<String, String>>();
        lst.add(new Pair<String, String>("username", loginModel.getUserName()));
        lst.add(new Pair<String, String>("password", loginModel.getPassword()));
        return RegistrationConnections.getJsonSignIn(NetworkingHelper.Mainurl + NetworkingHelper.SignInURL,
                lst, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onSignInResponseListener.SignInLoading();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onSignInResponseListener.SignInInternetConnection();
        } else if (s.charAt(0) != '{') {
            onSignInResponseListener.SignInFail();
        } else {
            try {
                Client client = JsonParser.getClient(s);
                onSignInResponseListener.SignInSuccessFuly(client);
            } catch (Exception ex) {
                onSignInResponseListener.SignInFail();
            }
        }


    }

}
