package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.ActiveMaterialAutoComplete;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnActiveMaterialAutoCompleteListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class ActiveMaterialAutoCompleteAsync extends AsyncTask<Void, Void, String> {
    public OnActiveMaterialAutoCompleteListener onActiveMaterialAutoCompleteListener;

    public ActiveMaterialAutoCompleteAsync(OnActiveMaterialAutoCompleteListener onActiveMaterialAutoCompleteListener) {
        this.onActiveMaterialAutoCompleteListener = onActiveMaterialAutoCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onActiveMaterialAutoCompleteListener.onActiveMaterialAutoCompleteLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.ActiveMaterialAutoCompleteUrl, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onActiveMaterialAutoCompleteListener.onActiveMaterialAutoCompleteInternetConnection();
            } else {
                List<ActiveMaterialAutoComplete> activeMaterialAutoCompleteList =
                        Arrays.asList(new Gson().fromJson(s, ActiveMaterialAutoComplete[].class));
                if (activeMaterialAutoCompleteList.size() > 0)
                    onActiveMaterialAutoCompleteListener.onActiveMaterialAutoCompleteSuccessFully(activeMaterialAutoCompleteList);
                else
                    onActiveMaterialAutoCompleteListener.onActiveMaterialAutoCompleteNoData();
            }
            return;
        } catch (Exception ex) {
        }
        onActiveMaterialAutoCompleteListener.onActiveMaterialAutoCompleteInternetConnection();
    }

}
