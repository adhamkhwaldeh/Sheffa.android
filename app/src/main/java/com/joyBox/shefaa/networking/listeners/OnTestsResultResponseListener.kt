package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.TestResultEntity

/**
 * Created by Adhamkh on 2018-08-21.
 */
interface OnTestsResultResponseListener {
    fun onTestsResultResponseLoading();

    fun onTestsResultResponseInternetConnection()

    fun onTestsResultResponseSuccessFully(testResultEntityList: List<TestResultEntity>)

    fun onTestsResultResponseNoData()
}