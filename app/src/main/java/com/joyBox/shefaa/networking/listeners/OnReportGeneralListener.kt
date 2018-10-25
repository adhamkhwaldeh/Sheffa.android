package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.GeneralReport

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnReportGeneralListener {
    fun onReportGeneralLoading()

    fun onReportGeneralInternetConnection()

    fun onReportGeneralSuccessFully(generalReport: GeneralReport)

    fun onReportGeneralFail()
}