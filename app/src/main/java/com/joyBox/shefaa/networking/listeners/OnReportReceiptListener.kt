package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.ReportReceipt

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnReportReceiptListener {
    fun onReportGeneralLoading()

    fun onReportGeneralInternetConnection()

    fun onReportGeneralSuccessFully(reportReceiptList: List<ReportReceipt> /*generalReport: GeneralReport*/)

    fun onReportGeneralNoData()
}