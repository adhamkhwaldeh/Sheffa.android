package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MedicinePotionEntity

class MedicineAndPotionContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMedicineAndPotion(itemId: String)
    }

    interface View : BaseContract.View {
        fun onMedicineAndPotionSuccessfully(medicinePotionList:List<MedicinePotionEntity>)
    }
}