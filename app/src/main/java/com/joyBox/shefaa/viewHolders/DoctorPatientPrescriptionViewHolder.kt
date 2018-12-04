package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorPatient
import com.joyBox.shefaa.entities.DoctorPatientPrescription

/**
 * Created by Adhamkh on 2018-11-16.
 */
class DoctorPatientPrescriptionViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    @BindView(R.id.patientName)
    lateinit var patientName: TextView

    @BindView(R.id.doctorName)
    lateinit var doctorName: TextView

    @BindView(R.id.prescDate)
    lateinit var prescDate: TextView

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(doctorPatientPrescription: DoctorPatientPrescription) {
        patientName.text = doctorPatientPrescription.patient_Name
        doctorName.text = doctorPatientPrescription.author_Name
        prescDate.text = doctorPatientPrescription.prec_date
    }
}