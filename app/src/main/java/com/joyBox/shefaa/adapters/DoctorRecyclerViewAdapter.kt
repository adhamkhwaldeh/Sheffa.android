package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorViewHolder

class DoctorRecyclerViewAdapter(val context: Context, val doctorList: MutableList<Doctor>) :
        RecyclerView.Adapter<DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int = doctorList.size

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val poJo = doctorList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startDoctorDetailsActivity(context, poJo)
        }
    }

}