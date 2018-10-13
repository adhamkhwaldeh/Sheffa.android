package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Pair;

import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.RegistrationConnections;
import com.joyBox.shefaa.networking.listeners.OnChangeEmailListener;
import com.joyBox.shefaa.networking.listeners.OnChangePasswordListener;

import java.util.List;
import java.util.Vector;

public class ChangePasswordAsync extends AsyncTask<Void, Void, String> {

    private String usrId;
    private String newPassword;
    private String currentPassword;
    private OnChangePasswordListener onChangePasswordListener;

    public ChangePasswordAsync(String usrId, String newPassword,
                               String currentPassword, OnChangePasswordListener onChangePasswordListener) {
        this.usrId = usrId;
        this.newPassword = newPassword;
        this.currentPassword = currentPassword;
        this.onChangePasswordListener = onChangePasswordListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onChangePasswordListener.changePasswordLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        List<Pair<String, String>> lst = new Vector<Pair<String, String>>();
        lst.add(new Pair<String, String>("current_pass", currentPassword));
        lst.add(new Pair<String, String>("pass", newPassword));
        return RegistrationConnections.putJsonChangePassword(
                NetworkingHelper.ChangeEmailUrl + usrId , lst, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onChangePasswordListener.changePasswordInternetConnection();
            } else {
                onChangePasswordListener.changePasswordSuccessFully();
            }
            return;
        } catch (Exception ex) {

        }
        onChangePasswordListener.changePasswordInternetConnection();
    }
}
