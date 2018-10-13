package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.entities.Medicine_and_potion

/**
 * Created by Adhamkh on 2018-08-21.
 */
interface OnMedicineAndPotionResponseListener {
    fun onMedicineAndPotionResponseLoading();

    fun onMedicineAndPotionResponseInternetConnection()

    fun onMedicineAndPotionResponseSuccessFuly(medicineAndPotionList: List<MedicinePotionEntity>)

    fun onMedicineAndPotionResponseNoData()
}