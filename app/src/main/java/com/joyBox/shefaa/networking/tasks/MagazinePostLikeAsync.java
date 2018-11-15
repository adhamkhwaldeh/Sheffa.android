package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.App;
import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.entities.models.MagazinePostLikeModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.MagazinePostConnections;
import com.joyBox.shefaa.networking.listeners.OnMagazinePostLikeResponseListener;
import com.joyBox.shefaa.repositories.UserRepository;

/**
 * Created by Adhamkh on 2018-10-05.
 */

public class MagazinePostLikeAsync extends AsyncTask<Void, Void, String> {
    private String magazinePostId;
    private String userId;
    private String flag;
    private OnMagazinePostLikeResponseListener onMagazinePostLikeResponseListener;

    public MagazinePostLikeAsync(String magazinePostId, String userId, String flag, OnMagazinePostLikeResponseListener onMagazinePostLikeResponseListener) {
        this.magazinePostId = magazinePostId;
        this.userId = userId;
        this.flag = flag;
        this.onMagazinePostLikeResponseListener = onMagazinePostLikeResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMagazinePostLikeResponseListener.onMagazinePostLikeResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        MagazinePostLikeModel likeModel = new MagazinePostLikeModel("like", flag, userId, magazinePostId);
        String jSon = new Gson().toJson(likeModel);


        Client client = new UserRepository(App.app).getClient();
        String url = NetworkingHelper.MagazinePostLikeUrl + "?sess_id=" + client.getSessid() +
                "&sess_name=" + client.getSessionName() + "&token=" + client.getToken();

        return MagazinePostConnections.getJsonPostWithDataJson(url, jSon, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMagazinePostLikeResponseListener.onMagazinePostLikeResponseInternetConnection();
            } else if (s.toLowerCase().contains("true")) {
                onMagazinePostLikeResponseListener.onMagazinePostLikeResponseSuccessFully();
            } else {
                onMagazinePostLikeResponseListener.onMagazinePostLikeResponseFail();
            }
            return;
        } catch (Exception ex) {

        }
        onMagazinePostLikeResponseListener.onMagazinePostLikeResponseInternetConnection();
    }
}
