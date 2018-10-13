package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.models.MagazinePostCommentAddModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.MagazinePostConnections;
import com.joyBox.shefaa.networking.listeners.OnMagazinePostCommentAddListener;

/**
 * Created by Adhamkh on 2018-10-05.
 */

public class MagazinePostCommentAddAsync extends AsyncTask<Void, Void, String> {

    private OnMagazinePostCommentAddListener onMagazinePostCommentAddListener;
    private MagazinePostCommentAddModel magazinePostCommentAddModel;

    public MagazinePostCommentAddAsync(MagazinePostCommentAddModel magazinePostCommentAddModel,
                                       OnMagazinePostCommentAddListener onMagazinePostCommentAddListener) {
        this.onMagazinePostCommentAddListener = onMagazinePostCommentAddListener;
        this.magazinePostCommentAddModel = magazinePostCommentAddModel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMagazinePostCommentAddListener.onMagazinePostCommentAddResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return MagazinePostConnections.getJsonPostWithDataJson(NetworkingHelper.MagazinePostCommentAddUrl
                , magazinePostCommentAddModel.getData(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
            onMagazinePostCommentAddListener.onMagazinePostCommentAddResponseInternetConnection();
        } else {
            onMagazinePostCommentAddListener.onMagazinePostCommentAddResponseSuccessFully();
        }
    }
}
