package com.joyBox.shefaa.fragments.doctorAppointmentFragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerDoctorShiftAppointmentComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.viewHolders.DoctorShiftAppointmentViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DoctorShiftAppointmentFragment : BaseDoctorAppointmentFragment(), AppointmentContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorAppointmentFragment {
            val f = DoctorShiftAppointmentFragment()
            f.titleRes = R.string.ShiftAppoitment
            return f
        }
    }

    @Inject
    lateinit var presenter: AppointmentPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    lateinit var doctorShiftAppointmentViewHolder: DoctorShiftAppointmentViewHolder


    fun initDI() {
        val component = DaggerDoctorShiftAppointmentComponent.builder()
                .appointmentModule(AppointmentModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

    }


    private fun initDatePicker(view: TextView) {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(context, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    view.text = sdt
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }


    private fun initTimePicker(view: TextView) {
        var calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(context, R.style.AppTheme_DialogSlideAnimwithback,
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
                    view.text = sdt
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true)
        timePicker.show()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_shift_appointment_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        doctorShiftAppointmentViewHolder = DoctorShiftAppointmentViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()

    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (doctorShiftAppointmentViewHolder.isValid()) {
            presenter.shiftAppointment(NetworkingHelper.DoctorAppointmentShiftUrl, doctorShiftAppointmentViewHolder.getModel())
        } else {
            Toast.makeText(context, R.string.AllRecordsAreMandatory, Toast.LENGTH_LONG).show()
        }
    }

    @OnClick(R.id.appointmentDateText)
    fun onAppointmentDateTextClick(view: View) {
        initDatePicker(view as TextView)
    }

    @OnClick(R.id.appointmentShiftTimeText, R.id.appointmentStartShiftText, R.id.closeClinicText)
    fun OnTimeClick(view: View) {
        initTimePicker(view as TextView)
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if(visible){
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        }else{
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onAppointmentShiftedSuccessfully() {
        Toast.makeText(context, resources.getText(R.string.AppointmentShiftSuccessfully), Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentShiftedFailed() {
        Toast.makeText(context, resources.getText(R.string.AppointmentShiftFailed), Toast.LENGTH_LONG).show()
    }

    /*Presenter ended*/
}
