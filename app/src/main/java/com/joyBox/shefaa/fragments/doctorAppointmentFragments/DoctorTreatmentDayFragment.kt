package com.joyBox.shefaa.fragments.doctorAppointmentFragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorAppointmentRecyclerViewAdapter
import com.joyBox.shefaa.adapters.DoctorAppointmentTreatmentRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorTreatmentDayComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.DateHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorTreatmentDayFragment : BaseDoctorAppointmentFragment(), AppointmentContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorAppointmentFragment {
            val f = DoctorTreatmentDayFragment()
            f.titleRes = R.string.DoctorTreatment
            return f
        }
    }

    @Inject
    lateinit var presenter: AppointmentPresenter

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview


    fun initDI() {
        val component = DaggerDoctorTreatmentDayComponent.builder()
                .appointmentModule(AppointmentModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadDoctorAppointments(generateRequestUrl())
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }

    private fun generateRequestUrl(): String {
        val client = UserRepository(context!!).getClient()!!
        return NetworkingHelper.DoctorAppointmentUrl + "?doctor_id=" + client.user.uid + "&date" + DateHelper.getCurrentDate()
//        urgent(0-1), home(0-1), date(Format: 14/08/2018)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_treatment_day_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()
        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadDoctorAppointments(generateRequestUrl())
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

    override fun onDoctorAppointmentsLoaded(doctorAppointmentList: MutableList<DoctorAppointment>) {
        recyclerView.adapter = DoctorAppointmentTreatmentRecyclerViewAdapter(context = context!!,
                doctorAppointmentList = doctorAppointmentList)
    }
    /*Presenter ended*/

}