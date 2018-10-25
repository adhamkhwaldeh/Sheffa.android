package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MedicineAutoComplete

/**
 * Created by Adhamkh on 2018-10-20.
 */
interface OnMedicineAutoCompleteListener {
    fun onMedicineAutoCompleteLoading();

    fun onMedicineAutoCompleteInternetConnection()

    fun onMedicineAutoCompleteSuccessFully(medicineAutoCompleteList: List<MedicineAutoComplete>)

    fun onMMedicineAutoCompleteNoData()
}