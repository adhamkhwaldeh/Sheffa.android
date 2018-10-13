package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Pair;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.RegistrationConnections;
import com.joyBox.shefaa.networking.listeners.OnChangeEmailListener;

import java.util.List;
import java.util.Vector;

/**
 * Created by Adhamkh on 2018-10-12.
 */

public class ChangeEmailAsync extends AsyncTask<Void, Void, String> {

    private String usrId;
    private String newEmail;
    private String currentPassword;
    private OnChangeEmailListener onChangeEmailListener;

    public ChangeEmailAsync(String usrId, String newEmail,
                            String currentPassword, OnChangeEmailListener onChangeEmailListener) {
        this.usrId = usrId;
        this.newEmail = newEmail;
        this.currentPassword = currentPassword;
        this.onChangeEmailListener = onChangeEmailListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onChangeEmailListener.changeEmailLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        List<Pair<String, String>> lst = new Vector<Pair<String, String>>();
        lst.add(new Pair<String, String>("current_pass", currentPassword));
        lst.add(new Pair<String, String>("mail", newEmail));
        return RegistrationConnections.putJsonChangeEmail(
                NetworkingHelper.ChangeEmailUrl + usrId + ".json", lst, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onChangeEmailListener.changeEmailInternetConnection();
            } else {
                onChangeEmailListener.changeEmailSuccessFully();
            }
            return;
        } catch (Exception ex) {

        }
        onChangeEmailListener.changeEmailInternetConnection();
    }
}
