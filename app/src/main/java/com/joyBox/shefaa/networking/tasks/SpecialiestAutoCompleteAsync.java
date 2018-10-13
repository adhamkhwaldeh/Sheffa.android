package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.SpecialistAutoComplete;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnSpecialistAutoCompleteListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-09.
 */

public class SpecialiestAutoCompleteAsync extends AsyncTask<Void, Void, String> {
    private String url;
    private OnSpecialistAutoCompleteListener onSpecialistAutoCompleteListener;

    public SpecialiestAutoCompleteAsync(String url, OnSpecialistAutoCompleteListener onSpecialistAutoCompleteListener) {
        this.url = url;
        this.onSpecialistAutoCompleteListener = onSpecialistAutoCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onSpecialistAutoCompleteListener.onSpecialistAutoLoading();
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
                onSpecialistAutoCompleteListener.onSpecialistAutoInternetConnection();
            } else {
                List<SpecialistAutoComplete> specialistAutoCompletes =
                        Arrays.asList(new Gson().fromJson(s, SpecialistAutoComplete[].class));
                if (specialistAutoCompletes.size() > 0) {
                    onSpecialistAutoCompleteListener.onSpecialiestAutoSuccessFully(specialistAutoCompletes);
                } else {
                    onSpecialistAutoCompleteListener.onSpecialiestAutoNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onSpecialistAutoCompleteListener.onSpecialistAutoInternetConnection();
    }

}
