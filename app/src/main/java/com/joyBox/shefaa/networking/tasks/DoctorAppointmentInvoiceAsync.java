package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.AppointmentsConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorAppointmentInvoiceListener;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class DoctorAppointmentInvoiceAsync extends AsyncTask<Void, Void, String> {

    private AppointmentInvoiceModel invoiceModel;
    private OnDoctorAppointmentInvoiceListener onDoctorAppointmentInvoiceListener;

    public DoctorAppointmentInvoiceAsync(AppointmentInvoiceModel invoiceModel, OnDoctorAppointmentInvoiceListener onDoctorAppointmentInvoiceListener) {
        this.invoiceModel = invoiceModel;
        this.onDoctorAppointmentInvoiceListener = onDoctorAppointmentInvoiceListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorAppointmentInvoiceListener.onAppointmentInvoiceLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String jSon = new Gson().toJson(invoiceModel);
        return AppointmentsConnections.getJsonPostWithDataJson(NetworkingHelper.AppointmentInvoiceUrl, jSon, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorAppointmentInvoiceListener.onAppointmentInvoiceInternetConnection();
            } else {
                onDoctorAppointmentInvoiceListener.onAppointmentInvoiceSuccessFully();
            }
            return;
        } catch (Exception ex) {
        }
        onDoctorAppointmentInvoiceListener.onAppointmentInvoiceInternetConnection();

    }
}
