package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.PaymentType;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnPaymentTypeListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class PaymentTypeAsync extends AsyncTask<Void, Void, String> {

    public OnPaymentTypeListener onPaymentTypeListener;

    public PaymentTypeAsync(OnPaymentTypeListener onPaymentTypeListener) {
        this.onPaymentTypeListener = onPaymentTypeListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPaymentTypeListener.onPaymentTypeLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.PaymentTypeUrl, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPaymentTypeListener.onPaymentTypeInternetConnection();
            } else {
                List<PaymentType> paymentTypeList = JsonParser.getPaymentTypes(s);
                if (paymentTypeList.size() > 0) {
                    onPaymentTypeListener.onPaymentTypeSuccessFully(paymentTypeList);
                } else {
                    onPaymentTypeListener.onPaymentTypeNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onPaymentTypeListener.onPaymentTypeInternetConnection();
    }
}
