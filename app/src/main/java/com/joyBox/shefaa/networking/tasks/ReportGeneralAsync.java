package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.joyBox.shefaa.entities.GeneralReport;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.ReportsConnections;
import com.joyBox.shefaa.networking.listeners.OnReportGeneralListener;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class ReportGeneralAsync extends AsyncTask<Void, Void, String> {

    public String year;
    public String month;
    public String day;
    public OnReportGeneralListener onReportGeneralListener;

    public ReportGeneralAsync(String year, String month, String day, OnReportGeneralListener onReportGeneralListener) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.onReportGeneralListener = onReportGeneralListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onReportGeneralListener.onReportGeneralLoading();
    }


    @Override
    protected String doInBackground(Void... voids) {
        return ReportsConnections.getJson(NetworkingHelper.ReportGeneralUrl +
                "?year=" + year + "&month=" + month + "&day=" + day, NetworkingHelper.RequestTimeout);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onReportGeneralListener.onReportGeneralInternetConnection();
            } else {
                GeneralReport generalReport = new Gson().fromJson(s, GeneralReport.class);
                onReportGeneralListener.onReportGeneralSuccessFully(generalReport);
            }
            return;
        } catch (Exception ex) {

        }
        onReportGeneralListener.onReportGeneralInternetConnection();
    }
}
