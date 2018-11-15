package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.viewHolders.DoctorSpecializationTagViewHolder

class DoctorSpecializationTagRecyclerViewAdapter constructor(val context: Context,
                                                             val specializationList: MutableList<SpecialistAutoComplete>)
    : RecyclerView.Adapter<DoctorSpecializationTagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorSpecializationTagViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_specialization_tag_item, parent, false)
        return DoctorSpecializationTagViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorSpecializationTagViewHolder, position: Int) {
        val pos = holder.adapterPosition
        val poJo = specializationList[pos]
        holder.bind(poJo)

        holder.closeBtn.setOnClickListener({
            specializationList.removeAt(pos)
            notifyItemRemoved(pos)
        })

    }

    override fun getItemCount(): Int = specializationList.size

}