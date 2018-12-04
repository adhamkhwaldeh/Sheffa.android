package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.ActiveMaterialAutoComplete
import com.joyBox.shefaa.entities.MedicineAutoComplete
import com.joyBox.shefaa.networking.listeners.OnActiveMaterialAutoCompleteListener
import com.joyBox.shefaa.networking.listeners.OnMedicineAutoCompleteListener
import com.joyBox.shefaa.networking.tasks.ActiveMaterialAutoCompleteAsync
import com.joyBox.shefaa.networking.tasks.MedicineAutoCompleteAsync


class MedicinePresenter constructor(val context: Context) : MedicineContract.Presenter {
    private lateinit var view: MedicineContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: MedicineContract.View) {
        this.view = view
    }

    override fun loadMedicineAutoCompleteList() {
        MedicineAutoCompleteAsync(object : OnMedicineAutoCompleteListener {

            override fun onMedicineAutoCompleteLoading() {
                view.showProgress(true)
            }

            override fun onMedicineAutoCompleteInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMedicineAutoCompleteSuccessFully(medicineAutoCompleteList: List<MedicineAutoComplete>) {
                view.showProgress(false)
                view.onMedicineAutoCompleteListLoadedSuccessfully(medicineAutoCompleteList.toMutableList())
            }

            override fun onMMedicineAutoCompleteNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun loadActiveMaterialList() {
        ActiveMaterialAutoCompleteAsync(object : OnActiveMaterialAutoCompleteListener {

            override fun onActiveMaterialAutoCompleteLoading() {
                view.showProgress(true)
            }

            override fun onActiveMaterialAutoCompleteInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onActiveMaterialAutoCompleteSuccessFully(alertMedicalList: List<ActiveMaterialAutoComplete>) {
                view.showProgress(false)
                view.onActiveMaterialListLoadedSuccessfully(alertMedicalList.toMutableList())

            }

            override fun onActiveMaterialAutoCompleteNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun unSubscribe() {

    }
}