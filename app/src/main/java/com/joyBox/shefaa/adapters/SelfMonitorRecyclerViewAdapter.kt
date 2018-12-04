package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.SelfMonitorEntity
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorViewHolder
import com.joyBox.shefaa.viewHolders.SelfMonitorViewHolder

class SelfMonitorRecyclerViewAdapter(val context: Context, val selfMonitorList: List<SelfMonitorEntity>) :
        RecyclerView.Adapter<SelfMonitorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelfMonitorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.selft_monitor_item, parent, false)
        return SelfMonitorViewHolder(view)
    }

    override fun getItemCount(): Int = selfMonitorList.size

    override fun onBindViewHolder(holder: SelfMonitorViewHolder, position: Int) {
        val poJo = selfMonitorList[position]
        holder.bind(poJo)
//        holder.itemView.setOnClickListener {
//            IntentHelper.startDoctorDetailsActivity(context, poJo)
//        }
    }

}
