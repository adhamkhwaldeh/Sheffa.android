package com.joyBox.shefaa.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.CompoundButton
import android.widget.Toast
import butterknife.*
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.di.component.DaggerAppointmentReserveComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.dialogs.DoctorAvailableTimeDialog
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewModels.AppointmentReserveViewHolder
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AppointmentReserveActivity : BaseActivity(), AppointmentContract.View {

    companion object {
        const val AppointmentReserveActivity_Tag = "AppointmentReserveActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private lateinit var appointmentReserveViewHolder: AppointmentReserveViewHolder

    @Inject
    lateinit var presenter: AppointmentPresenter

    var progressDialog: ProgressDialog = ProgressDialog()

    var doctorAutoComplete: DoctorAutoComplete? = null

    private fun initDI() {
        val component = DaggerAppointmentReserveComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    fun initToolBar() {
        toolbar.setTitle(R.string.AddAppointment)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDatePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(this@AppointmentReserveActivity, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    appointmentReserveViewHolder.appointmentDateEditText.setText(sdt)
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    private fun initTimePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(this@AppointmentReserveActivity, R.style.AppTheme_DialogSlideAnimwithback,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.AM_PM, Calendar.AM)
                    val date = calendar.time
                    //SimpleDateFormat sdf12 = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
                    val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                    val sdt = sdf.format(date)
//                    Log.v("hour", S(hourOfDay))
//                    Log.v("minute", String.valueOf(minute))
                    appointmentReserveViewHolder.suggestionHourEditText.setText(sdt)
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true)
        timePicker.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_reserve_layout)
        ButterKnife.bind(this)

        val json: String? = intent.getStringExtra(AppointmentReserveActivity_Tag)
        if (!json.isNullOrBlank()) {
            val doctor: Doctor = Gson().fromJson(json, Doctor::class.java)
            doctorAutoComplete = DoctorAutoComplete(doctor.doctor_id, doctor.doctor_id, doctor.name, doctor.name)
            appointmentReserveViewHolder.doctorAutoComplete = doctorAutoComplete
            appointmentReserveViewHolder.doctorAutoCompleteEditText.setText(doctorAutoComplete!!.nothing)
        }


        appointmentReserveViewHolder = AppointmentReserveViewHolder(findViewById(android.R.id.content))

        initToolBar()
        initDI()

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.DoctorAutoCompleteActivity_Tag -> {
                    val doctorAutoComplete: DoctorAutoComplete = it.message as DoctorAutoComplete
                    appointmentReserveViewHolder.doctorAutoComplete = doctorAutoComplete
                    appointmentReserveViewHolder.doctorAutoCompleteEditText.setText(doctorAutoComplete.nothing)
                }
                EventActions.LocationPickupActivity_Tag -> {

                }
                EventActions.AvailableTimeSelected_Tag -> {
                    appointmentReserveViewHolder.suggestionHourEditText.setText(it.message as String)
                }
            }
        }

    }



    @OnTouch(R.id.doctorAutoCompleteEditText)
    fun onDoctorAutoCompleteEditTextClick(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        IntentHelper.startDoctorAutoCompleteActivity(this@AppointmentReserveActivity)
        return false
    }

    @OnTouch(R.id.appointmentDateEditText)
    fun onAppointmentDateEditTextClick(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        initDatePicker()
        return false
    }

    @OnTouch(R.id.suggestionHourEditText)
    fun onSuggestionHourEditText(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        if (appointmentReserveViewHolder.isValidSelectTime()) {
            val doctorAvailableTimeDialog = DoctorAvailableTimeDialog.newInstance(
                    date = appointmentReserveViewHolder.appointmentDateEditText.text.toString(),
                    doctorAutoComplete = appointmentReserveViewHolder.doctorAutoComplete!!
            )
            doctorAvailableTimeDialog.show(supportFragmentManager,
                    DoctorAvailableTimeDialog.DoctorAvailableTimeDialog_Tag)
        }
        return false
    }

    @OnCheckedChanged(R.id.patientLocationCheckBox)
    fun onPatientLocationCheckBoxChange(view: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            val canonicalName = LocationPickupActivity::class.java.canonicalName
            IntentHelper.startLocationCheckActivity(context = baseContext, activityName = canonicalName)
        }
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (appointmentReserveViewHolder.isValid()) {
            presenter.addAppointment(appointmentReserveViewHolder.getReserveUrl())
        }
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            Toast.makeText(baseContext, R.string.PleaseCheckInternetConnection, Toast.LENGTH_LONG).show()
        }
    }

    override fun onAppointmentAddSuccessfully() {
        Toast.makeText(baseContext, R.string.AppointmentAddSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentAddFail() {
        Toast.makeText(baseContext, R.string.AppointmentAddFailed, Toast.LENGTH_LONG).show()
    }

    /*Presenter ended*/
}