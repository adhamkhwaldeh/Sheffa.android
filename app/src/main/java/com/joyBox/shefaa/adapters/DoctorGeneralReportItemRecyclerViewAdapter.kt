package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.GeneralReportItem
import com.joyBox.shefaa.viewHolders.GeneralReportItemViewHolder

class DoctorGeneralReportItemRecyclerViewAdapter(val context: Context, val generalReportItemList: MutableList<GeneralReportItem>) :
        RecyclerView.Adapter<GeneralReportItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralReportItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.general_report_item_item, parent, false)
        return GeneralReportItemViewHolder(view)
    }

    override fun getItemCount(): Int = generalReportItemList.size

    override fun onBindViewHolder(holder: GeneralReportItemViewHolder, position: Int) {
        val poJo = generalReportItemList[position]
        holder.bind(poJo)
    }

}
