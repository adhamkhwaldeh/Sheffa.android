package com.joyBox.shefaa.fragments.prescriptionFragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.MedicineAndPotionRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerMedicineAndPotionComponent
import com.joyBox.shefaa.di.module.MedicineAndPotionModule
import com.joyBox.shefaa.di.ui.MedicineAndPotionContract
import com.joyBox.shefaa.di.ui.MedicineAndPotionPresenter
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class PrescriptionMedicinePotionFragment : BasePrescriptionFragment(), MedicineAndPotionContract.View {

    companion object {
        fun getNewInstance(prescription: Prescription): PrescriptionMedicinePotionFragment {
            val f = PrescriptionMedicinePotionFragment()
            f.titleRes = R.string.Medicine_Potion
            f.prescription = prescription
            return f
        }
    }

    @Inject
    lateinit var presenter: MedicineAndPotionPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView


    private fun initDI() {
        val component = DaggerMedicineAndPotionComponent.builder()
                .medicineAndPotionModule(MedicineAndPotionModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadMedicineAndPotion(prescription.Medicine_and_potion[0].value)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.prescription_medicine_potion_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()
        initRecyclerView()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadMedicineAndPotion(prescription.Medicine_and_potion[0].value)
            }

            override fun onRequestPermission() {

            }
        })

    }


    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onMedicineAndPotionSuccessfully(medicinePotionList: List<MedicinePotionEntity>) {
        recyclerView.adapter = MedicineAndPotionRecyclerViewAdapter(context!!, medicinePotionList)
        Log.v("", "")
    }
    /*Presenter ended*/

}