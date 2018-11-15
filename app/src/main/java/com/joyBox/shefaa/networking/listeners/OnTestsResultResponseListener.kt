package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.TestResultEntity

interface OnTestsResultResponseListener {
    fun onTestsResultResponseLoading()

    fun onTestsResultResponseInternetConnection()

    fun onTestsResultResponseSuccessFully(testResultEntityList: MutableList<TestResultEntity>)

    fun onTestsResultResponseNoData()
}