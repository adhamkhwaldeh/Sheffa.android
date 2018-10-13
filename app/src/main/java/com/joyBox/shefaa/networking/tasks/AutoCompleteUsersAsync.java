package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.AutoCompleteUser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnAutoCompleteUsersListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-12.
 */

public class AutoCompleteUsersAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnAutoCompleteUsersListener onAutoCompleteUsersListener;

    public AutoCompleteUsersAsync(String url, OnAutoCompleteUsersListener onAutoCompleteUsersListener) {
        this.url = url;
        this.onAutoCompleteUsersListener = onAutoCompleteUsersListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onAutoCompleteUsersListener.autoCompleteUsersLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onAutoCompleteUsersListener.autoCompleteUsersInternetConnection();
            } else {
                List<AutoCompleteUser> autoCompleteUserList = Arrays.asList(new Gson().fromJson(s, AutoCompleteUser[].class));
                if (autoCompleteUserList.size() > 0) {
                    onAutoCompleteUsersListener.autoCompleteUsersSuccessFully(autoCompleteUserList);
                } else {
                    onAutoCompleteUsersListener.autoCompleteUsersNoData();
                }
                return;
            }
        } catch (Exception ex) {
        }
        onAutoCompleteUsersListener.autoCompleteUsersInternetConnection();
    }
}
