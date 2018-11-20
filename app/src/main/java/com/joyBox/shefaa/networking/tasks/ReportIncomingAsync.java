package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.ReportReceipt;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.ReportsConnections;
import com.joyBox.shefaa.networking.listeners.OnReportReceiptListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class ReportIncomingAsync extends AsyncTask<Void, Void, String> {

    public String startDate;
    public String endDate;
    public OnReportReceiptListener onReportReceiptListener;

    public ReportIncomingAsync(String startDate, String endDate, OnReportReceiptListener onReportReceiptListener) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.onReportReceiptListener = onReportReceiptListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onReportReceiptListener.onReportGeneralLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String url = NetworkingHelper.ReportReceiptUrl;
        if ((startDate == null) || (endDate == null))
            url += "?from_date=" + startDate + " 00:00:00" + "&to_date=" + endDate + " 00:00:00";
        return ReportsConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse))
                onReportReceiptListener.onReportGeneralInternetConnection();
            else {
                List<ReportReceipt> reportReceiptList = JsonParser.getReportReciepts(s);
                if (reportReceiptList.size() > 0)
                    onReportReceiptListener.onReportGeneralSuccessFully(reportReceiptList);
                else
                    onReportReceiptListener.onReportGeneralNoData();
            }
            return;
        } catch (Exception ex) {
        }
        onReportReceiptListener.onReportGeneralInternetConnection();
    }
}
