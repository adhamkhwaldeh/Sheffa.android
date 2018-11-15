package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.viewHolders.DoctorSpecializationViewHolder

class DoctorSpecializationRecyclerViewAdapter constructor(val context: Context, val specializationList: MutableList<SpecialistAutoComplete>) : RecyclerView.Adapter<DoctorSpecializationViewHolder>() {

//    var selectedcheckbox: CheckBox? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorSpecializationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_specialization_item, parent, false)
        return DoctorSpecializationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorSpecializationViewHolder, position: Int) {
        val poJo = specializationList[position]
        holder.bind(poJo)
        holder.selectedItemCheckBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            poJo.selected=isChecked
//            if (isChecked) {
//
////                selectedcheckbox?.isChecked = false
//                holder.selectedItemCheckBox.isChecked = true
////                selectedcheckbox = holder.selectedItemCheckBox
//            }
        })
    }

    override fun getItemCount(): Int = specializationList.size

    fun getDoctorSpecializationList(): MutableList<SpecialistAutoComplete> {
        val specialistAutoCompleteList: MutableList<SpecialistAutoComplete> = mutableListOf()
        specializationList.forEach {
            if (it.selected)
                specialistAutoCompleteList.add(it)
        }
        return specialistAutoCompleteList
    }

}