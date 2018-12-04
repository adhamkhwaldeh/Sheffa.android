package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorPatient

class DoctorPatientViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    @BindView(R.id.patientName)
    lateinit var patientName: TextView

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(doctorPatient: DoctorPatient) {
        patientName.text = doctorPatient.requester_id
    }

}