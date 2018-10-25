package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorAppointmentViewHolder
import com.joyBox.shefaa.viewHolders.DoctorViewHolder

/**
 * Created by Adhamkh on 2018-10-24.
 */
class DoctorAppointmentRecyclerViewAdapter(val context: Context, val doctorAppointmentList: MutableList<DoctorAppointment>) :
        RecyclerView.Adapter<DoctorAppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAppointmentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_appointment_item, parent, false)
        return DoctorAppointmentViewHolder(view)
    }

    override fun getItemCount(): Int = doctorAppointmentList.size

    override fun onBindViewHolder(holder: DoctorAppointmentViewHolder, position: Int) {
        val poJo = doctorAppointmentList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startDoctorAddPrescriptionActivity(context, poJo)
        }
    }
}
