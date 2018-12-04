package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.LabTest
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.entities.models.TestResultAddModel


class TestsResultsContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadAvailableTest(url: String)
        fun loadTestsResults(patientId: String)
        fun addTestResult(testResultAddModel: TestResultAddModel)
    }

    interface View : BaseContract.View {
        fun onTestsResultsLoadedSuccessfully(testResultEntityList: MutableList<TestResultEntity>) {}

        fun onAvailableTestsLoadedSuccessfully(labTests: MutableList<LabTest>) {}

        fun onTestResultAddedSuccessfully() {}
        fun onTestResultAddFail() {}

    }
}