package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.networking.listeners.OnMedicineAndPotionResponseListener
import com.joyBox.shefaa.networking.tasks.MedicineAndPotionAsync

class MedicineAndPotionPresenter constructor(val context: Context) : MedicineAndPotionContract.Presenter {
    private lateinit var view: MedicineAndPotionContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: MedicineAndPotionContract.View) {
        this.view = view
    }

    override fun loadMedicineAndPotion(itemId: String) {

        MedicineAndPotionAsync(itemId, object : OnMedicineAndPotionResponseListener {
            override fun onMedicineAndPotionResponseLoading() {
                view.showProgress(true)
            }

            override fun onMedicineAndPotionResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMedicineAndPotionResponseSuccessFuly(medicineAndPotionList: List<MedicinePotionEntity>) {
                view.showProgress(false)
                view.onMedicineAndPotionSuccessfully(medicineAndPotionList)
            }

            override fun onMedicineAndPotionResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun unSubscribe() {

    }

}