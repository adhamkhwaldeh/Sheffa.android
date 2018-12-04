package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.ActiveMaterialAutoComplete
import com.joyBox.shefaa.entities.MedicineAutoComplete

class MedicineContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMedicineAutoCompleteList()
        fun loadActiveMaterialList()
    }

    interface View : BaseContract.View {
        fun onMedicineAutoCompleteListLoadedSuccessfully(medicineList: MutableList<MedicineAutoComplete>){}
        fun onActiveMaterialListLoadedSuccessfully(activeMaterialAutoCompleteList:MutableList<ActiveMaterialAutoComplete>){}
    }
}