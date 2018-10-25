package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.joyBox.shefaa.entities.DoctorAppointment

/**
 * Created by Adhamkh on 2018-10-24.
 */
class DoctorAppointmentViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(doctorAppointment: DoctorAppointment) {

    }

}