package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Lab
import com.joyBox.shefaa.entities.LabTest
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.LabTestViewHolder
import com.joyBox.shefaa.viewHolders.LabViewHolder

/**
 * Created by Adhamkh on 2018-12-04.
 */
class LabTestRecyclerViewAdapter constructor(val context: Context, var labTestList: MutableList<LabTest>) :
        RecyclerView.Adapter<LabTestViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabTestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lab_test_item, parent, false)
        return LabTestViewHolder(view)
    }

    override fun getItemCount(): Int = labTestList.size

    override fun onBindViewHolder(holder: LabTestViewHolder, position: Int) {
        val poJo = labTestList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            RxBus.publish(MessageEvent(EventActions.Available_Test_Tag, poJo))
        }
    }

}