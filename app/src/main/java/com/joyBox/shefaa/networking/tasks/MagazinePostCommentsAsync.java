package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MagazinePostComment;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMagazinePostCommentsResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MagazinePostCommentsAsync extends AsyncTask<Void, Void, String> {

    private String postId;
    private OnMagazinePostCommentsResponseListener onMagazinePostCommentsResponseListener;

    public MagazinePostCommentsAsync(String postId, OnMagazinePostCommentsResponseListener onMagazinePostCommentsResponseListener) {
        this.postId = postId;
        this.onMagazinePostCommentsResponseListener = onMagazinePostCommentsResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMagazinePostCommentsResponseListener.onMagazinePostCommentsResponseLoading();

    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MagazinePostCommentsUrl + postId + "/comments"
                , NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMagazinePostCommentsResponseListener.onMagazinePostCommentsResponseInternetConnection();
            } else {
                List<MagazinePostComment> magazinePostComments = Arrays.asList(new Gson().fromJson(s, MagazinePostComment[].class));
                if (magazinePostComments.size() > 0) {
                    onMagazinePostCommentsResponseListener.onMagazinePostCommentsResponseSuccessFully(magazinePostComments);
                } else {
                    onMagazinePostCommentsResponseListener.onMagazinePostCommentsResponseNoData();
                }
                return;
            }
        } catch (Exception ex) {

        }
        onMagazinePostCommentsResponseListener.onMagazinePostCommentsResponseInternetConnection();
    }


}
