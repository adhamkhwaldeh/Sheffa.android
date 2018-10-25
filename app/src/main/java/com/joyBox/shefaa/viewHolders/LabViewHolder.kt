package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Lab
import com.schibstedspain.leku.LATITUDE

/**
 * Created by Adhamkh on 2018-10-12.
 */
class LabViewHolder : RecyclerView.ViewHolder {

    var context: Context

    @BindView(R.id.labName)
    lateinit var labName: TextView

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(lab: Lab) {
        labName.text = lab.name
    }

}