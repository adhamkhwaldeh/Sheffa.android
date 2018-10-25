package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.ReportExpense

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnReportExpenseListener {

    fun onReportExpenseLoading()

    fun onReportExpenseInternetConnection()

    fun onReportExpenseSuccessFully(reportExpenseList: List<ReportExpense>)

    fun onReportExpenseNoData()
}