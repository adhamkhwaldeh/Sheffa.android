package com.joyBox.shefaa.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.GeneralReportItem


class GeneralReportItemViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.name)
    lateinit var name: TextView

    @BindView(R.id.incoming)
    lateinit var incoming: TextView

    @BindView(R.id.spending)
    lateinit var spending: TextView

    @BindView(R.id.total)
    lateinit var total: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(generalReportItem: GeneralReportItem) {
        name.text = generalReportItem.name
        incoming.text = generalReportItem.incoming
        spending.text = generalReportItem.spending
        total.text = generalReportItem.net
    }
}