package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MedicinePotionEntity

/**
 * Created by Adhamkh on 2018-08-21.
 */
class MedicineAndPotionContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMedicineAndPotion(itemId: String)
    }

    interface View : BaseContract.View {
        fun onMedicineAndPotionSuccessfully(medicinePotionList:List<MedicinePotionEntity>)
    }
}