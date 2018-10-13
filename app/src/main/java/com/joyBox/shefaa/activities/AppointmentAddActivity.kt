package com.joyBox.shefaa.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.AppointmentAvailableTimeAdapter
import com.joyBox.shefaa.di.component.DaggerAppointmentAddComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.entities.AvailableTime
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.enums.AppointmentStatus
import com.joyBox.shefaa.repositories.UserRepositoy
import com.joyBox.shefaa.views.GridDividerDecoration
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AppointmentAddActivity : BaseActivity(), AppointmentContract.View {

    companion object {
        const val AppointmentAddActivity_Tag = "AppointmentAddActivity_Tag"
    }

    @Inject
    lateinit var presenter: AppointmentPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.dateText)
    lateinit var dateText: TextView

    @BindView(R.id.calendarView)
    lateinit var calendarView: CalendarView

    @BindView(R.id.appointmentType)
    lateinit var appointmentType: SwitchCompat

    @BindView(R.id.urgentCheckBox)
    lateinit var urgentCheckBox: CheckBox

    @BindView(R.id.availableTimeRecyclerView)
    lateinit var availableTimeRecyclerView: RecyclerView

    @BindView(R.id.appointmentTime)
    lateinit var appointmentTime: TextView

    @BindView(R.id.urgentCause)
    lateinit var urgentCause: TextView

    lateinit var doctor: Doctor

    private fun initToolBar() {
        toolbar.setTitle(R.string.NewAppointment)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initialDate() {
        val calendar = Calendar.getInstance()
        calendarView.date = calendar.time.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        dateText.text = dateFormat.format(calendar.time)
    }

    private fun initRecyclerView() {
        availableTimeRecyclerView.layoutManager = LinearLayoutManager(this)
        availableTimeRecyclerView.addItemDecoration(GridDividerDecoration(this))

    }

    private fun initDI() {
        val component = DaggerAppointmentAddComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadAvailableTime(doctorId = doctor.doctor_id, date = dateText.text.toString())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_add_layout)
        ButterKnife.bind(this)
        val json = intent.getStringExtra(AppointmentAddActivity_Tag)
        doctor = Gson().fromJson(json, Doctor::class.java)

        initToolBar()
        initialDate()
        initRecyclerView()
        initDI()

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = Calendar.getInstance(Locale.ENGLISH)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            dateText.text = dateFormat.format(date.time)
            presenter.loadAvailableTime(doctorId = doctor.doctor_id, date = dateText.text.toString())
        }

    }

    @OnClick(R.id.requestBtn)
    fun onRequestButtonClick(view: View) {
//        var xx = UserRepositoy(this).getClient()!!.user.location
//        var x = UserRepositoy(this).getClient()!!.user.locations
        bookAppointment()
        Log.v("", "")
    }

    private fun bookAppointment() {
        var tm = ""
        if (availableTimeRecyclerView.adapter != null) {
            val availableTimeAdapter = availableTimeRecyclerView.adapter as AppointmentAvailableTimeAdapter
            tm = availableTimeAdapter.selectedtime
        }
        if (tm != "" || appointmentTime.getText().toString() != "") {

            val usrId = UserRepositoy(this).getClient()!!.user.uid
            val dt = dateText.text.toString()
            if (appointmentTime.text.toString() != "")
                tm = appointmentTime.text.toString()
            val urg = if (urgentCheckBox.isChecked) AppointmentStatus.Urgent.status else AppointmentStatus.NotUrgent.status
            val cause = urgentCause.text.toString()

//            RequestAppointment().execute(Utl.RequestAppointmentUrl + "?doctor_id=" + DoctorId + "&patient_id=" + usrId
//                    + "&date=" + dt + "&time=" + tm + "&urgent=" + urg + "&urgent_cause=" + cause)
        } else {
            Toast.makeText(baseContext, resources.getString(R.string.PleaseSelectTime), Toast.LENGTH_LONG).show()
        }
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {

    }

    override fun showEmptyView(visible: Boolean) {

    }

    override fun showLoadErrorMessage(visible: Boolean) {

    }

    override fun onAvailableTimeLoadedSuccessfully(availableTime: AvailableTime) {
        availableTimeRecyclerView.adapter = AppointmentAvailableTimeAdapter(this, availableTime.availableTimes)
        Log.v("", "")
    }

    override fun onAppointmentAddSuccessfully() {

    }
    /*Presenter ended*/
}