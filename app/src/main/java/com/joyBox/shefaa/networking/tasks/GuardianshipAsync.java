package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.GuardianshipEntity;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GuardianshipConnections;
import com.joyBox.shefaa.networking.listeners.OnGuardianshipListener;

import java.util.Arrays;
import java.util.List;

public class GuardianshipAsync extends AsyncTask<Void, Void, String> {

    private String url;

    private OnGuardianshipListener onGuardianshipListener;

    public GuardianshipAsync(String url, OnGuardianshipListener onGuardianshipListener) {
        this.url = url;
        this.onGuardianshipListener = onGuardianshipListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onGuardianshipListener.onGuardianshipResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GuardianshipConnections.getJson(NetworkingHelper.MyGuardiansListUrl, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onGuardianshipListener.onGuardianshipResponseInternetConnection();
            } else {
                List<GuardianshipEntity> guardianshipEntityList = Arrays.asList(new Gson().fromJson(s, GuardianshipEntity[].class));
                if (guardianshipEntityList.size() > 0) {
                    onGuardianshipListener.onGuardianshipResponseSuccessFully(guardianshipEntityList);
                } else {
                    onGuardianshipListener.onGuardianshipResponseNoData();
                }
            }
            return;
        } catch (Exception ex) {
        }
        onGuardianshipListener.onGuardianshipResponseInternetConnection();

    }

}
