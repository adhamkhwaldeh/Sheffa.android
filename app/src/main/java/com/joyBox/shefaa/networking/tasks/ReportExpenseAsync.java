package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.ReportExpense;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.ReportsConnections;
import com.joyBox.shefaa.networking.listeners.OnReportExpenseListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class ReportExpenseAsync extends AsyncTask<Void, Void, String> {

    public String startDate;
    public String endDate;
    public OnReportExpenseListener onReportExpenseListener;

    public ReportExpenseAsync(String startDate, String endDate, OnReportExpenseListener onReportExpenseListener) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.onReportExpenseListener = onReportExpenseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onReportExpenseListener.onReportExpenseLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String url = NetworkingHelper.ReportGeneralUrl;
        if ((startDate == null) || (endDate == null))
            url += "?from_date=" + startDate + "&to_date=" + endDate;
        return ReportsConnections.getJson(url, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse))
                onReportExpenseListener.onReportExpenseInternetConnection();
            else {
                List<ReportExpense> reportReceiptList = JsonParser.getReportExpenses(s);
                if (reportReceiptList.size() > 0)
                    onReportExpenseListener.onReportExpenseSuccessFully(reportReceiptList);
                else
                    onReportExpenseListener.onReportExpenseNoData();
            }
            return;
        } catch (Exception ex) {
        }
        onReportExpenseListener.onReportExpenseInternetConnection();
    }
}

