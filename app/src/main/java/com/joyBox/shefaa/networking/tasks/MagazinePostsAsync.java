package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MagazinePost;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMagazinePostsResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MagazinePostsAsync extends AsyncTask<Void, Void, String> {

    private String url;
    private OnMagazinePostsResponseListener onMagazinePostsResponseListener;

    public MagazinePostsAsync(String url, OnMagazinePostsResponseListener onMagazinePostsResponseListener) {
        this.url = url;
        this.onMagazinePostsResponseListener = onMagazinePostsResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMagazinePostsResponseListener.onMagazinePostsResponseLoading();
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
                onMagazinePostsResponseListener.onMagazinePostsResponseInternetConnection();
            } else {
                List<MagazinePost> magazinePostList = Arrays.asList(new Gson().fromJson(s, MagazinePost[].class));
                if (magazinePostList.size() > 0) {
                    onMagazinePostsResponseListener.onMagazinePostsResponseSuccessFully(magazinePostList);
                } else {
                    onMagazinePostsResponseListener.onMagazinePostsResponseNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onMagazinePostsResponseListener.onMagazinePostsResponseInternetConnection();
    }

}
