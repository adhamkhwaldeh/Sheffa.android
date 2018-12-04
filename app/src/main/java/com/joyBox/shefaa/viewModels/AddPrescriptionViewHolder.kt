package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAppointment

class AddPrescriptionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val context: Context = view.context

    @BindView(R.id.patientName)
    lateinit var patientName: EditText

    var doctorAppointment: DoctorAppointment? = null


    init {
        ButterKnife.bind(this, view)
    }

    fun bindDoctorAppointment(doctorAppointment: DoctorAppointment) {
        this.doctorAppointment = doctorAppointment
        patientName.setText(doctorAppointment.patient_Name)
    }

}