package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.joyBox.shefaa.entities.DoctorPatient
import com.joyBox.shefaa.entities.DoctorPatientPrescription

/**
 * Created by Adhamkh on 2018-11-16.
 */
class DoctorPatientPrescriptionViewHolder   constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(doctorPatientPrescription: DoctorPatientPrescription) {

    }
}