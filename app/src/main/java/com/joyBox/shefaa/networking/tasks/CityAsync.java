package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.CityEntity;
import com.joyBox.shefaa.enums.CityEnum;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnCityListListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-11-28.
 */

public class CityAsync extends AsyncTask<Void, Void, String> {

    public OnCityListListener onCityListListener;
    public CityEnum cityEnum;

    public CityAsync(CityEnum cityEnum,OnCityListListener onCityListListener ) {
        this.onCityListListener = onCityListListener;
        this.cityEnum = cityEnum;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onCityListListener.onCityListLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.CityListUrl + "?profile_type=" +
                cityEnum.getCitiesType(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onCityListListener.onCityListInternetConnection();
            } else {

                List<CityEntity> cityList =
                        Arrays.asList(new Gson().fromJson(s, CityEntity[].class));
                if (cityList.size() > 0)
                    onCityListListener.onCityListSuccessFully(cityList);
                else
                    onCityListListener.onCityListNoData();
            }
            return;
        } catch (Exception ex) {

        }

        onCityListListener.onCityListInternetConnection();
    }

}
