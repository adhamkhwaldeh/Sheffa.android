package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorPatient
import com.joyBox.shefaa.entities.User
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorPatientViewHolder

/**
 * Created by Adhamkh on 2018-11-16.
 */
class DoctorPatientRecyclerViewAdapter(val context: Context, val doctorPatientList: MutableList<DoctorPatient>) :
        RecyclerView.Adapter<DoctorPatientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorPatientViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_patient_item, parent, false)
        return DoctorPatientViewHolder(view)
    }

    override fun getItemCount(): Int = doctorPatientList.size

    override fun onBindViewHolder(holder: DoctorPatientViewHolder, position: Int) {
        val poJo = doctorPatientList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startMyMedicalProfileActivity(context, User(poJo.uid))
//            IntentHelper.startMyPatientProfileActivity(context, poJo.picture)
        }
    }

}
