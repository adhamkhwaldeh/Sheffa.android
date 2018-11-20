package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.ReportExpense
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.ReportExpenseViewHolder

class DoctorReportExpenseRecyclerViewAdapter (val context: Context, val doctorList: MutableList<ReportExpense>) :
        RecyclerView.Adapter<ReportExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportExpenseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.report_expense_item, parent, false)
        return ReportExpenseViewHolder(view)
    }

    override fun getItemCount(): Int = doctorList.size

    override fun onBindViewHolder(holder: ReportExpenseViewHolder, position: Int) {
        val poJo = doctorList[position]
        holder.bind(poJo)
//        holder.itemView.setOnClickListener {
//            IntentHelper.startDoctorDetailsActivity(context, poJo)
//        }
    }

}