package com.joyBox.shefaa.fragments.doctorPatientsFragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnCheckedChanged
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorPatientPrescriptionRecyclerViewAdapter
import com.joyBox.shefaa.adapters.DoctorPatientSpinnerAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorPatientsPrescriptionsComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.DoctorPatient
import com.joyBox.shefaa.entities.DoctorPatientPrescription
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorPatientsPrescriptionsFragment : BaseDoctorPatientsFragment(), DoctorContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorPatientsFragment {
            val f = DoctorPatientsPrescriptionsFragment()
            f.titleRes = R.string.MyPatientsPrescriptions
            return f
        }
    }

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.patientsSpinner)
    lateinit var patientsSpinner: Spinner

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.isMyPrescriptionCheckBox)
    lateinit var isMyPrescriptionCheckBox: CheckBox

    @Inject
    lateinit var presenter: DoctorPresenter

    var isPatientLoading: Boolean = true

    fun initRecyclerView() {
        recyclerView.addItemDecoration(GridDividerDecoration(context))
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun initDI() {
        val component = DaggerDoctorPatientsPrescriptionsComponent.builder()
                .doctorModule(DoctorModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadDoctorPatients()
    }

    fun prescriptionRequest() {
        if (patientsSpinner.selectedItem != null) {
            val patient = patientsSpinner.selectedItem as DoctorPatient
            var doctorName = ""
            if (isMyPrescriptionCheckBox.isChecked) {
                doctorName = UserRepository(context!!).getClient()!!.user.name
            }
            presenter.loadDoctorPatientPrescription(patientId = patient.uid, doctorName = doctorName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_patients_prescription_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()
        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                if (isPatientLoading)
                    presenter.loadDoctorPatients()
                else
                    prescriptionRequest()
            }

            override fun onRequestPermission() {

            }
        })

        patientsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                prescriptionRequest()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    @OnCheckedChanged(R.id.isMyPrescriptionCheckBox)
    fun onMyPrescriptionCheckBoxChange(view: CompoundButton, isChecked: Boolean) {
        prescriptionRequest()
    }


    /*Doctor presenter started*/
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

    override fun onMyPatientsLoaded(doctorPatientList: MutableList<DoctorPatient>) {
        patientsSpinner.adapter = DoctorPatientSpinnerAdapter(context!!, doctorPatientList)
        isPatientLoading = false
        prescriptionRequest()
    }

    override fun onDoctorPatientPrescriptionLoaded(doctorPatientPrescription: MutableList<DoctorPatientPrescription>) {
        recyclerView.adapter = DoctorPatientPrescriptionRecyclerViewAdapter(context!!, doctorPatientPrescription)
    }

    /*Doctor presenter ended*/

}