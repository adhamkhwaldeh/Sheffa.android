package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Lab
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.LabViewHolder

/**
 * Created by Adhamkh on 2018-10-12.
 */
class LabRecyclerViewAdapter (val context: Context, val labList: MutableList<Lab>) :
        RecyclerView.Adapter<LabViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lab_item, parent, false)
        return LabViewHolder(view)
    }

    override fun getItemCount(): Int = labList.size

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        val poJo = labList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startLabDetailsActivity(context, poJo)
        }
    }

}