package com.joyBox.shefaa.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.ReportExpense

class ReportExpenseViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.name)
    lateinit var name: TextView

    @BindView(R.id.cost)
    lateinit var cost: TextView

    @BindView(R.id.date)
    lateinit var date: TextView

    @BindView(R.id.type)
    lateinit var type: TextView

    @BindView(R.id.note)
    lateinit var note: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(reportExpense: ReportExpense) {
        name.text = reportExpense.title
        cost.text = reportExpense.how_much
        date.text = reportExpense.paying_date
        type.text = reportExpense.type_of_payments
        note.text = reportExpense.notes
    }
}