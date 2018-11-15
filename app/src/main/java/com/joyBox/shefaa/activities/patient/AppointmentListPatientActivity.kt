package com.joyBox.shefaa.activities.patient

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.AppointmentPatientRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerAppointmentListPatientComponent
import com.joyBox.shefaa.di.module.AppointmentListPatientModule
import com.joyBox.shefaa.di.ui.AppointmentListPatientContract
import com.joyBox.shefaa.di.ui.AppointmentListPatientPresenter
import com.joyBox.shefaa.entities.AppointmentEntity
import com.joyBox.shefaa.enums.AppointmentPlace
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import it.beppi.tristatetogglebutton_library.TriStateToggleButton
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AppointmentListPatientActivity : BaseActivity(), AppointmentListPatientContract.View {

    @Inject
    lateinit var presenter: AppointmentListPatientPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.dateText)
    lateinit var dateText: TextView

    @BindView(R.id.appointmentPlace)
    lateinit var appointmentPlace: TriStateToggleButton

    lateinit var calendar: Calendar

    private fun initDI() {
        val component = DaggerAppointmentListPatientComponent.builder()
                .appointmentListPatientModule(AppointmentListPatientModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        val client = UserRepository(this).getClient()!!
        presenter.loadAppointments(client.user.uid)
    }

    private fun initToolbar() {
        toolbar.setTitle(R.string.Appointments)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GridDividerDecoration(this))
    }

    private fun initDate() {
        calendar = Calendar.getInstance()
        val date = calendar.time
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val sdt = sdf.format(date)
        dateText.text = (sdt)
    }

    private fun initTimePicker() {
        val timePicker = DatePickerDialog(this@AppointmentListPatientActivity, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    dateText.text = sdt
                    filtering()
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    private fun filtering() {
        var isHome = AppointmentPlace.Home.place
        when (appointmentPlace.toggleStatus) {
            TriStateToggleButton.ToggleStatus.off -> {
                isHome = AppointmentPlace.Home.place
            }
            TriStateToggleButton.ToggleStatus.on -> {
                isHome = AppointmentPlace.Clinic.place
            }
            TriStateToggleButton.ToggleStatus.mid -> {
                isHome = AppointmentPlace.NOTSET.place
            }
        }
        val dt = dateText.text.toString()

        if (recyclerView.adapter != null) {
            (recyclerView.adapter as AppointmentPatientRecyclerViewAdapter).filter.filter("$isHome=>$dt")
        }

//        val isUrgent = if (!urgent.toggleStatus) AppointmentStatus.NotUrgent.status else AppointmentStatus.Urgent.status
//        val isApproved = if (Approved.isChecked()) "0" else "1"
//
//        if (Appointmentrecyclerview.getAdapter() != null) {
//            (Appointmentrecyclerview.getAdapter() as AppointmentAdapter).getFilter().filter("$isurgent=>$isApproved=>$dt")
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_list_patient_layout)
        ButterKnife.bind(this)
        initDI()
        initToolbar()
        initRecyclerView()
        initDate()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                val client = UserRepository(this@AppointmentListPatientActivity).getClient()!!
                presenter.loadAppointments(client.user.uid)
            }

            override fun onRequestPermission() {

            }
        })

        appointmentPlace.setOnToggleChanged(object : TriStateToggleButton.OnToggleChanged {
            override fun onToggle(toggleStatus: TriStateToggleButton.ToggleStatus?,
                                  booleanToggleStatus: Boolean, toggleIntValue: Int) {
                when (toggleStatus) {
                    TriStateToggleButton.ToggleStatus.off -> {
                        filtering()
                    }
                    TriStateToggleButton.ToggleStatus.on -> {
                        filtering()
                    }
                    TriStateToggleButton.ToggleStatus.mid -> {
                        filtering()
                    }
                }
            }
        })

    }

    @OnClick(R.id.dateBtn)
    fun onDateButtonClick(view: View) {
        initTimePicker()
    }

    @OnClick(R.id.reserveAppointmentBtn)
    fun onReserveAppointmentButtonClick(view: View) {
        IntentHelper.startAppointmentReserveActivity(this)
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

    override fun onAppointmentsLoadedSuccesfuly(appointmentList: List<AppointmentEntity>) {
        recyclerView.adapter = AppointmentPatientRecyclerViewAdapter(AppointmentListPatientActivity@ this, appointmentList)
        Log.v("", "")
    }
    /*Presenter ended*/

}