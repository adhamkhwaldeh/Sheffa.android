package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.viewHolders.DiagonsisViewHolder

/**
 * Created by Adhamkh on 2018-12-04.
 */
class DiagonsisRecyclerViewAdapter(val context: Context, val diagnosisList: MutableList<DiagnosiseAutoComplete>) :
        RecyclerView.Adapter<DiagonsisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagonsisViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.diagnosis_item, parent, false)
        return DiagonsisViewHolder(view)
    }

    override fun getItemCount(): Int = diagnosisList.size

    override fun onBindViewHolder(holder: DiagonsisViewHolder, position: Int) {
        val poJo = diagnosisList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            RxBus.publish(MessageEvent(EventActions.Diagnosis_Tag, poJo))
        }
    }

}
