package com.joyBox.shefaa.activities.autoComplete

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.AppointmentAutoCompleteListViewAdapter
import com.joyBox.shefaa.di.component.DaggerAppointmentAutoCompleteComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.networking.listeners.OnAppiontmentSelectListener
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject

class AppointmentAutoCompleteActivity : BaseActivity(), AppointmentContract.View, OnAppiontmentSelectListener {

    @BindView(R.id.searchView)
    lateinit var searchView: MaterialSearchView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: AppointmentPresenter


    fun initToolBar() {
        toolbar.setTitle(R.string.AppointmentAutoComplete)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initSearch() {
        searchView.setSubmitOnClick(true)
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                KeywordValue.text = query
//                if (KeywordValue.text.isNotEmpty())
//                    KeywordValue.visibility = View.VISIBLE
//                else
//                    KeywordValue.visibility = View.GONE
//                clearData()
                searchView.closeSearch()

//                doctorAutoCompleteEditText.setText((if (query != null) query else ""))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                if (searchView.setAdapter() != null)
//                    doctorPresenter.loadDoctorAutoComplete(NetworkingHelper.Doctors_Short_List_ServiceUrl)
                return false
            }
        })


    }

    fun initDI() {
        val component = DaggerAppointmentAutoCompleteComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadAutoCompleteAppointments("")
//        presenter.loadDoctorAppointments(generateRequestUrl())

    }

    private fun generateRequestUrl(): String {
        val client = UserRepository(baseContext).getClient()!!
        return NetworkingHelper.DoctorAppointmentUrl + "?doctor_id=" + client.user.uid
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_auto_complete)
        ButterKnife.bind(this)
        initToolBar()
        initDI()
        initSearch()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadAutoCompleteAppointments("")
            }

            override fun onRequestPermission() {

            }
        })

//        searchView.showSearch(true)
//        searchView.requestFocus()
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }


    /*Appointment Presenter started*/
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

    override fun onAppointmentAutoCompletLoaded(appointmentAutoCompleteList: MutableList<AppointmentAutoComplete>) {
        searchView.showSearch()
        searchView.setAdapter(AppointmentAutoCompleteListViewAdapter(context = baseContext,
                appointmentAutoCompleteList = appointmentAutoCompleteList,
                onAppiontmentSelectListener = this@AppointmentAutoCompleteActivity))
    }

    override fun onDoctorAppointmentsLoaded(doctorAppointmentList: MutableList<DoctorAppointment>) {
        val list = doctorAppointmentList.map {
            AppointmentAutoComplete(it.nid,
                    it.patient_Name + "  " + it.appointment_Date)
        }.toMutableList()
        onAppointmentAutoCompletLoaded(list)
    }

    /*Doctor Presenter ended*/

    override fun onAppointmentSelect(appointmentAutoComplete: AppointmentAutoComplete) {
        RxBus.publish(MessageEvent(EventActions.AppointmentAutoCompleteActivity_Tag, appointmentAutoComplete))
        finish()
        Log.v("", "")
    }


}