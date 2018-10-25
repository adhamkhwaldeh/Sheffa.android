package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.models.PaymentAddModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.PaymentConnections;
import com.joyBox.shefaa.networking.listeners.OnPaymentAddListener;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class PaymentAddAsync extends AsyncTask<Void, Void, String> {

    public PaymentAddModel paymentAddModel;
    public OnPaymentAddListener onPaymentAddListener;

    public PaymentAddAsync(PaymentAddModel paymentAddModel, OnPaymentAddListener onPaymentAddListener) {
        this.paymentAddModel = paymentAddModel;
        this.onPaymentAddListener = onPaymentAddListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPaymentAddListener.onPaymentAddLoading();

    }

    @Override
    protected String doInBackground(Void... voids) {
        return PaymentConnections.getJsonPostWithDataJson(NetworkingHelper.PaymentAddUrl, paymentAddModel.getData(), NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onPaymentAddListener.onPaymentAddInternetConnection();
            } else {
                onPaymentAddListener.onPaymentAddSuccessFully();
            }
            return;
        } catch (Exception ex) {

        }
        onPaymentAddListener.onPaymentAddInternetConnection();
    }
}
