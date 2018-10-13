package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Pharmacy


class PharmacyContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun loadPharmacyList(url: String)
//        fun loadPharmacyAutoComplete(url: String)
//
//        fun loadSpecialistAutoComplete(url: String)

    }

    interface View : BaseContract.View {
        fun onPharmacyListLoadedSuccessfully(pharmacyList: MutableList<Pharmacy>)

//        fun onPharmacyAutoCompleteSuccessfully(PharmacyAutoCompleteList: List<PharmacyAutoComplete>)
//        fun onSpecialistAutoCompleteLoadedSuccessfully(specialistAutoCompleteList: MutableList<SpecialistAutoComplete>){}
    }

}