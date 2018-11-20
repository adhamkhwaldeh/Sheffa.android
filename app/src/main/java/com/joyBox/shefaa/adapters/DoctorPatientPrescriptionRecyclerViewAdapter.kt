package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorPatientPrescription
import com.joyBox.shefaa.viewHolders.DoctorPatientPrescriptionViewHolder

/**
 * Created by Adhamkh on 2018-11-16.
 */
class DoctorPatientPrescriptionRecyclerViewAdapter (val context: Context,
                                                    val doctorPatientPrescriptionList:
                                                    MutableList<DoctorPatientPrescription>) :
        RecyclerView.Adapter<DoctorPatientPrescriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorPatientPrescriptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_patient_prescription_item, parent, false)
        return DoctorPatientPrescriptionViewHolder(view)
    }

    override fun getItemCount(): Int = doctorPatientPrescriptionList.size

    override fun onBindViewHolder(holder: DoctorPatientPrescriptionViewHolder, position: Int) {
        val poJo = doctorPatientPrescriptionList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
//            IntentHelper.startMyPatientProfileActivity(context, poJo.picture)
        }
    }


}