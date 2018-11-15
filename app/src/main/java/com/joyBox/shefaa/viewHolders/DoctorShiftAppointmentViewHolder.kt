package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.models.AppointmentShiftModel
import com.joyBox.shefaa.enums.AppointmentShiftType

class DoctorShiftAppointmentViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

    var context: Context

    @BindView(R.id.appointmentDateText)
    lateinit var appointmentDateText: TextView

    @BindView(R.id.earlierRadioButton)
    lateinit var earlierRadioButton: RadioButton

    @BindView(R.id.laterRadioButton)
    lateinit var laterRadioButton: RadioButton

    @BindView(R.id.appointmentShiftTimeText)
    lateinit var appointmentShiftTimeText: TextView

    @BindView(R.id.appointmentStartShiftText)
    lateinit var appointmentStartShiftText: TextView

    @BindView(R.id.closeClinicText)
    lateinit var closeClinicText: TextView

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun isValid(): Boolean {
        if (appointmentDateText.text.isNullOrBlank())
            return false
        if (appointmentShiftTimeText.text.isNullOrBlank())
            return false
        if (appointmentStartShiftText.text.isNullOrBlank())
            return false
        if (closeClinicText.text.isNullOrBlank())
            return false

        if ((!earlierRadioButton.isChecked) && (!laterRadioButton.isChecked))
            return false

        return true
    }

    fun getModel(): AppointmentShiftModel {
        var appointmentShiftType: AppointmentShiftType = AppointmentShiftType.Earlier
        if (laterRadioButton.isChecked)
            appointmentShiftType = AppointmentShiftType.Later
        return AppointmentShiftModel(date = appointmentDateText.text.toString(),
                hours = appointmentShiftTimeText.text.toString().split(":")[0],
                minutes = appointmentShiftTimeText.text.toString().split(":")[1],
                clinicCloseHour = closeClinicText.text.toString().split(":")[0],
                clinicCloseMinute = closeClinicText.text.toString().split(":")[1],
                startHour = appointmentStartShiftText.text.toString().split(":")[0],
                startMinute = appointmentStartShiftText.text.toString().split(":")[1],
                appointmentShiftType = appointmentShiftType)
    }


}