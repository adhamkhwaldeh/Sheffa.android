package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.LabTest
import com.joyBox.shefaa.entities.TestResultEntity

/**
 * Created by Adhamkh on 2018-08-21.
 */
class TestsResultsContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadAvailableTest(url: String)
        fun loadTestsResults(patientId: String)
    }

    interface View : BaseContract.View {
        fun onTestsResultsLoadedSuccessfully(testResultEntityList: MutableList<TestResultEntity>){}

        fun onAvailableTestsLoadedSuccessfully(labTests: MutableList<LabTest>){}
    }
}