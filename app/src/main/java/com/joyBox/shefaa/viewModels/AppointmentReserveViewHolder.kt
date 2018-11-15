package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.entities.PointEntity
import com.joyBox.shefaa.enums.AppointmentStatus
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository

class AppointmentReserveViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.doctorAutoCompleteEditText)
    lateinit var doctorAutoCompleteEditText: EditText

    @BindView(R.id.appointmentDateEditText)
    lateinit var appointmentDateEditText: EditText

    @BindView(R.id.suggestionHourEditText)
    lateinit var suggestionHourEditText: EditText

    @BindView(R.id.urgentCheckBox)
    lateinit var urgentCheckBox: CheckBox

    @BindView(R.id.appointmentCause)
    lateinit var appointmentCause: EditText

    @BindView(R.id.patientLocationCheckBox)
    lateinit var patientLocationCheckBox: CheckBox

    var doctorAutoComplete: DoctorAutoComplete? = null

    var pointEntity: PointEntity? = null

    val context: Context = view.context

    init {
        ButterKnife.bind(this, view)
    }

    fun isValidSelectTime(): Boolean {
        if (doctorAutoComplete == null) {
            Toast.makeText(context, R.string.PleaseSelectDoctor, Toast.LENGTH_LONG).show()
            return false
        }
        if (appointmentDateEditText.text.isNullOrEmpty()) {
            Toast.makeText(context, R.string.pleaseSelectDate, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun isValid(): Boolean {
        if (!isValidSelectTime())
            return false
        if (suggestionHourEditText.text.isNullOrEmpty()) {
            Toast.makeText(context, R.string.pleaseSelectTime, Toast.LENGTH_LONG).show()
            return false
        }
        if (patientLocationCheckBox.isChecked) {
            if (pointEntity == null) {
                Toast.makeText(context, R.string.pleaseSelectHomeLocation, Toast.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }

    fun getReserveUrl(): String {
        val usrId = UserRepository(context).getClient()!!.user.uid
        val dt = appointmentDateEditText.text.toString()
        val tm = suggestionHourEditText.text.toString()
        val urg = if (urgentCheckBox.isChecked) AppointmentStatus.Urgent.status else AppointmentStatus.NotUrgent.status
        val cause = appointmentCause.text.toString()
        val doctorId = doctorAutoComplete!!.doctor_id

        var url = NetworkingHelper.Appointment_Book_Url + "?doctor_id=" + doctorId + "&patient_id=" + usrId +
                "&date=" + dt + "&time=" + tm + "&urgent=" + urg + "&urgent_cause=" + cause
        if (patientLocationCheckBox.isChecked) {
            url += "&home=1&long=" + pointEntity!!.lng.toString() + "&lat=" + pointEntity!!.lat.toString()
        } else {
            url += "&home=0"
        }

        return url
    }


}