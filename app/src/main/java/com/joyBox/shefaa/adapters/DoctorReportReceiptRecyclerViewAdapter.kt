package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.ReportReceipt
import com.joyBox.shefaa.viewHolders.ReportReceiptViewHolder

/**
 * Created by Adhamkh on 2018-11-19.
 */
class DoctorReportReceiptRecyclerViewAdapter (val context: Context, val doctorList: MutableList<ReportReceipt>) :
        RecyclerView.Adapter<ReportReceiptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportReceiptViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.report_receipt_item, parent, false)
        return ReportReceiptViewHolder(view)
    }

    override fun getItemCount(): Int = doctorList.size

    override fun onBindViewHolder(holder: ReportReceiptViewHolder, position: Int) {
        val poJo = doctorList[position]
        holder.bind(poJo)
//        holder.itemView.setOnClickListener {
//            IntentHelper.startDoctorDetailsActivity(context, poJo)
//        }
    }

}