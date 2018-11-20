package com.joyBox.shefaa.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.ReportExpense
import com.joyBox.shefaa.entities.ReportReceipt

/**
 * Created by Adhamkh on 2018-11-19.
 */
class ReportReceiptViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {


    @BindView(R.id.name)
    lateinit var name: TextView

    @BindView(R.id.patientName)
    lateinit var patientName: TextView

    @BindView(R.id.date)
    lateinit var date: TextView

    @BindView(R.id.cost)
    lateinit var cost: TextView

    @BindView(R.id.note)
    lateinit var note: TextView


    init {
        ButterKnife.bind(this, view)
    }

    fun bind(reportReceipt: ReportReceipt) {
        name.text = reportReceipt.Receipt_title
        cost.text = reportReceipt.Receipt_value
        date.text = reportReceipt.Receipt_Date
        patientName.text = reportReceipt.Patient_Name
        note.text = reportReceipt.Notes
    }
}