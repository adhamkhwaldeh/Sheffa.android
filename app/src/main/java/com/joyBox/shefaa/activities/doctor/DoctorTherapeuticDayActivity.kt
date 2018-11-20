package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.DoctorAppointmentTreatmentRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorTherapeuticDayComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorTherapeuticDayActivity : BaseActivity(), AppointmentContract.View/*, DoctorContract.View*/ {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

//    @Inject
//    lateinit var presenter: DoctorPresenter

    @Inject
    lateinit var appointmentPresenter: AppointmentPresenter

    private fun initToolbar() {
        toolbar.setTitle(R.string.ManageAppointments)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun generateRequestUrl(): String {
        val client = UserRepository(baseContext).getClient()!!
        return NetworkingHelper.DoctorAppointmentUrl + "?doctor_id=" + client.user.uid
//        urgent(0-1), home(0-1), date(Format: 14/08/2018)

    }

    fun initDI() {
        val component = DaggerDoctorTherapeuticDayComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .doctorModule(DoctorModule(this))
                .build()
        component.inject(this)
//        presenter.attachView(this)
//        presenter.subscribe()

        appointmentPresenter.attachView(this)
        appointmentPresenter.subscribe()
        appointmentPresenter.loadDoctorAppointments(generateRequestUrl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_therapeutic_day_layout)
        ButterKnife.bind(this)
        initToolbar()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                appointmentPresenter.loadDoctorAppointments(generateRequestUrl())
            }

            override fun onRequestPermission() {

            }
        })
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

    override fun onDoctorAppointmentsLoaded(doctorAppointmentList: MutableList<DoctorAppointment>) {
        recyclerView.adapter = DoctorAppointmentTreatmentRecyclerViewAdapter(context = baseContext!!,
                doctorAppointmentList = doctorAppointmentList)
    }
    /*Doctor presenter ended*/

}