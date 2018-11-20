package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.AppointmentPlace
import com.joyBox.shefaa.enums.AppointmentStatus

/**
 * Created by Adhamkh on 2018-11-15.
 */
class DoctorAppointmentDetailsViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    lateinit var doctorAppointment: DoctorAppointment

    @BindView(R.id.patientId)
    lateinit var patientId: TextView

    @BindView(R.id.appointmentId)
    lateinit var appointmentId: TextView

    @BindView(R.id.patientName)
    lateinit var patientName: TextView

    @BindView(R.id.appointmentDate)
    lateinit var appointmentDate: TextView

    @BindView(R.id.shiftTime)
    lateinit var shiftTime: TextView

    @BindView(R.id.urgentCheckBox)
    lateinit var urgentCheckBox: CheckBox

    @BindView(R.id.appointmentCause)
    lateinit var appointmentCause: TextView

    @BindView(R.id.homeCheckBox)
    lateinit var homeCheckBox: CheckBox

    @BindView(R.id.patientAddress)
    lateinit var patientAddress: TextView

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(doctorAppointment: DoctorAppointment) {
        this.doctorAppointment = doctorAppointment

        patientId.setText(doctorAppointment.patient_ID)
        appointmentId.setText(doctorAppointment.nid)

        patientName.text = doctorAppointment.patient_Name
        appointmentDate.text = doctorAppointment.appointment_Date
        shiftTime.text = doctorAppointment.appointment_start_time

        urgentCheckBox.isChecked = (doctorAppointment.urgent_appointment == (AppointmentStatus.Urgent.status)) ||
                (doctorAppointment.urgent_appointment == (AppointmentStatus.UrgentYes.status))

        appointmentCause.text = doctorAppointment.urgent_appointment_cause

        homeCheckBox.isChecked = doctorAppointment.home_appointment == AppointmentPlace.Home.place

        if (doctorAppointment.patient_home_address != null)
            patientAddress.text = doctorAppointment.patient_home_address.city


    }

}