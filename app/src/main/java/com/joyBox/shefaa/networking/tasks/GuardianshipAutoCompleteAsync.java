package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.GuardianshipAutoComplete;
import com.joyBox.shefaa.entities.GuardianshipEntity;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GuardianshipConnections;
import com.joyBox.shefaa.networking.listeners.OnGuardianshipAutoCompleteListener;
import com.joyBox.shefaa.networking.listeners.OnGuardianshipListener;

import java.util.Arrays;
import java.util.List;

public class GuardianshipAutoCompleteAsync extends AsyncTask<Void, Void, String> {


    private OnGuardianshipAutoCompleteListener onGuardianshipListener;

    public GuardianshipAutoCompleteAsync(OnGuardianshipAutoCompleteListener onGuardianshipListener) {
        this.onGuardianshipListener = onGuardianshipListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onGuardianshipListener.onGuardianshipAutoCompleteLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GuardianshipConnections.getJson(NetworkingHelper.GuardiansAutoCompleteUrl, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onGuardianshipListener.onGuardianshipAutoCompleteInternetConnection();
            } else {
                List<GuardianshipAutoComplete> guardianshipEntityList =
                        Arrays.asList(new Gson().fromJson(s, GuardianshipAutoComplete[].class));
                if (guardianshipEntityList.size() > 0) {
                    onGuardianshipListener.onGuardianshipAutoCompleteSuccessFully(guardianshipEntityList);
                } else {
                    onGuardianshipListener.onGuardianshipAutoCompleteNoData();
                }
            }
            return;
        } catch (Exception ex) {
        }
        onGuardianshipListener.onGuardianshipAutoCompleteInternetConnection();

    }

}

