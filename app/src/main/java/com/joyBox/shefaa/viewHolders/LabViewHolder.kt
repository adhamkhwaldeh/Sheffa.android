package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.joyBox.shefaa.entities.Lab

/**
 * Created by Adhamkh on 2018-10-12.
 */
class LabViewHolder : RecyclerView.ViewHolder {

    var context: Context

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(lab: Lab) {

    }

}