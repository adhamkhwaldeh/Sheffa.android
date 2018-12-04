package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MedicinePotionEntity;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMedicineAndPotionResponseListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MedicineAndPotionAsync extends AsyncTask<Void, Void, String> {

    private String itemId;
    private OnMedicineAndPotionResponseListener onMedicineAndPotionResponseListener;

    public MedicineAndPotionAsync(String itemId, OnMedicineAndPotionResponseListener onMedicineAndPotionResponseListener) {
        this.itemId = itemId;
        this.onMedicineAndPotionResponseListener = onMedicineAndPotionResponseListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMedicineAndPotionResponseListener.onMedicineAndPotionResponseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MedicineAndPotionDetailsUrl + itemId, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMedicineAndPotionResponseListener.onMedicineAndPotionResponseInternetConnection();
            } else {
                List<MedicinePotionEntity> entityList = JsonParser.getMedicinePotionEntities(s);//Arrays.asList(new Gson().fromJson(s, MedicinePotionEntity[].class));
                if (entityList.size() > 0) {
                    onMedicineAndPotionResponseListener.onMedicineAndPotionResponseSuccessFuly(entityList);
                } else {
                    onMedicineAndPotionResponseListener.onMedicineAndPotionResponseNoData();
                }
            }
            return;
        } catch (Exception ex) {
            String msg = ex.getMessage();
            Log.v("Error", msg);
        }
        onMedicineAndPotionResponseListener.onMedicineAndPotionResponseInternetConnection();
    }
}
