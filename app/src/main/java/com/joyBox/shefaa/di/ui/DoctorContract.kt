package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.*

class DoctorContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun loadDoctorList(url: String)
        fun loadDoctorAutoComplete(url: String)

        fun loadSpecialistAutoComplete(url: String)

        fun loadDoctorPatients()

        fun loadDoctorPatientPrescription(patientId: String, doctorName: String)

        fun loadDoctorTestResult(doctorId: String)
    }

    interface View : BaseContract.View {
        fun onDoctorAutoCompleteSuccessfully(doctorAutoCompleteList: List<DoctorAutoComplete>) {}
        fun onDoctorListLoadedSuccessfully(doctorList: MutableList<Doctor>) {}
        fun onSpecialistAutoCompleteLoadedSuccessfully(specialistAutoCompleteList: MutableList<SpecialistAutoComplete>) {}
        fun onMyPatientsLoaded(doctorPatientList: MutableList<DoctorPatient>) {}
        fun onDoctorPatientPrescriptionLoaded(doctorPatientPrescription: MutableList<DoctorPatientPrescription>) {}
        fun onTestsResultsLoadedSuccessfully(testResultEntityList: MutableList<TestResultEntity>) {}
    }

}