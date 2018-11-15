package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.models.UploadFileModel;
import com.joyBox.shefaa.entities.responses.UploadFileResponse;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.AttachmentConnections;
import com.joyBox.shefaa.networking.listeners.OnUploadFileListener;

/**
 * Created by Adhamkh on 2018-10-28.
 */

public class UploadFileAsync extends AsyncTask<Void, Void, String> {

    private UploadFileModel uploadFileModel;
    private OnUploadFileListener onUploadFileListener;

    public UploadFileAsync(UploadFileModel uploadFileModel, OnUploadFileListener onUploadFileListener) {
        this.uploadFileModel = uploadFileModel;
        this.onUploadFileListener = onUploadFileListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onUploadFileListener.onUploadFileLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return AttachmentConnections.getJsonPostWithDataJson(NetworkingHelper.UploadFileUrl, new Gson().toJson(uploadFileModel), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onUploadFileListener.onUploadFileInternetConnection();
            } else {
                UploadFileResponse response = new Gson().fromJson(s, UploadFileResponse.class);
                onUploadFileListener.onUploadFileSuccessFully(response);
            }
            return;
        } catch (Exception ex) {
        }
        onUploadFileListener.onUploadFileInternetConnection();
    }

}
