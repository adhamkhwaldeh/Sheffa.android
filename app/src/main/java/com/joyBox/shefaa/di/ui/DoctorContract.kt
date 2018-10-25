package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.entities.SpecialistAutoComplete

class DoctorContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun loadDoctorList(url: String)
        fun loadDoctorAutoComplete(url: String)

        fun loadSpecialistAutoComplete(url: String)

    }

    interface View : BaseContract.View {
        fun onDoctorAutoCompleteSuccessfully(doctorAutoCompleteList: List<DoctorAutoComplete>){}
        fun onDoctorListLoadedSuccessfully(doctorList: MutableList<Doctor>){}
        fun onSpecialistAutoCompleteLoadedSuccessfully(specialistAutoCompleteList: MutableList<SpecialistAutoComplete>){}
    }

}