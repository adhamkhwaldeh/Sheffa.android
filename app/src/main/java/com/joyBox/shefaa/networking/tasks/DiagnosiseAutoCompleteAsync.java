package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDiagnosiseAutoCompleteListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class DiagnosiseAutoCompleteAsync extends AsyncTask<Void, Void, String> {

    public OnDiagnosiseAutoCompleteListener onDiagnosiseAutoCompleteListener;

    public DiagnosiseAutoCompleteAsync(OnDiagnosiseAutoCompleteListener onDiagnosiseAutoCompleteListener) {
        this.onDiagnosiseAutoCompleteListener = onDiagnosiseAutoCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDiagnosiseAutoCompleteListener.onDiagnosiseAutoCompleteLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.DiagnosiseAutoCompleteUrl, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDiagnosiseAutoCompleteListener.onDiagnosiseAutoCompleteInternetConnection();
            } else {

                List<DiagnosiseAutoComplete> diagnosiseAutoCompleteList =
                        Arrays.asList(new Gson().fromJson(s, DiagnosiseAutoComplete[].class));
                if (diagnosiseAutoCompleteList.size() > 0)
                    onDiagnosiseAutoCompleteListener.onDiagnosiseAutoCompleteSuccessFully(diagnosiseAutoCompleteList);
                else
                    onDiagnosiseAutoCompleteListener.onDiagnosiseAutoCompleteNoData();
            }
            return;
        } catch (Exception ex) {

        }

        onDiagnosiseAutoCompleteListener.onDiagnosiseAutoCompleteInternetConnection();
    }

}
