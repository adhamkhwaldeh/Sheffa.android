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
import com.joyBox.shefaa.adapters.PrescriptionFollowUpRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerPrescriptionFollowUpComponent
import com.joyBox.shefaa.di.module.PrescriptionFollowUpModule
import com.joyBox.shefaa.di.ui.PrescriptionFollowUpContract
import com.joyBox.shefaa.di.ui.PrescriptionFollowUpPresenter
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class PrescriptionFollowUpFragment : BasePrescriptionFragment(), PrescriptionFollowUpContract.View {


    companion object {
        fun getNewInstance(prescription: Prescription): PrescriptionFollowUpFragment {
            val f = PrescriptionFollowUpFragment()
            f.titleRes = R.string.Diagnosis
            f.prescription = prescription
            return f
        }
    }


    @Inject
    lateinit var presenter: PrescriptionFollowUpPresenter


    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private fun initDI() {
        val component = DaggerPrescriptionFollowUpComponent.builder()
                .prescriptionFollowUpModule(PrescriptionFollowUpModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        if (prescription.Indicators_to_follow_up.size > 0)
            presenter.loadPrescriptionFollowUp(prescription.Indicators_to_follow_up[0].value)
        else
            showEmptyView(true)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.prescription_follow_up_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()
        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadPrescriptionFollowUp(prescription.Indicators_to_follow_up[0].value)
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

    override fun onPrescriptionFollowUpSuccessfully(prescriptionFollowUpList: List<PrescriptionFollowUp>) {
        recyclerView.adapter = PrescriptionFollowUpRecyclerViewAdapter(context = context!!,
                prescriptionFollowUpList = prescriptionFollowUpList, prescription = prescription)
        Log.v("", "")
    }
    /*Presenter ended*/

}