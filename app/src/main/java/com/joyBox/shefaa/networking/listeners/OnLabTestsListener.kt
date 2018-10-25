package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.LabTest

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnLabTestsListener {
    fun onLabTestsLoading()

    fun onLabTestsInternetConnection()

    fun onLabTestsSuccessFully(labTestList: MutableList<LabTest>)

    fun onLabTestsNoData()

}