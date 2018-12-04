package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.AppointmentStatus
import org.w3c.dom.Text


class DoctorAppointmentTreatmentViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    @BindView(R.id.startAppointment)
    lateinit var startAppointment: Button

    @BindView(R.id.endAppointment)
    lateinit var endAppointment: Button

    @BindView(R.id.addPrescriptionBtn)
    lateinit var addPrescriptionBtn: Button

    @BindView(R.id.patientName)
    lateinit var patientName: TextView

    @BindView(R.id.startTime)
    lateinit var startTime: TextView

    @BindView(R.id.shiftTime)
    lateinit var shiftTime: TextView

    @BindView(R.id.isUrgent)
    lateinit var isUrgentView: View

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(doctorAppointment: DoctorAppointment) {
        patientName.text = doctorAppointment.patient_Name
        startTime.text = doctorAppointment.appointment_start_time
        shiftTime.text = doctorAppointment.appointment_Date
        val isUrgent = doctorAppointment.urgent_appointment
        if (isUrgent.equals(AppointmentStatus.Urgent.status) || isUrgent.equals(AppointmentStatus.UrgentYes.status)) {
            //            cardView.setBackgroundColor(context.getResources().getColor(R.color.redcolor));
            isUrgentView.setVisibility(View.VISIBLE)
        }
    }

}