package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.TestResultEntity

/**
 * Created by Adhamkh on 2018-08-21.
 */
class TestsResultsContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadTestsResults(patientId: String)
    }

    interface View : BaseContract.View {
        fun onTestsResultsLoadedSuccessfully(testResultEntityList: List<TestResultEntity>)
    }
}