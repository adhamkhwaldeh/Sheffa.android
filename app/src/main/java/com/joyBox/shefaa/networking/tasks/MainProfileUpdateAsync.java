package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.enums.Gender;
import com.joyBox.shefaa.enums.ProfileType;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMainProfileUpdateListener;

/**
 * Created by Adhamkh on 2018-10-03.
 */

public class MainProfileUpdateAsync extends AsyncTask<Void, Void, String> {

    private String uid;
    private ProfileType profileType;
    private String first_name;
    private String last_name;
    private String gender;

    private OnMainProfileUpdateListener onMainProfileUpdateListener;

    public MainProfileUpdateAsync(String uid, ProfileType profileType,
                                  String first_name, String last_name, String gender,
                                  OnMainProfileUpdateListener onMainProfileUpdateListener) {
        this.uid = uid;
        this.profileType = profileType;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.onMainProfileUpdateListener = onMainProfileUpdateListener;
    }

    public String generateUrl() {
        return NetworkingHelper.GeneralProfile_Update_Url + "?uid=" + uid + "&type=" + profileType.getType() + "&first_name=" + first_name +
                "&last_name=" + last_name + "&gender=" + gender;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMainProfileUpdateListener.onMainProfileUpdateLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJsonPost(generateUrl(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onMainProfileUpdateListener.onMainProfileUpdateInternetConnection();
        } else if (s.equalsIgnoreCase("\"success\"\n")) {
            onMainProfileUpdateListener.onMainProfileUpdateSuccessFully();
        } else {
            onMainProfileUpdateListener.onMainProfileUpdateFail();
        }
    }

}
