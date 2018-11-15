package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorAppointmentTreatmentViewHolder

class DoctorAppointmentTreatmentRecyclerViewAdapter(val context: Context, val doctorAppointmentList: MutableList<DoctorAppointment>) :
        RecyclerView.Adapter<DoctorAppointmentTreatmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAppointmentTreatmentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_appointment_treatment_item, parent, false)
        return DoctorAppointmentTreatmentViewHolder(view)
    }

    override fun getItemCount(): Int = doctorAppointmentList.size

    override fun onBindViewHolder(holder: DoctorAppointmentTreatmentViewHolder, position: Int) {
        val poJo = doctorAppointmentList[position]
        holder.bind(poJo)

        holder.addPrescriptionBtn.setOnClickListener({
            IntentHelper.startDoctorAddPrescriptionActivity(context, poJo)
        })
        holder.patientName.setOnClickListener {
            IntentHelper.startMyPatientProfileActivity(context, poJo.patient_ID)
        }
        holder.startAppointment.setOnClickListener {
            RxBus.publish(MessageEvent(EventActions.SwitchAppointmentStateStart_Tag, poJo))
        }
        holder.endAppointment.setOnClickListener {
            RxBus.publish(MessageEvent(EventActions.SwitchAppointmentStateEnd_Tag, poJo))
        }
        holder.itemView.setOnClickListener {
            IntentHelper.startDoctorAppointmentDetailsActivity(context, poJo)
        }

    }
}
