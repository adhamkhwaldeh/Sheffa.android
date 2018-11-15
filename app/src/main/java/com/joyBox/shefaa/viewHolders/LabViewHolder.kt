package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Lab

class LabViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    @BindView(R.id.labName)
    lateinit var labName: TextView

    fun bind(lab: Lab) {
        labName.text = lab.name
    }


}