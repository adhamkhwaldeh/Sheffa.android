package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.MedicineAutoComplete;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnMedicineAutoCompleteListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class MedicineAutoCompleteAsync extends AsyncTask<Void, Void, String> {

    public OnMedicineAutoCompleteListener onMedicineAutoCompleteListener;

    public MedicineAutoCompleteAsync(OnMedicineAutoCompleteListener onMedicineAutoCompleteListener) {
        this.onMedicineAutoCompleteListener = onMedicineAutoCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMedicineAutoCompleteListener.onMedicineAutoCompleteLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.MedicineAutoCompleteUrl,
                NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onMedicineAutoCompleteListener.onMedicineAutoCompleteInternetConnection();
            } else {
                List<MedicineAutoComplete> medicineAutoCompleteList = Arrays.asList(new Gson().fromJson(s, MedicineAutoComplete[].class));
                if (medicineAutoCompleteList.size() > 0) {
                    onMedicineAutoCompleteListener.onMedicineAutoCompleteSuccessFully(medicineAutoCompleteList);
                } else {
                    onMedicineAutoCompleteListener.onMMedicineAutoCompleteNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onMedicineAutoCompleteListener.onMedicineAutoCompleteInternetConnection();
    }

}
