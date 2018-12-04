package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.LabTest


class LabTestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    @BindView(R.id.labTestName)
    lateinit var labTestName: TextView

    fun bind(labTest: LabTest) {
        labTestName.text = labTest.test_type
    }


}